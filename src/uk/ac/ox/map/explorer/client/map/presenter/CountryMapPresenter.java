package uk.ac.ox.map.explorer.client.map.presenter;

import java.util.List;

import uk.ac.ox.map.explorer.client.event.CountrySelectedEvent;
import uk.ac.ox.map.explorer.client.proxy.EntityProxy;
import uk.ac.ox.map.explorer.client.proxy.MapLayer;
import uk.ac.ox.map.explorer.client.proxy.RetrieveVars;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * The presenter for the PR perspective
 * 
 * @author will
 */
public class CountryMapPresenter extends BaseMapPresenter {
  
  @Inject
  public CountryMapPresenter(ResourceBundle resources,
      CountryMapInfoPresenter mapInfoPresenter) {
    
    super(resources, mapInfoPresenter);
    
  }
  
  @Override
  public List<MapLayer> getLayers() {
    
    return new RetrieveVars().getLayers("PR");
  }
  
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    super.start(panel, eventBus);
    
    eventBus.addHandler(CountrySelectedEvent.TYPE,
        new CountrySelectedEvent.Handler() {
          @Override
          public void onCountrySelected(CountrySelectedEvent requestEvent) {
            EntityProxy extent = requestEvent.getCountry();
            mapView.zoomToBounds(extent);
          }
        });
  }
  
}
