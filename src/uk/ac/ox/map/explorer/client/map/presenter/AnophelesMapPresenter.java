package uk.ac.ox.map.explorer.client.map.presenter;


import java.util.List;

import uk.ac.ox.map.explorer.client.event.AnophelineSelectedEvent;
import uk.ac.ox.map.explorer.client.proxy.EntityProxy;
import uk.ac.ox.map.explorer.client.proxy.MapLayer;
import uk.ac.ox.map.explorer.client.proxy.RetrieveVars;
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
  
  private static final String ANOPHELES_ANOPHELINE_DISPLAY = "Anopheles:anopheline_display";

  @Inject
  public AnophelesMapPresenter(ResourceBundle resources, AnophelesMapInfoPresenter mapInfoPresenter) {
    
    super(resources, mapInfoPresenter);
    
  }
  
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    
    super.start(panel, eventBus);
    
    eventBus.addHandler(AnophelineSelectedEvent.TYPE, new AnophelineSelectedEvent.Handler() {
      @Override
      public void onAnophelineSelected(AnophelineSelectedEvent requestEvent) {
        EntityProxy extent = requestEvent.getAnopheline();
        mapView.zoomToBounds(extent);
        
        mapView.setCql(ANOPHELES_ANOPHELINE_DISPLAY, "anopheline_id=" + requestEvent.getAnopheline().getId());
      }
    });
    
  }

  @Override
  public List<MapLayer> getLayers() {
    
//    List<MapLayer> layers = new ArrayList<MapLayer>();
//    layers.add(new MapLayer("Static:admin0", "Country boundaries", resources.countryBoundary(), true, "Country boundaries"));
//    layers.add(new MapLayer(ANOPHELES_ANOPHELINE_DISPLAY, "Presence points", resources.prPoint(), false, "Ano points"));
    
//    for (MapLayer mapLayer : layers) {
//      mapView.addWmsLayer(mapLayer, true);
//    }
//    
//    return layers;
    
    return new RetrieveVars().getLayers("Anopheles");
    
  }
}
