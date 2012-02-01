package uk.ac.ox.map.explorer.client.map.presenter;


import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.event.CountrySelectedEvent;
import uk.ac.ox.map.explorer.client.proxy.ExtentProxy;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * 
 * @author will
 *
 */

public class CountryMapPresenter extends BaseMapPresenter {
  
  @Inject
  public CountryMapPresenter(ResourceBundle resources, CountryMapInfoPresenter mapInfoPresenter) {
    
    super(resources, mapInfoPresenter);
    
  }
  
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    super.start(panel, eventBus);
    
    eventBus.addHandler(CountrySelectedEvent.TYPE, new CountrySelectedEvent.Handler() {
      @Override
      public void onCountrySelected(CountrySelectedEvent requestEvent) {
        ExtentProxy extent = requestEvent.getCountry().getExtent();
        mapView.zoomToBounds(extent);
      }
    });
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
