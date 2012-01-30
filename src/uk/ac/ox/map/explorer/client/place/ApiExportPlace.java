package uk.ac.ox.map.explorer.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ApiExportPlace extends Place {

  public static class Tokenizer implements PlaceTokenizer<ApiExportPlace> {

    @Override
    public ApiExportPlace getPlace(String token) {
      return new ApiExportPlace();
    }

    @Override
    public String getToken(ApiExportPlace place) {
      return "";
    }
    
  }
}
