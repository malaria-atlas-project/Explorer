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

public class CountryMapPresenter extends BaseMapPresenter {
  
  @Inject
  public CountryMapPresenter(ResourceBundle resources) {
    
    super(resources);
    
  }

  @Override
  public List<MapLayer> addLayers() {
    
    List<MapLayer> layers = new ArrayList<MapLayer>();
    layers.add(new MapLayer("Base:pr_mean", "2010 endemicity", resources.endemicityScale(), true));
    layers.add(new MapLayer("Static:admin0", "Country boundaries", resources.countryBoundary(), true));
    layers.add(new MapLayer("PR:pf_points_available", "Pf points", resources.prPoint(), true));
    
    for (MapLayer mapLayer : layers) {
      mapView.addWmsLayer(mapLayer, true);
    }
    
    return layers;
  }

}
