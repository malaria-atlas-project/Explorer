package uk.ac.ox.map.explorer.client.proxy;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;

public class RetrieveVars {
  
  private final CommFactory commFactory = GWT.create(CommFactory.class);
  
  public RetrieveVars() {
    
  }
  
  public List<MapLayer> getLayers(String varName) {
    
    MapLayerList layerList = AutoBeanCodex.decode(commFactory,
        MapLayerList.class, getValue(varName)).as();
    List<MapLayer> layers = layerList.getLayerList();
    
    return layers;
  }
  
  public native String getValue(String varName) /*-{
                                                return $wnd[varName];
                                                }-*/;
}
