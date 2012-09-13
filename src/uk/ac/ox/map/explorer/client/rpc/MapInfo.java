package uk.ac.ox.map.explorer.client.rpc;

import com.google.gwt.core.client.JavaScriptObject;

public class MapInfo extends JavaScriptObject {
  
  public static final native MapInfo fromJson(String input) /*-{ 
    return eval('(' + input + ')') 
  }-*/;
  
  protected MapInfo() {
  }
  
  public final native int getSiteCount() /*-{ 
    return this.siteCount; 
  }-*/;
  
  public final native int getSurveyReplicateCount() /*-{ 
    return this.uniqueSiteDateCount; 
  }-*/;
  
}
