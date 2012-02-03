package uk.ac.ox.map.explorer.client.proxy;

import com.google.gwt.resources.client.ImageResource;

public interface MapLayer {

  String getName();
  void setName(String name);
  
  String getWmsLayerName();
  void setWmsLayerName(String string);

  boolean getUseWebCache();
  void setUseWebCache(boolean useWebCache);
  
  String getInfoText();
  void setInfoText(String infoText);
  
  String getImageUrl();
  void setImageUrl(String url);
  
}
