package uk.ac.ox.map.explorer.client.proxy;

import uk.ac.ox.map.domain.Anopheline;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Anopheline.class)
public interface AnophelineProxy extends EntityProxyRepr {

  Long getId();

  void setId(Long id);
  
//  RegionProxy getRegion();
//  
//  void setRegion(RegionProxy proxy);

  String getName();

  void setName(String name);

//  Boolean getPfEndemic();
//
//  void setPfEndemic(Boolean pfEndemic);
//
//  Boolean getPvEndemic();
//
//  void setPvEndemic(Boolean pvEndemic);
//
//  String getContinent();
//
//  void setContinent(String continent);

  ExtentProxy getExtent();
  
  void setExtent(ExtentProxy extent);

}