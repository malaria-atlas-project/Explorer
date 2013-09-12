package uk.ac.ox.map.explorer.client.map.presenter;

import java.util.List;

import uk.ac.ox.map.explorer.client.event.G6PDSelectedEvent;
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

public class G6PDMapPresenter extends BaseMapPresenter {
	
//	  private static final String G6PD_AVAILABLE_DISPLAY = "Explorer:g6pd_data_available";
//	  private static final String G6PD_UNAVAILABLE_DISPLAY = "Explorer:g6pd_data_unavailable";
	  
	  
	  @Inject
	  public G6PDMapPresenter(ResourceBundle resources,
	      G6PDMapInfoPresenter mapInfoPresenter) {
	    
	    super(resources, mapInfoPresenter);
	    
	  }
	  
	  @Override
	  public List<MapLayer> getLayers() {
	    
	    return new RetrieveVars().getLayers("G6PD");
	  }
	  
	  @Override
	  public void start(AcceptsOneWidget panel, EventBus eventBus) {
		    super.start(panel, eventBus);
		    
		    eventBus.addHandler(G6PDSelectedEvent.TYPE,
		        new G6PDSelectedEvent.Handler() {
		          @Override
		          public void onCountrySelected(G6PDSelectedEvent requestEvent) {
		            EntityProxy extent = requestEvent.getCountry();
		            mapView.zoomToBounds(extent);
		          }
		        });
		  }
	  
//	  public void start(AcceptsOneWidget panel, EventBus eventBus) {
//	    super.start(panel, eventBus);
//	    
//	    eventBus.addHandler(G6PDSelectedEvent.TYPE,
//	        new G6PDSelectedEvent.Handler() {
//
//	    	  @Override
//	          public void onCountrySelected(G6PDSelectedEvent requestEvent) {
//	              EntityProxy extent = requestEvent.getCountry();
//	              if (extent == null) {
//	                mapView.zoomToBounds(new Double(-180), new Double(-90),
//	                    new Double(180), new Double(90));
//	                
//	                mapView.setCql(G6PD_AVAILABLE_DISPLAY, null);
//	                mapView.setCql(G6PD_UNAVAILABLE_DISPLAY, null);
//	              } 
//	          else {
//	                mapView.zoomToBounds(extent);
//	                
//	                mapView.setCql("Explorer:countryborders",
//	                    "COUNTRY_ID=" + requestEvent.getCountry().getId());
//
//	              }
//	            }
//	        });
//	  }
	  
	}

