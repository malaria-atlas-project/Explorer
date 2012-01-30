package uk.ac.ox.map.explorer.client.map.presenter;

import java.util.ArrayList;
import java.util.List;

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
import com.google.inject.Provider;

/**
 * 
 * @author will
 * 
 */

public abstract class BaseMapPresenter extends AbstractActivity {

  protected ResourceBundle resources;
  private CompositeMapView compositeMapView;
  protected MapView mapView;

  @Inject
  private Provider<KeyPresenter> keyProvider;

  @Inject
  private Provider<MapInfoPresenter> infoProvider;

  private MapInfoPresenter infoPresenter;

  private boolean isIntialized = false;

  @Inject
  public BaseMapPresenter(CompositeMapView compositeMapView, MapView mapView, ResourceBundle resources) {
    this.compositeMapView = compositeMapView;

    this.mapView = mapView;

    this.resources = resources;

  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    
    panel.setWidget(compositeMapView);

    compositeMapView.getMapPanel().setWidget(mapView);

    if (!isIntialized) {

      KeyPresenter keyPresenter = keyProvider.get();
      keyPresenter.start(compositeMapView.getKeyPanel(), eventBus);

      this.infoPresenter = infoProvider.get();
      infoPresenter.start(compositeMapView.getInfoPanel(), eventBus);

      mapView.setListener(this);

      List<MapLayer> layers = addLayers();

      keyPresenter.setLayers(layers);

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

    }
    
    isIntialized = true;

  }

  public void fireMapClicked(double lon, double lat) {
    System.out.println(lon);
    System.out.println(lat);
  }

  public void fireMapMoveEnd() {
    infoPresenter.updateMapInfo(mapView.getExtent());
  }

  public abstract List<MapLayer> addLayers();
}
