package uk.ac.ox.map.explorer.client.map.presenter;


import java.util.List;

import uk.ac.ox.map.explorer.client.event.CountrySelectedEvent;
import uk.ac.ox.map.explorer.client.proxy.ExtentProxy;
import uk.ac.ox.map.explorer.client.proxy.seed.MapLayer;
import uk.ac.ox.map.explorer.client.proxy.seed.RetrieveVars;
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
  public List<MapLayer> getLayers() {
    
    return new RetrieveVars().getLayers("PR");
  }

}
