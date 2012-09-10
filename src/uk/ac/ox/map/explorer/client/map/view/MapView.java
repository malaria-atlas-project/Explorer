package uk.ac.ox.map.explorer.client.map.view;

import java.util.HashMap;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.event.MapClickListener;
import org.gwtopenmaps.openlayers.client.event.MapMoveEndListener;
import org.gwtopenmaps.openlayers.client.layer.LayerOptions;
import org.gwtopenmaps.openlayers.client.layer.TransitionEffect;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import uk.ac.ox.map.explorer.client.map.presenter.BaseMapPresenter;
import uk.ac.ox.map.explorer.client.proxy.EntityProxy;
import uk.ac.ox.map.explorer.client.proxy.MapLayer;

import com.google.gwt.user.client.ui.Composite;

/**
 * 
 * 
 * @author will
 */
public class MapView extends Composite {

  private MapWidget mapWidget;
  private Map map;

  private BaseMapPresenter listener;
  
  private String gwcUrl = "http://localhost:8080/geoserver/gwc/service/wms";
  private String wmsUrl = "http://localhost:8080/geoserver/wms";
  
  private java.util.Map<String, WMS> wmsLayerMap = new HashMap<String, WMS>();

  public MapView() {

    MapOptions defaultMapOptions = new MapOptions();
    mapWidget = new MapWidget("100%", "100%", defaultMapOptions);
    map = mapWidget.getMap();

    initWidget(mapWidget);
   
    {
	    WMSParams params = new WMSParams();
      params.setLayers("Explorer:bluemarble");
      params.setTransparent(true);
	    WMS bm = new WMS("Blue marble", gwcUrl, params);
	    LayerOptions options = new LayerOptions();
	    options.setTransitionEffect(TransitionEffect.RESIZE);
	    bm.addOptions(options);
	    bm.setIsBaseLayer(true);
	    map.addLayer(bm);
	    map.setBaseLayer(bm);
    }
    
//    map.addControl(new MousePosition());
//    map.addControl(new LayerSwitcher());
    
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
  
  public void setListener(BaseMapPresenter baseMapPresenter){
    this.listener = baseMapPresenter;
  }

  public void addWmsLayer(MapLayer mapLayer, boolean isTransparent) {
    
      String url = mapLayer.getUseWebCache() ? gwcUrl : wmsUrl;
    
      WMSParams params = new WMSParams();
      params.setLayers(mapLayer.getWmsLayerName());
      params.setTransparent(isTransparent);
      
      WMS wms = new WMS(mapLayer.getWmsLayerName(), url, params);
      wms.setIsBaseLayer(false);
      if (mapLayer.getWmsLayerName().equals("Explorer:countryborders") || mapLayer.getWmsLayerName().equals("Explorer:pr_mean")) {
    	LayerOptions options = new LayerOptions();
    	options.setTransitionEffect(TransitionEffect.RESIZE);
    	wms.addOptions(options);
      }
      map.addLayer(wms);
      
      wmsLayerMap.put(mapLayer.getWmsLayerName(), wms);
      
  }
  
  /**
   * Zoom to smallest extent that will enclose this bounding box
   * 
   * @param minx
   * @param miny
   * @param maxx
   * @param maxy
   */
  public void zoomToBounds(Double minx, Double miny, Double maxx, Double maxy) {
    if (minx == null || miny == null || maxx == null || maxy == null) {
      return;
    }
    Bounds bounds = new Bounds(minx, miny, maxx, maxy);
    map.zoomToExtent(bounds);
  }

//  public void setSites(Collection<SiteProxy> sites) {
//
//    if (markers == null) {
//      markers = new Markers("Sites");
//      map.addLayer(markers);
//    }
//
//    for (SiteProxy site : sites) {
//
//      LonLat ll = new LonLat(site.getLongitude().doubleValue(), site.getLatitude().doubleValue());
//      if (marker == null) {
//        marker = new Marker(ll);
//        markers.addMarker(marker);
//      } else {
//        markers.removeMarker(marker);
//        marker.setLonLat(ll);
//        markers.addMarker(marker);
//      }
//
//    }
//
//  }

  public void zoomToBounds(EntityProxy extent) {
    System.out.println(extent.getMinX());
    zoomToBounds(extent.getMinX(), extent.getMinY(), extent.getMaxX(), extent.getMaxY());
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
  public void toggleLayer(String wmslayerName, boolean active) {
    
    WMS lyr = wmsLayerMap.get(wmslayerName);
    if (lyr != null) {
      lyr.setIsVisible(active);
    }
  }
  
  /**
   * Updates the CQL filter for layer.
   * 
   * @param wmsLayerName
   * @param cql
   */
  public void setCql(String wmsLayerName, String cql) {
    
    WMS lyr = wmsLayerMap.get(wmsLayerName);
    if (lyr != null) {
	      WMSParams params = new WMSParams();
	      params.setParameter("cql_filter", cql);
	      lyr.mergeNewParams(params);
    }
    
  }

}
