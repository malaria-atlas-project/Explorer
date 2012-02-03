package uk.ac.ox.map.request.server;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import uk.ac.ox.map.explorer.client.place.EntityPlace;
import uk.ac.ox.map.explorer.client.place.PlaceUtils;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.google.inject.servlet.RequestScoped;

@RequestScoped
public class SimpleDao {

  private static final int MAXRESULTS = 1000;
  private final EntityManager em;

  @Inject
  public SimpleDao(EntityManager em) {
    this.em = em;
  }
  
  public EntityManager getEntityManager() {
    return em;
  }

  public Object persist(Object obj) {
    
    EntityTransaction tx = em.getTransaction();
    tx.begin();

    em.persist(obj);
    em.flush();
    tx.commit();

    return obj;
  }

  public <T> T find(Class<T> clazz, Object id) {
    return em.find(clazz, id);
  }

  @Transactional
  public <T> void remove(Class<T> clazz, Object id) {
    em.remove(em.find(clazz, id));
  }

  public <T> List<T> search(Integer firstResult, Integer maxResults, String searchParam, Class<T> clazz) {

    if (firstResult == null) {
      return null;
    }

    CriteriaBuilder cb = em.getCriteriaBuilder();

    EntityPlace ep = new EntityPlace.Tokenizer().getPlace(searchParam);

    String q = ep.getQueryString();

    TypedQuery<T> tq;

    if (ep.isNamedQuery()) {
      String[] parts = q.split("=");
      String queryName = parts[0];
      String queryString = parts[1];
      tq = em.createNamedQuery(queryName, clazz);
      if (queryName.equals("findSitesByPrSource")) {
        tq.setParameter("sourceId", Long.getLong(queryString));
        tq.setMaxResults(MAXRESULTS);
      } else if (queryName.equals("findContactsByPartialString")) {
        tq.setParameter("q", queryString.toLowerCase());
        tq.setMaxResults(maxResults);
      } else {
        return null;
      }

    } else {
      CriteriaQuery<T> cq = CriteriaQueryBuilder.getQuery(clazz, ep, cb);
      tq = em.createQuery(cq).setFirstResult(firstResult).setMaxResults(maxResults);
    }

    return tq.getResultList();
  }

  public Long searchCount(String searchParams, Class<?> clazz) {

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Long> cq = CriteriaQueryBuilder.getCountQuery(clazz, PlaceUtils.getMapFromParams(searchParams), cb);
    Long resultCount = em.createQuery(cq).getSingleResult().longValue();
    return resultCount;

  }

  public <T> List<T> all(Class<T> clazz) {

    CriteriaQuery<T> cq = em.getCriteriaBuilder().createQuery(clazz);

    Root<T> root = cq.from(clazz);
    cq.select(root);

    TypedQuery<T> tq = em.createQuery(cq);
    tq.setMaxResults(MAXRESULTS);

    return tq.getResultList();
  }

}
