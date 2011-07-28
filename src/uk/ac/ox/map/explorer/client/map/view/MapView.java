package uk.ac.ox.map.explorer.client.map.view;

import java.util.Collection;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Marker;
import org.gwtopenmaps.openlayers.client.Size;
import org.gwtopenmaps.openlayers.client.event.MapClickListener;
import org.gwtopenmaps.openlayers.client.event.MapMoveEndListener;
import org.gwtopenmaps.openlayers.client.layer.Layer;
import org.gwtopenmaps.openlayers.client.layer.Markers;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import uk.ac.ox.map.explorer.client.map.presenter.MapPresenter;
import uk.ac.ox.map.request.client.proxy.ExtentProxy;
import uk.ac.ox.map.request.client.proxy.SiteProxy;

import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Singleton;

@Singleton
public class MapView extends Composite {

  private MapWidget mapWidget;
  private Map map;

  private Markers markers;
  private Marker marker;
  private MapPresenter listener;

  public MapView() {

    MapOptions defaultMapOptions = new MapOptions();
//    defaultMapOptions.setTileSize(new Size(512, 512));
    mapWidget = new MapWidget("100%", "100%", defaultMapOptions);
    map = mapWidget.getMap();

    initWidget(mapWidget);
   
    String gwcUrl = "http://map1.zoo.ox.ac.uk/geoserver/gwc/service/wms";
    {
	    WMSParams params = new WMSParams();
      params.setLayers("Base:bmng");
      params.setIsTransparent(true);
	    WMS bm = new WMS("Blue marble", gwcUrl, params);
	    bm.setIsBaseLayer(true);
	    map.addLayer(bm);
	    map.setBaseLayer(bm);
    }
    /*
    */
    
//    map.addControl(new MousePosition());
    
    map.zoomTo(2);

    map.addMapClickListener(new MapClickListener() {

      @Override
      public void onClick(MapClickEvent mapClickEvent) {
        LonLat lonlat = mapClickEvent.getLonLat();
        if (listener != null) {
          listener.fireMapClicked(lonlat.lon(), lonlat.lat());
        }
      }
    });
    
    map.addMapMoveEndListener(new MapMoveEndListener() {
      @Override
      public void onMapMoveEnd(MapMoveEndEvent eventObject) {
        if (listener != null) {
          listener.fireMapMoveEnd();
        }
      }
    });
  }
  
  public void setListener(MapPresenter listener){
    this.listener = listener;
  }

  public void addWmsLayer(String name, String url, String layer, boolean isTransparent) {
      WMSParams params = new WMSParams();
      params.setLayers(layer);
      params.setIsTransparent(isTransparent);
      
      
      WMS wms = new WMS(name, url, params);
      wms.setIsBaseLayer(false);
      map.addLayer(wms);
  }
  

  public void zoomToBounds(Double minx, Double miny, Double maxx, Double maxy) {
    if (minx == null || miny == null || maxx == null || maxy == null) {
      return;
    }
    Bounds bounds = new Bounds(minx, miny, maxx, maxy);
    map.zoomToExtent(bounds);
  }

  public void setSites(Collection<SiteProxy> sites) {

    if (markers == null) {
      markers = new Markers("Sites");
      map.addLayer(markers);
    }

    for (SiteProxy site : sites) {
      // System.out.println("site");

      LonLat ll = new LonLat(site.getLongitude().doubleValue(), site.getLatitude().doubleValue());
      if (marker == null) {
        marker = new Marker(ll);
        markers.addMarker(marker);
      } else {
        markers.removeMarker(marker);
        marker.setLonLat(ll);
        markers.addMarker(marker);
      }

    }

  }

  public void zoomToBounds(ExtentProxy extent) {
    zoomToBounds(extent.getMinx(), extent.getMiny(), extent.getMaxx(), extent.getMaxy());
  }

  public Bounds getExtent() {
    return map.getExtent();
  }

  public Bounds getMaxExtent() {
    return map.getMaxExtent();
  }

  /**
   * Toggles a layer based on it's name. No custom layer cache used. 
   * @param layerName
   * @param active
   */
  public void toggleLayer(String layerName, boolean active) {
    System.out.println(layerName);
    System.out.println(active);
    
    Layer lyr = map.getLayerByName(layerName);
    if (lyr != null) {
      lyr.setIsVisible(active);
    }
  }

}
