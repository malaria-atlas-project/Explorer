package uk.ac.ox.map.explorer.client.map.presenter;


import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.event.ExtentChangeRequestEvent;
import uk.ac.ox.map.explorer.client.map.view.MapView;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;
import uk.ac.ox.map.request.client.proxy.ExtentProxy;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * 
 * @author will
 *
 */

public class MapPresenter extends AbstractActivity {
  

  private MapView mapView;
  private ResourceBundle resources;
  private KeyPresenter kp;

  @Inject
  public MapPresenter(MapView map, ResourceBundle resources, KeyPresenter kp) {
    this.mapView = map;
    this.resources = resources;
    this.kp = kp;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(mapView);
    mapView.setListener(this);
    
    String gwcUrl = "http://map1.zoo.ox.ac.uk/geoserver/gwc/service/wms";
    String wmsUrl = "http://map1.zoo.ox.ac.uk/geoserver/wms";
    
    List<MapLayer> layers = new ArrayList<MapLayer>();
    layers.add(new MapLayer("Base:pr_mean", "2010 endemicity", resources.endemicityScale()));
    layers.add(new MapLayer("Static:admin0", "Country boundaries", resources.endemicityScale()));
    layers.add(new MapLayer("Base:pf_colour_public", "Pf points", resources.successIcon()));
    
    for (MapLayer mapLayer : layers) {
      mapView.addWmsLayer(mapLayer.getDescription(), gwcUrl, mapLayer.getName(), true);
    }
    kp.setLayers(layers);
    
    /*
    mapView.addWmsLayer("2010 endemicity", gwcUrl, "Base:pr_mean", true);
    
    mapView.addWmsLayer("admin0", gwcUrl, "Static:admin0", true);
    
    mapView.addWmsLayer("Pr points", gwcUrl, "Base:pf_colour_public", true);
    */
    
    eventBus.addHandler(ExtentChangeRequestEvent.TYPE, new ExtentChangeRequestEvent.Handler() {
      @Override
      public void onExtentChangeRequest(ExtentChangeRequestEvent requestEvent) {
        ExtentProxy extent = requestEvent.getExtent();
        mapView.zoomToBounds(extent);
      }
    });
    
  }

  public void fireMapClicked(double lon, double lat) {
    System.out.println(lon);
    System.out.println(lat);
  }
}
