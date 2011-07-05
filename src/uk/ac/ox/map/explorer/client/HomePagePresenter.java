package uk.ac.ox.map.explorer.client;


import uk.ac.ox.map.explorer.client.base.view.MapView;
import uk.ac.ox.map.explorer.client.map.view.MapExample;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * 
 * @author will
 *
 */

public class HomePagePresenter extends AbstractActivity {
  
  private MapView mapView;
  private MapExample map;

  @Inject
  public HomePagePresenter(MapView mapView, MapExample map) {
    this.mapView = mapView;
    this.map = map;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(mapView);
    mapView.getMapPanel().setWidget(map);
  }
	

}
