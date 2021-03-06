package uk.ac.ox.map.explorer.client.map.presenter;

import org.gwtopenmaps.openlayers.client.Bounds;

import uk.ac.ox.map.explorer.client.map.view.MapInfoView;
import uk.ac.ox.map.explorer.client.rpc.MapInfoServiceAsync;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public abstract class BaseMapInfoPresenter extends AbstractActivity {
  
  protected MapInfoView display;
  protected MapInfoServiceAsync rpcService;
  
  public BaseMapInfoPresenter(MapInfoView mapInfoView,
      MapInfoServiceAsync rpcService, EventBus eventBus) {
    display = mapInfoView;
    this.rpcService = rpcService;
  }
  
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(display);
    updateMapInfo(new Bounds(-180, -90, 180, 90));
  }
  
  public abstract void updateMapInfo(Bounds bounds);
  
  public abstract void updateMapInfo(String toggledLayerName, Bounds extent);
  
}
