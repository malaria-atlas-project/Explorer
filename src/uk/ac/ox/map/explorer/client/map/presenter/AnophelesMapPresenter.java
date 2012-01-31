package uk.ac.ox.map.explorer.client.map.presenter;


import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.resource.ResourceBundle;

import com.google.inject.Inject;

/**
 * 
 * @author will
 *
 */

public class AnophelesMapPresenter extends BaseMapPresenter {
  
  @Inject
  public AnophelesMapPresenter(ResourceBundle resources) {
    
    super(resources);
    
  }

  @Override
  public List<MapLayer> addLayers() {
    
    String gwcUrl = "http://map1.zoo.ox.ac.uk/geoserver/gwc/service/wms";
    
    List<MapLayer> layers = new ArrayList<MapLayer>();
    layers.add(new MapLayer("Static:admin0", "Country boundaries", resources.countryBoundary()));
    layers.add(new MapLayer("PR:pf_points_available", "Pf points", resources.prPoint()));
    
    for (MapLayer mapLayer : layers) {
      mapView.addWmsLayer(mapLayer.getName(), gwcUrl, mapLayer.getWmsLayerName(), true);
    }
    
    return layers;
    
    
  }


}
