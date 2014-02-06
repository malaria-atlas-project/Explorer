package uk.ac.ox.map.explorer.client.map.presenter;

import org.gwtopenmaps.openlayers.client.Bounds;

import uk.ac.ox.map.explorer.client.event.DuffySelectedEvent;
import uk.ac.ox.map.explorer.client.map.view.MapInfoView;
import uk.ac.ox.map.explorer.client.proxy.CountryProxy;
import uk.ac.ox.map.explorer.client.rpc.MapInfo;
import uk.ac.ox.map.explorer.client.rpc.MapInfoServiceAsync;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * @author andy
 */

public class DuffyMapInfoPresenter extends BaseMapInfoPresenter {

	private Boolean availableProxy = true;
	  private Boolean unavailableProxy = true;
	  private final String AVAILABLE_LAYER = "Explorer:duffy_available";
	  private final String UNAVAILABLE_LAYER = "Explorer:duffy_unavailable";
	  
	  @Inject
	  public DuffyMapInfoPresenter(MapInfoView mapInfoView,
	      MapInfoServiceAsync rpcService, EventBus eventBus) {
	    super(mapInfoView, rpcService, eventBus);
	    
	    
	  }

	  
	  @Override
	  public void updateMapInfo(Bounds bounds) {
	
	    
	    rpcService.getDuffyExtentInfo(availableProxy, unavailableProxy,
	        bounds.getLowerLeftX(), bounds.getLowerLeftY(),
	        bounds.getUpperRightX(), bounds.getUpperRightY(),
	        new AsyncCallback<String>() {
	          
	          @Override
	          public void onFailure(Throwable caught) {
	            // TODO Application wide AsyncCallback
	          }
	          
	          @Override
	          public void onSuccess(String result) {
	            MapInfo mi = MapInfo.fromJson(result);
	            display.showMapInfo(mi);
	          }
	        });
	  }
	  
	  @Override
	  public void updateMapInfo(String toggledLayerName, Bounds extent) {
	    if (toggledLayerName.equals(AVAILABLE_LAYER)) {
	      availableProxy = !availableProxy;
	    } else if (toggledLayerName.equals(UNAVAILABLE_LAYER)) {
	      unavailableProxy = !unavailableProxy;
	    }
	    updateMapInfo(extent);
	    
	  }
}
