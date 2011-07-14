package uk.ac.ox.map.explorer.client.map.presenter;

import org.gwtopenmaps.openlayers.client.Bounds;

import uk.ac.ox.map.explorer.client.map.view.MapInfoView;
import uk.ac.ox.map.explorer.client.map.view.MapView;
import uk.ac.ox.map.explorer.client.rpc.MapInfo;
import uk.ac.ox.map.explorer.client.rpc.MapInfoServiceAsync;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class MapInfoPresenter extends AbstractActivity {
  
  private MapInfoView display;
  private MapInfoServiceAsync rpcService;
  private MapView mapView;

  @Inject
  public MapInfoPresenter(MapView mapView, MapInfoView mapInfoView, MapInfoServiceAsync rpcService, EventBus eventBus) {
    this.display = mapInfoView;
    this.rpcService = rpcService;
    this.mapView = mapView;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(display);
    display.setListener(this);
    updateMapInfo(mapView.getMaxExtent());
  }
  
  public void updateMapInfo(Bounds bounds) {
    
    rpcService.getExtentInfo(bounds.getLowerLeftX(), bounds.getLowerLeftY(), bounds.getUpperRightX(), bounds.getUpperRightY(), new AsyncCallback<String>() {

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

  public void onUpdateClicked() {
    updateMapInfo(mapView.getExtent());
  }
  
  
  

}
