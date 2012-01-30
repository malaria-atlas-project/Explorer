package uk.ac.ox.map.explorer.client.place;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EntityPlace extends Place {

  private final String entityName;
  private final String queryString;
  private final String orderBy;
  private final Boolean isNamed;
  
  public EntityPlace(String entityName) {
    this.queryString = "";
    this.orderBy = "";
    this.entityName = entityName;
    this.isNamed = false;
  }

  public EntityPlace(String entityName, String queryString, String orderBy, Boolean isNamed) {
    this.queryString = (queryString != null) ? queryString : "";
    this.orderBy = (orderBy != null) ? orderBy : "";
    this.entityName = entityName;
    this.isNamed = isNamed;
  }
  
  public String getEntityName() {
    return entityName;
  }
  
  public String getQueryString() {
    return queryString;
  }
  
  public String getOrderBy() {
    return orderBy;
  }
  
  public Boolean isNamedQuery() {
    return isNamed;
  }

  public String getPayload() {
    return new Tokenizer().getToken(this);
  }
  
  public static class Tokenizer implements PlaceTokenizer<EntityPlace> {
    
    private static final String orderParamName = "o";
    private static final String queryParamName = "q";
    private static final String isNamedQuery = "n";
    
    @Override
    public String getToken(EntityPlace place) {
      
      QueryStringBuilder qsb = new QueryStringBuilder('/');
      
      qsb.addParam(place.getEntityName());
      qsb.addParam(queryParamName, place.getQueryString(), ':');
      qsb.addParam(orderParamName, place.getOrderBy(), ':');
      if (place.isNamedQuery()) {
	      qsb.addParam(isNamedQuery, place.isNamedQuery().toString(), ':');
      }
      
      return qsb.finish();
    }

    @Override
    public EntityPlace getPlace(String token) {
      
      String[] parts = token.split("/");
      
      String entityName = parts[0];
      
      Map<String, String> params = new HashMap<String, String>();
      
      for (int i = 1; i < parts.length; i++) {
        String[] p = parts[i].split(":");
        params.put(p[0], p[1]);
      }
      
      String named = params.get(isNamedQuery);
      boolean inq = false;
      if (named != null && named.equals("true")) {
        inq = true;
      }
      
      return new EntityPlace(entityName, params.get(queryParamName), params.get(orderParamName), inq);
    }
  }


}
