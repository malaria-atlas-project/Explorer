package uk.ac.ox.map.explorer.client.map.presenter;


import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.base.view.CompositeMapView;
import uk.ac.ox.map.explorer.client.event.ExtentChangeRequestEvent;
import uk.ac.ox.map.explorer.client.event.LayerChangeRequestEvent;
import uk.ac.ox.map.explorer.client.map.view.MapView;
import uk.ac.ox.map.explorer.client.proxy.ExtentProxy;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 * @author will
 *
 */

public class CountryMapPresenter extends BaseMapPresenter {
  
  @Inject
  public CountryMapPresenter(CompositeMapView compositeMapView, MapView mapView, ResourceBundle resources) {
    
    super(compositeMapView, mapView, resources);
    
  }

  @Override
  public List<MapLayer> addLayers() {
    
    String gwcUrl = "http://map1.zoo.ox.ac.uk/geoserver/gwc/service/wms";
    
    
    List<MapLayer> layers = new ArrayList<MapLayer>();
    layers.add(new MapLayer("Base:pr_mean", "2010 endemicity", resources.endemicityScale()));
    layers.add(new MapLayer("Static:admin0", "Country boundaries", resources.countryBoundary()));
    layers.add(new MapLayer("PR:pf_points_available", "Pf points", resources.prPoint()));
    
    for (MapLayer mapLayer : layers) {
      mapView.addWmsLayer(mapLayer.getName(), gwcUrl, mapLayer.getWmsLayerName(), true);
    }
    
    return layers;
    
    
  }


}
