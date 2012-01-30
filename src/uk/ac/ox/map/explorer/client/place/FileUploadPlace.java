package uk.ac.ox.map.explorer.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class FileUploadPlace extends Place {

  private final String action;

  public FileUploadPlace(String action) {
    this.action = action;
  }

  public String getAction() {
    return action;
  }
  
  public static class Tokenizer implements PlaceTokenizer<FileUploadPlace> {
    @Override
    public String getToken(FileUploadPlace place) {

      return place.getAction();

    }

    @Override
    public FileUploadPlace getPlace(String token) {
      return new FileUploadPlace(token);
    }
  }

}
