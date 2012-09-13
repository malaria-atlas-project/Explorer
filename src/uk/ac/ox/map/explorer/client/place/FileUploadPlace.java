package uk.ac.ox.map.explorer.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class FileUploadPlace extends Place {
  
  public static class Tokenizer implements PlaceTokenizer<FileUploadPlace> {
    @Override
    public FileUploadPlace getPlace(String token) {
      return new FileUploadPlace(token);
    }
    
    @Override
    public String getToken(FileUploadPlace place) {
      
      return place.getAction();
      
    }
  }
  
  private final String action;
  
  public FileUploadPlace(String action) {
    this.action = action;
  }
  
  public String getAction() {
    return action;
  }
  
}
