package uk.ac.ox.map.explorer.client.map.presenter;

import org.gwtopenmaps.openlayers.client.Bounds;

import uk.ac.ox.map.explorer.client.map.view.MapInfoView;
import uk.ac.ox.map.explorer.client.rpc.MapInfo;
import uk.ac.ox.map.explorer.client.rpc.MapInfoServiceAsync;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class CountryMapInfoPresenter extends BaseMapInfoPresenter {
  
  private Boolean availableProxy = true;
  private Boolean unavailableProxy = true;
  private final String AVAILABLE_LAYER = "Explorer:pr_display_available";
  private final String UNAVAILABLE_LAYER = "Explorer:pr_display_unavailable";
  
  @Inject
  public CountryMapInfoPresenter(MapInfoView mapInfoView,
      MapInfoServiceAsync rpcService, EventBus eventBus) {
    super(mapInfoView, rpcService, eventBus);
  }
  
  @Override
  public void updateMapInfo(Bounds bounds) {
    
    rpcService.getPrExtentInfo(availableProxy, unavailableProxy,
        bounds.getLowerLeftX(), bounds.getLowerLeftY(),
        bounds.getUpperRightX(), bounds.getUpperRightY(),
        new AsyncCallback<String>() {
          
          @Override
          public void onFailure(Throwable caught) {
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
