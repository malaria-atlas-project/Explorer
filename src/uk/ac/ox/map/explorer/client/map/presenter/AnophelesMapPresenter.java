package uk.ac.ox.map.explorer.client.map.presenter;


import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.event.AnophelineSelectedEvent;
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

public class AnophelesMapPresenter extends BaseMapPresenter {
  
  @Inject
  public AnophelesMapPresenter(ResourceBundle resources) {
    
    super(resources);
    
  }
  
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    
    super.start(panel, eventBus);
    
    eventBus.addHandler(AnophelineSelectedEvent.TYPE, new AnophelineSelectedEvent.Handler() {
      @Override
      public void onAnophelineSelected(AnophelineSelectedEvent requestEvent) {
        ExtentProxy extent = requestEvent.getAnopheline().getExtent();
        mapView.zoomToBounds(extent);
        
        mapView.setCql("Presence points", "anopheline_id=" + requestEvent.getAnopheline().getId());
      }
    });
    
  }

  @Override
  public List<MapLayer> addLayers() {
    
    List<MapLayer> layers = new ArrayList<MapLayer>();
    layers.add(new MapLayer("Static:admin0", "Country boundaries", resources.countryBoundary(), true));
    layers.add(new MapLayer("Anopheles:anopheline_display", "Presence points", resources.prPoint(), false));
    
    for (MapLayer mapLayer : layers) {
      mapView.addWmsLayer(mapLayer, true);
    }
    
    return layers;
  }
}
