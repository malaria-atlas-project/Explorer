package uk.ac.ox.map.explorer.client.proxy;

public interface MapLayer {
  
  String getImageUrl();
  
  String getInfoText();
  
  String getName();
  
  boolean getUseResizeTransition();
  
  boolean getUseWebCache();
  
  String getWmsLayerName();
  
  void setImageUrl(String url);
  
  void setInfoText(String infoText);
  
  void setName(String name);
  
  void setUseResizeTransition(boolean useResizeTransition);
  
  void setUseWebCache(boolean useWebCache);
  
  void setWmsLayerName(String string);
  
}
