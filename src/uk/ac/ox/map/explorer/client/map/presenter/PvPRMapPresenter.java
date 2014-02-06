package uk.ac.ox.map.explorer.client.map.presenter;

import java.util.List;

import uk.ac.ox.map.explorer.client.event.PvPRSelectedEvent;
import uk.ac.ox.map.explorer.client.proxy.EntityProxy;
import uk.ac.ox.map.explorer.client.proxy.MapLayer;
import uk.ac.ox.map.explorer.client.proxy.RetrieveVars;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * The presenter for the G6PD perspective
 * 
 * @author andy
 */

public class PvPRMapPresenter extends BaseMapPresenter {
	
	@Inject
	  public PvPRMapPresenter(ResourceBundle resources,
			  PvPRMapInfoPresenter mapInfoPresenter) {
	    
	    super(resources, mapInfoPresenter);
	    
	  }
	  
	  @Override
	  public List<MapLayer> getLayers() {
	    
	    return new RetrieveVars().getLayers("PvPR");
	  }
	  
	  @Override
	  public void start(AcceptsOneWidget panel, EventBus eventBus) {
		    super.start(panel, eventBus);
		    
		    eventBus.addHandler(PvPRSelectedEvent.TYPE,
		        new PvPRSelectedEvent.Handler() {
		          @Override
		          public void onCountrySelected(PvPRSelectedEvent requestEvent) {
		            EntityProxy extent = requestEvent.getCountry();
		            mapView.zoomToBounds(extent);
		          }
		        });
		  }

}
