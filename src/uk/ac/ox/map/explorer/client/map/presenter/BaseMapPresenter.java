package uk.ac.ox.map.explorer.client.map.presenter;

import java.util.List;

import uk.ac.ox.map.explorer.client.base.view.CompositeMapView;
import uk.ac.ox.map.explorer.client.event.ToggleLayerRequestEvent;
import uk.ac.ox.map.explorer.client.map.view.MapView;
import uk.ac.ox.map.explorer.client.proxy.MapLayer;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * Manages all map related activities. Subclasses must provide a list of layers
 * to add to the {@link MapView}
 * 
 * @author will
 */
public abstract class BaseMapPresenter extends AbstractActivity {

  protected final ResourceBundle resources;

  protected final MapView mapView;

  @Inject
  private KeyPresenter keyPresenter;

  private boolean isIntialized = false;

  private final CompositeMapView compositeMapView;

  private final BaseMapInfoPresenter mapInfoPresenter;

  public BaseMapPresenter(ResourceBundle resources, BaseMapInfoPresenter mapInfoPresenter) {

    this.mapView = new MapView();

    this.resources = resources;

    this.compositeMapView = new CompositeMapView(mapView);

    this.mapInfoPresenter = mapInfoPresenter;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {

    panel.setWidget(compositeMapView);

    if (!isIntialized) {

      compositeMapView.getMapPanel().setWidget(mapView);

      mapView.setListener(this);

      /*
       * Get layers (from subclass) and add to map and key
       */
      List<MapLayer> layers = getLayers();
      for (MapLayer mapLayer : layers) {
        mapView.addWmsLayer(mapLayer, true);
      }

      keyPresenter.setLayers(layers);
    }

    keyPresenter.start(compositeMapView.getKeyPanel(), eventBus);

    mapInfoPresenter.start(compositeMapView.getInfoPanel(), eventBus);


    eventBus.addHandler(ToggleLayerRequestEvent.TYPE, new ToggleLayerRequestEvent.Handler() {
      public void onLayerChangeRequest(ToggleLayerRequestEvent layerChangeEvent) {
        mapView.toggleLayer(layerChangeEvent.getLayerName(), layerChangeEvent.isActive());
      }
    });

    isIntialized = true;
  }

  /**
   * Called by {@link MapView} when map has been clicked.
   */
  public void fireMapClicked(double lon, double lat) {

  }

  /**
   * Called by {@link MapView} when map has moved.
   */
  public void fireMapMoveEnd() {
    mapInfoPresenter.updateMapInfo(mapView.getExtent());
  }

  public abstract List<MapLayer> getLayers();
}
