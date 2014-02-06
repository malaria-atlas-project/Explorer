package uk.ac.ox.map.explorer.client.map.presenter;

import java.util.List;

import uk.ac.ox.map.explorer.client.event.DuffySelectedEvent;
import uk.ac.ox.map.explorer.client.proxy.EntityProxy;
import uk.ac.ox.map.explorer.client.proxy.MapLayer;
import uk.ac.ox.map.explorer.client.proxy.RetrieveVars;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * The presenter for the Duffy perspective
 * 
 * @author andy
 */
public class DuffyMapPresenter extends BaseMapPresenter {

	@Inject
	  public DuffyMapPresenter(ResourceBundle resources,
	      DuffyMapInfoPresenter mapInfoPresenter) {
	    
	    super(resources, mapInfoPresenter);
	    
	  }
	  
	  @Override
	  public List<MapLayer> getLayers() {
	    
	    return new RetrieveVars().getLayers("Duffy");
	  }
	  
	  @Override
	  public void start(AcceptsOneWidget panel, EventBus eventBus) {
		    super.start(panel, eventBus);
		    
		    eventBus.addHandler(DuffySelectedEvent.TYPE,
		        new DuffySelectedEvent.Handler() {
		          @Override
		          public void onCountrySelected(DuffySelectedEvent requestEvent) {
		            EntityProxy extent = requestEvent.getCountry();
		            mapView.zoomToBounds(extent);
		          }
		        });
		  }
	  
}
