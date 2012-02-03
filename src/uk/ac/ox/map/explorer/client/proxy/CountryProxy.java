package uk.ac.ox.map.explorer.client.proxy;

import uk.ac.ox.map.domain.Country;
import uk.ac.ox.map.request.server.CountryLocator;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Country.class, locator = CountryLocator.class)
public interface CountryProxy extends NamedProxy {

  String getId();

  void setId(String id);
  
  RegionProxy getRegion();
  
  void setRegion(RegionProxy proxy);

  String getName();

  void setName(String name);

  Boolean getPfEndemic();

  void setPfEndemic(Boolean pfEndemic);

  Boolean getPvEndemic();

  void setPvEndemic(Boolean pvEndemic);

  String getContinent();

  void setContinent(String continent);

  ExtentProxy getExtent();
  
  void setExtent(ExtentProxy extent);

}
