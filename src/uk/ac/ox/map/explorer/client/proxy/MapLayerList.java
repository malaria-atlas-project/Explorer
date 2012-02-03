package uk.ac.ox.map.explorer.client.proxy;

import java.util.List;


public interface MapLayerList {
  
  void setLayerList(List<MapLayer> layers);
  
  List<MapLayer> getLayerList();

}
