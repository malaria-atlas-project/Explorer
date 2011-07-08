package uk.ac.ox.map.explorer.client;


import uk.ac.ox.map.explorer.client.base.view.CompositeMapView;

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
  
  private CompositeMapView mapView;
  

  @Inject
  public HomePagePresenter(CompositeMapView mapView) {
    this.mapView = mapView;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(mapView);
  }
	

}
