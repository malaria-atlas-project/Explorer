package uk.ac.ox.map.explorer.client.map.view;

import java.util.Collection;

import org.gwtopenmaps.openlayers.client.Bounds;
import org.gwtopenmaps.openlayers.client.LonLat;
import org.gwtopenmaps.openlayers.client.Map;
import org.gwtopenmaps.openlayers.client.MapOptions;
import org.gwtopenmaps.openlayers.client.MapWidget;
import org.gwtopenmaps.openlayers.client.Marker;
import org.gwtopenmaps.openlayers.client.control.LayerSwitcher;
import org.gwtopenmaps.openlayers.client.control.MousePosition;
import org.gwtopenmaps.openlayers.client.event.MapClickListener;
import org.gwtopenmaps.openlayers.client.layer.Markers;
import org.gwtopenmaps.openlayers.client.layer.WMS;
import org.gwtopenmaps.openlayers.client.layer.WMSParams;

import uk.ac.ox.map.request.client.proxy.ExtentProxy;
import uk.ac.ox.map.request.client.proxy.SiteProxy;

import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Singleton;

@Singleton
public class MapView extends Composite {

  private MapWidget mapWidget;
  private Map map;

  private String gwcUrl = "http://map1.zoo.ox.ac.uk/geoserver/gwc/service/wms";
  private String wmsUrl = "http://map1.zoo.ox.ac.uk/geoserver/wms";
  private String nasaUrl = "http://wms.jpl.nasa.gov/wms.cgi?";
  private WMS admin;
  private WMS prPoints;
  private Markers markers;
  private Marker marker;

  public MapView() {

    MapOptions defaultMapOptions = new MapOptions();
    mapWidget = new MapWidget("100%", "100%", defaultMapOptions);
    map = mapWidget.getMap();

    initWidget(mapWidget);

    
    WMS a0 = addWmsLayer2("admin0", gwcUrl, "Base:admin0", false);
    a0.setIsBaseLayer(true);
    map.addLayer(a0);
    
    WMS bm = addWmsLayer2("Blue marble", "http://disc1.gsfc.nasa.gov/daac-bin/wms_ogc?LAYER=AIRIBRAD_DAY&", "bluemarble", true);
    bm.setIsBaseLayer(true);
    map.addLayer(bm);
    map.setBaseLayer(bm);
    
    WMS pfPr = addWmsLayer2("2010 endemicity", gwcUrl, "Base:pr_mean", true);
    pfPr.setIsBaseLayer(false);
    map.addLayer(pfPr);
    
    WMS pfPoints = addWmsLayer2("Pr points", gwcUrl, "Base:pf_colour_public", true);
    pfPoints.setIsBaseLayer(false);
    map.addLayer(pfPoints);
    

    map.addControl(new LayerSwitcher());

    // map.zoomToExtent(new Bounds(-180.021,-89.979,188.479,90.021));
    map.zoomTo(1);

    map.addMapClickListener(new MapClickListener() {

      @Override
      public void onClick(MapClickEvent mapClickEvent) {
        LonLat lonlat = mapClickEvent.getLonLat();
        System.out.println(lonlat.hashCode());
      }
    });
  }

  public WMS addWmsLayer2(String description, String url, String layer, boolean isTransparent) {
      WMSParams params = new WMSParams();
      params.setLayers(layer);
      params.setIsTransparent(isTransparent);

      WMS wms = new WMS(description, url, params);
      return wms;
  }
  
  public void addAVHRR() {
    WMSParams params = new WMSParams();
    params.setLayers("AVHRR_CLIM_M");

    WMS layer = new WMS("OpenLayers WMS", "http://neowms.sci.gsfc.nasa.gov/wms/wms", params);
    map.addLayer(layer);
    
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

}
