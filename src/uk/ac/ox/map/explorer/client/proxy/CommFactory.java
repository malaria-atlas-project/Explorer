package uk.ac.ox.map.explorer.client.proxy;



import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface CommFactory extends AutoBeanFactory {
  
  AutoBean<MapLayerList> mapLayerList();
  
  AutoBean<MapLayer> mapLayer();

}
