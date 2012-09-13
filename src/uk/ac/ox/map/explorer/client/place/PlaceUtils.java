package uk.ac.ox.map.explorer.client.place;

import java.util.HashMap;
import java.util.Map;

public class PlaceUtils {
  public static Map<String, String> getMapFromParams(String paramString) {
    
    String[] paramlist = paramString.split("&");
    Map<String, String> placeParams = new HashMap<String, String>();
    if (paramString.isEmpty()) {
      return placeParams;
    }
    
    for (int i = 0; i < paramlist.length; i++) {
      String parts = paramlist[i];
      String[] headTail = parts.split("=");
      placeParams.put(headTail[0], headTail[1]);
    }
    return placeParams;
  }
  
  // TODO: decide on a good way to do this!!!
  public static Map<String, String> getMapFromParams2(String paramString) {
    
    String[] paramlist = paramString.split("&");
    Map<String, String> placeParams = new HashMap<String, String>();
    if (paramString.isEmpty()) {
      return placeParams;
    }
    
    for (int i = 0; i < paramlist.length; i++) {
      String parts = paramlist[i];
      String[] headTail = parts.split("_");
      placeParams.put(headTail[0], headTail[1]);
    }
    return placeParams;
  }
  
  public static String getStringFromParamMap(Map<String, String> params) {
    
    if (params == null) {
      return null;
    }
    
    StringBuilder sb = new StringBuilder();
    int i = 0;
    for (String key : params.keySet()) {
      String val = params.get(key);
      if (i != 0) {
        sb.append("&");
      }
      sb.append(key).append("=").append(val);
      i++;
    }
    return sb.toString();
  }
  
}
