package uk.ac.ox.map.explorer.client.proxy.seed;

public interface MapLayer {

  String getName();
  void setName(String name);
  
  String getWmsLayerName();
  void setWmsLayerName(String string);

  boolean getUseWebCache();
  void setUseWebCache(boolean useWebCache);
  
  String getInfoText();
  void setInfoText(String infoText);
  
}
