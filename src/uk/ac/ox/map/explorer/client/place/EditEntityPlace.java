package uk.ac.ox.map.explorer.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class EditEntityPlace extends Place {
  
  public static class Tokenizer implements PlaceTokenizer<EditEntityPlace> {
    
    @Override
    public EditEntityPlace getPlace(String token) {
      
      String[] parts = token.split("/");
      if (parts.length > 1) {
        return new EditEntityPlace(parts[0], Long.parseLong(parts[1]));
      }
      return new EditEntityPlace(parts[0]);
      
    }
    
    @Override
    public String getToken(EditEntityPlace place) {
      StringBuilder sb = new StringBuilder();
      sb.append(place.getEntityName());
      if (place.getEntityId() != null) {
        sb.append("/").append(place.getEntityId());
      }
      return sb.toString();
    }
  }
  
  private String entityName;
  
  private Long entityId;
  
  public EditEntityPlace(String entityName) {
    this.entityName = entityName;
  }
  
  public EditEntityPlace(String entityName, Long entityId) {
    this.entityName = entityName;
    this.entityId = entityId;
  }
  
  public Long getEntityId() {
    return entityId;
  }
  
  public String getEntityName() {
    return entityName;
  }
  
}
