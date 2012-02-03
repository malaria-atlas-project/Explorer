package uk.ac.ox.map.explorer.server;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import uk.ac.ox.map.domain.Country;
import uk.ac.ox.map.explorer.client.proxy.seed.CountryProxy;
import uk.ac.ox.map.explorer.client.rpc.EntityService;
import uk.ac.ox.map.request.server.SimpleDao;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 */
@Singleton
@SuppressWarnings("serial")
public class EntityServiceImpl extends RemoteServiceServlet implements EntityService{

  private Provider<SimpleDao> daoProvider;

  @Inject
  public EntityServiceImpl(Provider<SimpleDao> dao) {
    this.daoProvider = dao;
  }

  @Override
  public ArrayList<CountryProxy> search(Integer firstResult, Integer maxResults, String searchParams) {
    List<Country> countries = daoProvider.get().search(firstResult, maxResults, searchParams, Country.class);
    ArrayList<CountryProxy> dtos = new ArrayList<CountryProxy>();
    
    for (Country country : countries) {
      CountryProxy p = new CountryProxy();
      p.setId(country.getId());
      p.setName(country.getName());
      dtos.add(p);
    }
    return dtos;
  }

  @Override
  public Long searchCount(String searchParams) {
    return daoProvider.get().searchCount(searchParams, Country.class);
  }


}
