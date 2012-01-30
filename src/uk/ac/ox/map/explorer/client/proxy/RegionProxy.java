package uk.ac.ox.map.explorer.client.proxy;


import uk.ac.ox.map.domain.Region;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Region.class)
public interface RegionProxy extends EntityProxy {
  
  Long getId();

  void setId(Long id);
  
  String getName();
  
  void setName(String name);

}
