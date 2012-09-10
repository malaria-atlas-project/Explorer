package uk.ac.ox.map.explorer.client.map.presenter;

import org.gwtopenmaps.openlayers.client.Bounds;

import uk.ac.ox.map.explorer.client.event.AnophelineSelectedEvent;
import uk.ac.ox.map.explorer.client.map.view.MapInfoView;
import uk.ac.ox.map.explorer.client.proxy.AnophelineProxy;
import uk.ac.ox.map.explorer.client.rpc.MapInfo;
import uk.ac.ox.map.explorer.client.rpc.MapInfoServiceAsync;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class AnophelesMapInfoPresenter extends BaseMapInfoPresenter {
  
  private AnophelineProxy anoProxy = null;
  private Boolean presenceProxy = true;
  private Boolean absenceProxy = true;
  private final String PRESENCE_LAYER = "Explorer:anopheline_display_presence";
  private final String ABSENCE_LAYER = "Explorer:anopheline_display_absence";
  
  @Inject
  public AnophelesMapInfoPresenter(MapInfoView mapInfoView, MapInfoServiceAsync rpcService, EventBus eventBus) {
    super(mapInfoView, rpcService, eventBus);
  }

  
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    super.start(panel, eventBus);
    
    eventBus.addHandler(AnophelineSelectedEvent.TYPE, new AnophelineSelectedEvent.Handler() {
      
      @Override
      public void onAnophelineSelected(AnophelineSelectedEvent requestEvent) {
        anoProxy = requestEvent.getAnopheline();
      }
    });
  }
  

  public void updateMapInfo(Bounds bounds) {
    
    Long anoId = null;
    if (anoProxy != null) {
      anoId = anoProxy.getId();
    }
    
    rpcService.getAnophelineExtentInfo(presenceProxy, absenceProxy, anoId, bounds.getLowerLeftX(), bounds.getLowerLeftY(), bounds.getUpperRightX(), bounds.getUpperRightY(), new AsyncCallback<String>() {

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
      if (toggledLayerName.equals(PRESENCE_LAYER))
      {
    	  presenceProxy = !presenceProxy;
      } else if (toggledLayerName.equals(ABSENCE_LAYER)) {
    	  absenceProxy = !absenceProxy;
      }
      updateMapInfo(extent);
  }


}
