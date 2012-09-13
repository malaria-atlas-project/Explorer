package uk.ac.ox.map.request.server;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import uk.ac.ox.map.explorer.client.place.EntityPlace;
import uk.ac.ox.map.explorer.client.place.Order;
import uk.ac.ox.map.explorer.client.place.PlaceUtils;

/**
 * Basic attempt at factoring out query building logic.
 * 
 * Select required after return Only supports chained AND logic in restrictions
 * 
 */
public class CriteriaQueryBuilder {
  
  public static <T> CriteriaQuery<Long> getCountQuery(Class<T> clazz,
      Map<String, String> sp, CriteriaBuilder criteriaBuilder) {
    
    CriteriaQuery<Long> cq = criteriaBuilder.createQuery(Long.class);
    
    Root<T> sourceRoot = cq.from(clazz);
    cq.select(criteriaBuilder.count(sourceRoot));
    
    // Build restrictions TODO: remove repetition in above query
    
    Predicate restrictions = criteriaBuilder.conjunction();
    for (String key : sp.keySet()) {
      
      Object val = sp.get(key);
      if (val.equals("true")) {
        val = true;
      }
      if (val.equals("false")) {
        val = false;
      }
      
      String[] parts = key.split("_");
      
      String op = parts[1];
      Predicate pred;
      if (op.equals("contains")) {
        pred = criteriaBuilder.like(criteriaBuilder.lower(sourceRoot
            .<String> get(parts[0])), '%' + val.toString().toLowerCase() + '%');
      } else {
        pred = criteriaBuilder.equal(sourceRoot.get(parts[0]), val);
      }
      
      restrictions = criteriaBuilder.and(restrictions, pred);
      
    }
    cq.where(restrictions);
    
    return cq;
  }
  
  /**
   * 
   * @param <T>
   *          The entity being searched for
   * @param clazz
   *          The entity being searched for
   * @param searchParam
   *          Supplied search parameters
   * @param criteriaBuilder
   *          Criteria builder obtained from entitymanager
   * @param defaultOrder
   *          Only used when no ordering is requested in sp
   * @return
   */
  public static <T> CriteriaQuery<T> getQuery(Class<T> clazz, EntityPlace ep,
      CriteriaBuilder criteriaBuilder) {
    
    CriteriaQuery<T> cq = criteriaBuilder.createQuery(clazz);
    
    Root<T> sourceRoot = cq.from(clazz);
    cq.select(sourceRoot);
    
    /*
     * Set up ordering
     */
    String orderBy = ep.getOrderBy();
    if (orderBy.isEmpty()) {
      cq.orderBy(criteriaBuilder.asc(sourceRoot.get("id")));
    } else {
      Order o = new Order(orderBy);
      
      Path<Object> path = sourceRoot.get(o.getColName());
      
      if (o.isAscending()) {
        cq.orderBy(criteriaBuilder.asc(path));
      } else {
        cq.orderBy(criteriaBuilder.desc(path));
      }
    }
    
    /*
     * Build search
     */
    Map<String, String> searchParam = PlaceUtils.getMapFromParams(ep
        .getQueryString());
    
    Predicate restrictions = criteriaBuilder.conjunction();
    for (String key : searchParam.keySet()) {
      
      Object val = searchParam.get(key);
      if (val.equals("true")) {
        val = true;
      }
      if (val.equals("false")) {
        val = false;
      }
      
      String[] parts = key.split("_");
      
      String op = parts[1];
      Predicate pred;
      if (op.equals("contains")) {
        pred = criteriaBuilder.like(criteriaBuilder.lower(sourceRoot
            .<String> get(parts[0])), '%' + val.toString().toLowerCase() + '%');
      } else {
        pred = criteriaBuilder.equal(sourceRoot.get(parts[0]), val);
      }
      
      restrictions = criteriaBuilder.and(restrictions, pred);
    }
    cq.where(restrictions);
    
    return cq;
  }
  
}
