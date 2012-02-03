package uk.ac.ox.map.explorer.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import uk.ac.ox.map.domain.EMF;
import uk.ac.ox.map.domain.ExplorerLayer;
import uk.ac.ox.map.domain.ExplorerPerspective;
import uk.ac.ox.map.explorer.client.proxy.CommFactory;
import uk.ac.ox.map.explorer.client.proxy.MapLayer;
import uk.ac.ox.map.explorer.client.proxy.MapLayerList;
import uk.ac.ox.map.request.server.SimpleDao;


import com.google.inject.Inject;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.autobean.vm.AutoBeanFactorySource;

/**
 * 
 * 
 * http://code.google.com/p/google-web-toolkit/wiki/AutoBean
 */
public class LayerAutoBeanEncoder {

  private CommFactory autoBeanFactory = AutoBeanFactorySource.create(CommFactory.class);

  private MapLayerList layers;
  
  public String getLayerPayload(List<ExplorerLayer> explorerLayers) {
    /*
     * Map layers
     */
    layers = autoBeanFactory.mapLayerList().as();
    List<MapLayer> mapLayers = new ArrayList<MapLayer>();
    layers.setLayerList(mapLayers);
    
    for (ExplorerLayer explorerLayer : explorerLayers) {
      mapLayers.add(getLayer(explorerLayer));
    }
    
    String stringBeans = getPayloadFromAutoBean(layers);
    return stringBeans;
  }
  
  private <T> String getPayloadFromAutoBean(T delegate) {
    return AutoBeanCodex.encode(AutoBeanUtils.getAutoBean(delegate)).getPayload();
  }

  private MapLayer getLayer(ExplorerLayer explorerLayer) {
    
    MapLayer mapLayer = autoBeanFactory.mapLayer().as();
    mapLayer.setName(explorerLayer.getName());
    mapLayer.setWmsLayerName(explorerLayer.getLayers());
    mapLayer.setInfoText(explorerLayer.getInfoText());
    mapLayer.setImageUrl(explorerLayer.getImage());
//    mapLayer.setIsVisible(explorerLayer.getIsVisible());
    
    return mapLayer;
  }

}
