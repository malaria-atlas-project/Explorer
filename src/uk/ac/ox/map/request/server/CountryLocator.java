package uk.ac.ox.map.request.server;

import uk.ac.ox.map.domain.Country;
import uk.ac.ox.map.domain.EMF;

import com.google.web.bindery.requestfactory.shared.Locator;

public class CountryLocator extends Locator<Country, String> {
  
  SimpleDao dao = new SimpleDao(EMF.get());

  @Override
  public Country create(Class<? extends Country> clazz) {
    return new Country();
  }

  @Override
  public Country find(Class<? extends Country> clazz, String id) {
    return dao.find(clazz, id);
  }

  @Override
  public Class<Country> getDomainType() {
    return Country.class;
  }

  @Override
  public String getId(Country domainObject) {
    return domainObject.getId();
  }

  @Override
  public Class<String> getIdType() {
    return String.class;
  }

  @Override
  public Object getVersion(Country domainObject) {
    return 0;
  }
  
}