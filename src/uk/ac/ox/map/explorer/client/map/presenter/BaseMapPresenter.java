package uk.ac.ox.map.explorer.client.map.presenter;

import java.util.List;

import uk.ac.ox.map.explorer.client.base.view.BaseView;
import uk.ac.ox.map.explorer.client.base.view.CompositeMapView;
import uk.ac.ox.map.explorer.client.event.ExtentChangeRequestEvent;
import uk.ac.ox.map.explorer.client.event.LayerChangeRequestEvent;
import uk.ac.ox.map.explorer.client.map.view.MapView;
import uk.ac.ox.map.explorer.client.proxy.ExtentProxy;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * 
 * @author will
 * 
 */

public abstract class BaseMapPresenter extends AbstractActivity {

  protected final ResourceBundle resources;

  protected final MapView mapView;

  @Inject
  private KeyPresenter keyPresenter;

  @Inject
  private MapInfoPresenter mapInfoPresenter;

  private boolean isIntialized = false;

  private final CompositeMapView compositeMapView;

  public BaseMapPresenter(ResourceBundle resources) {

    this.mapView = new MapView();

    this.resources = resources;

    this.compositeMapView = new CompositeMapView(mapView);

  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    panel.setWidget(compositeMapView);

    if (!isIntialized) {
      
      compositeMapView.getMapPanel().setWidget(mapView);

      mapView.setListener(this);

      List<MapLayer> layers = addLayers();

      keyPresenter.setLayers(layers);
    }
    
    keyPresenter.start(compositeMapView.getKeyPanel(), eventBus);

    mapInfoPresenter.start(compositeMapView.getInfoPanel(), eventBus);

    eventBus.addHandler(ExtentChangeRequestEvent.TYPE, new ExtentChangeRequestEvent.Handler() {
      @Override
      public void onExtentChangeRequest(ExtentChangeRequestEvent requestEvent) {
        ExtentProxy extent = requestEvent.getExtent();
        mapView.zoomToBounds(extent);
      }
    });

    eventBus.addHandler(LayerChangeRequestEvent.TYPE, new LayerChangeRequestEvent.Handler() {
      public void onExtentChangeRequest(LayerChangeRequestEvent layerChangeEvent) {
        mapView.toggleLayer(layerChangeEvent.getLayerName(), layerChangeEvent.isActive());
      }
    });

    isIntialized = true;

  }

  public void fireMapClicked(double lon, double lat) {
    System.out.println(lon);
    System.out.println(lat);
  }

  public void fireMapMoveEnd() {
    mapInfoPresenter.updateMapInfo(mapView.getExtent());
  }

  public abstract List<MapLayer> addLayers();
}
