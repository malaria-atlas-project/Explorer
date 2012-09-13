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
public class MapInfoServiceImpl extends RemoteServiceServlet implements
    MapInfoService {
  
  private Provider<EntityManager> emProvider;
  private final Gson gson = new Gson();
  
  @Inject
  public MapInfoServiceImpl(Provider<EntityManager> emProvider) {
    this.emProvider = emProvider;
  }
  
  @Override
  public String getAnophelineExtentInfo(Boolean presencePointsEnabled,
      Boolean absencePointsEnabled, Long anoId, Double minX, Double minY,
      Double maxX, Double maxY) {
    
    EntityManager em = emProvider.get();
    String sqlString = "select count(distinct(site_id)) as siteCount, count(distinct(id)) as samplePeriodCount "
        + "from explorer.anopheline_display "
        + "where "
        + "geom && ST_SetSRID(st_makebox2d(st_makepoint(:minX,:minY), st_makepoint(:maxX,:maxY)), 4326) ";
    
    if (anoId != null) {
      sqlString += "and (anopheline_id = :anoId) ";
    }
    
    if (!presencePointsEnabled) {
      sqlString += "and (NOT is_present = TRUE) ";
    }
    if (!absencePointsEnabled) {
      sqlString += "and (NOT is_present = FALSE) ";
    }
    
    sqlString += ";";
    
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
  
  @Override
  public String getPrExtentInfo(Boolean availablePointsEnabled,
      Boolean unavailablePointsEnabled, Double minX, Double minY, Double maxX,
      Double maxY) {
    
    EntityManager em = emProvider.get();
    String query_string = "select count(distinct(geom)) as siteCount, count(distinct(id)) as surveyReplicateCount from explorer.pr_display where pr_display.geom && ST_SetSRID(st_makebox2d(st_makepoint(?,?), st_makepoint(?,?)), 4326) ";
    if (!availablePointsEnabled) {
      query_string += "and NOT ( is_available = True ) ";
    }
    if (!unavailablePointsEnabled) {
      query_string += "and NOT ( is_available = False ) ";
    }
    query_string += ";";
    
    Query q = em.createNativeQuery(query_string);
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
  
}
