package uk.ac.ox.map.explorer.client.map.presenter;

import java.util.List;

import uk.ac.ox.map.explorer.client.event.HbSSelectedEvent;
import uk.ac.ox.map.explorer.client.proxy.EntityProxy;
import uk.ac.ox.map.explorer.client.proxy.MapLayer;
import uk.ac.ox.map.explorer.client.proxy.RetrieveVars;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * The presenter for the HbS perspective
 * 
 * @author andy
 */

public class HbSMapPresenter extends BaseMapPresenter {
	
  
	  @Inject
	  public HbSMapPresenter(ResourceBundle resources,
	      HbSMapInfoPresenter mapInfoPresenter) {
	    
	    super(resources, mapInfoPresenter);
	    
	  }
	  
	  @Override
	  public List<MapLayer> getLayers() {
	    
	    return new RetrieveVars().getLayers("HbS");
	  }
	  
	  @Override
	  public void start(AcceptsOneWidget panel, EventBus eventBus) {
		    super.start(panel, eventBus);
		    
		    eventBus.addHandler(HbSSelectedEvent.TYPE,
		        new HbSSelectedEvent.Handler() {
		          @Override
		          public void onCountrySelected(HbSSelectedEvent requestEvent) {
		            EntityProxy extent = requestEvent.getCountry();
		            mapView.zoomToBounds(extent);
		          }
		        });
		  }
	  

	}

