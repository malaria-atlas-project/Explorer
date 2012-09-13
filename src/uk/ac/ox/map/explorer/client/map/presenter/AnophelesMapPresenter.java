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
  
  private static final String ANOPHELES_ANOPHELINE_PRESENCE_DISPLAY = "Explorer:anopheline_display_presence";
  private static final String ANOPHELES_ANOPHELINE_ABSENCE_DISPLAY = "Explorer:anopheline_display_absence";
  
  @Inject
  public AnophelesMapPresenter(ResourceBundle resources,
      AnophelesMapInfoPresenter mapInfoPresenter) {
    
    super(resources, mapInfoPresenter);
    
  }
  
  @Override
  public List<MapLayer> getLayers() {
    
    return new RetrieveVars().getLayers("Anopheles");
    
  }
  
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    
    super.start(panel, eventBus);
    
    eventBus.addHandler(AnophelineSelectedEvent.TYPE,
        new AnophelineSelectedEvent.Handler() {
          @Override
          public void onAnophelineSelected(AnophelineSelectedEvent requestEvent) {
            EntityProxy extent = requestEvent.getAnopheline();
            if (extent == null) {
              mapView.zoomToBounds(new Double(-180), new Double(-90),
                  new Double(180), new Double(90));
              
              mapView.setCql(ANOPHELES_ANOPHELINE_PRESENCE_DISPLAY, null);
              mapView.setCql(ANOPHELES_ANOPHELINE_ABSENCE_DISPLAY, null);
            } else {
              mapView.zoomToBounds(extent);
              
              mapView.setCql(ANOPHELES_ANOPHELINE_PRESENCE_DISPLAY,
                  "anopheline_id=" + requestEvent.getAnopheline().getId());
              mapView.setCql(ANOPHELES_ANOPHELINE_ABSENCE_DISPLAY,
                  "anopheline_id=" + requestEvent.getAnopheline().getId());
            }
          }
        });
    
  }
}
