package uk.ac.ox.map.explorer.client.proxy;

import java.util.List;

public interface MapLayerList {
  
  List<MapLayer> getLayerList();
  
  void setLayerList(List<MapLayer> layers);
  
}
