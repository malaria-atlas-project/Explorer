package uk.ac.ox.map.explorer.client.map.presenter;


import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.base.view.CompositeMapView;
import uk.ac.ox.map.explorer.client.event.ExtentChangeRequestEvent;
import uk.ac.ox.map.explorer.client.event.LayerChangeRequestEvent;
import uk.ac.ox.map.explorer.client.map.view.MapView;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;
import uk.ac.ox.map.request.client.proxy.ExtentProxy;

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

public class MapPresenter extends AbstractActivity {
  
  private ResourceBundle resources;
  private CompositeMapView compositeMapView;
  private MapView mapView;
  
  @Inject
  private Provider<KeyPresenter> keyProvider;
  
  @Inject
  private Provider<MapInfoPresenter> infoProvider;
  private MapInfoPresenter infoPresenter;

  @Inject
  public MapPresenter(CompositeMapView compositeMapView, MapView mapView, ResourceBundle resources) {
    this.compositeMapView = compositeMapView;
    
    this.mapView = mapView;
    
    this.resources = resources;
    
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(compositeMapView);
    
    compositeMapView.getMapPanel().setWidget(mapView);
    
    KeyPresenter keyPresenter = keyProvider.get();
    keyPresenter.start(compositeMapView.getKeyPanel(), eventBus);
    
    this.infoPresenter = infoProvider.get();
    infoPresenter.start(compositeMapView.getInfoPanel(), eventBus);
    
    mapView.setListener(this);
    
    String gwcUrl = "http://map1.zoo.ox.ac.uk/geoserver/gwc/service/wms";
    String wmsUrl = "http://map1.zoo.ox.ac.uk/geoserver/wms";
    
    List<MapLayer> layers = new ArrayList<MapLayer>();
    layers.add(new MapLayer("Base:pr_mean", "2010 endemicity", resources.endemicityScale()));
    layers.add(new MapLayer("Static:admin0", "Country boundaries", resources.countryBoundary()));
    layers.add(new MapLayer("Base:pf_colour_public", "Pf points", resources.prPoint()));
    
    for (MapLayer mapLayer : layers) {
      mapView.addWmsLayer(mapLayer.getName(), gwcUrl, mapLayer.getWmsLayerName(), true);
    }
    
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

  public void fireMapClicked(double lon, double lat) {
    System.out.println(lon);
    System.out.println(lat);
  }

  public void fireMapMoveEnd() {
    infoPresenter.updateMapInfo(mapView.getExtent());
  }
}
