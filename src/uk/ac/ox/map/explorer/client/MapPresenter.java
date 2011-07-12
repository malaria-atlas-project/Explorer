package uk.ac.ox.map.explorer.client;


import uk.ac.ox.map.explorer.client.event.ExtentChangeRequestEvent;
import uk.ac.ox.map.explorer.client.map.view.MapView;
import uk.ac.ox.map.request.client.proxy.ExtentProxy;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * 
 * @author will
 *
 */

public class MapPresenter extends AbstractActivity {
  

  private MapView map;

  @Inject
  public MapPresenter(MapView map) {
    this.map = map;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(map);
    
    eventBus.addHandler(ExtentChangeRequestEvent.TYPE, new ExtentChangeRequestEvent.Handler() {
      @Override
      public void onExtentChangeRequest(ExtentChangeRequestEvent requestEvent) {
        ExtentProxy extent = requestEvent.getExtent();
        map.zoomToBounds(extent);
      }
    });
    
  }
	

}
