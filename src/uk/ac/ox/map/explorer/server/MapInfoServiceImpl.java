package uk.ac.ox.map.explorer.server;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import uk.ac.ox.map.explorer.client.rpc.MapInfoService;

import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 */
@Singleton
@SuppressWarnings("serial")
public class MapInfoServiceImpl extends RemoteServiceServlet implements MapInfoService {

  private Provider<EntityManager> emProvider;
  private final Gson gson = new Gson();

  @Inject
  public MapInfoServiceImpl(Provider<EntityManager> emProvider) {
    this.emProvider = emProvider;
  }

  @Override
  public String getPrExtentInfo(Double minX, Double minY, Double maxX, Double maxY) {

    EntityManager em = emProvider.get();
    Query q = em.createNativeQuery("select count(distinct(site_id)) as siteCount, count(distinct(sr.id)) as surveyReplicateCount " + "from site s " + "join pr2010.survey su on s.id = su.site_id "
        + "join pr2010.survey_replicate sr on su.id = sr.survey_id " + "where s.geom && setsrid(st_makebox2d(st_makepoint(?,?), st_makepoint(?,?)), 4326);");
    q.setParameter(1, minX);
    q.setParameter(2, minY);
    q.setParameter(3, maxX);
    q.setParameter(4, maxY);

    Object[] res = (Object[]) q.getSingleResult();

    Map<String, BigInteger> m = new HashMap<String, BigInteger>();
    m.put("siteCount", (BigInteger) res[0]);
    m.put("uniqueSiteDateCount", (BigInteger) res[1]);

    String json = gson.toJson(m);

    return json;
  }

  @Override
  public String getAnophelineExtentInfo(Long anoId, Double minX, Double minY, Double maxX, Double maxY) {

    EntityManager em = emProvider.get();
    String sqlString = "select count(distinct(site_id)) as siteCount, count(distinct(id)) as samplePeriodCount " 
      + "from explorer.anopheline_display " 
      + "where "
      + "geom && setsrid(st_makebox2d(st_makepoint(:minX,:minY), st_makepoint(:maxX,:maxY)), 4326) ";

    if (anoId != null) {
      sqlString += "and (anopheline_id = :anoId);";
    }

    Query q = em.createNativeQuery(sqlString);

    q.setParameter("minX", minX);
    q.setParameter("minY", minY);
    q.setParameter("maxX", maxX);
    q.setParameter("maxY", maxY);

    if (anoId != null) {
      q.setParameter("anoId", anoId);
    }

    Object[] res = (Object[]) q.getSingleResult();

    Map<String, BigInteger> m = new HashMap<String, BigInteger>();
    m.put("siteCount", (BigInteger) res[0]);
    m.put("uniqueSiteDateCount", (BigInteger) res[1]);

    String json = gson.toJson(m);

    return json;
  }

}
