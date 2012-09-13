package uk.ac.ox.map.explorer.server;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import uk.ac.ox.map.domain.Anopheline;
import uk.ac.ox.map.explorer.client.proxy.AnophelineProxy;
import uk.ac.ox.map.explorer.client.rpc.AnoService;
import uk.ac.ox.map.request.server.SimpleDao;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 */
@Singleton
@SuppressWarnings("serial")
public class AnoServiceImpl extends RemoteServiceServlet implements AnoService {
  
  private Provider<SimpleDao> daoProvider;
  
  @Inject
  public AnoServiceImpl(Provider<SimpleDao> dao) {
    daoProvider = dao;
  }
  
  @Override
  public ArrayList<AnophelineProxy> search(Integer firstResult,
      Integer maxResults, String searchParams) {
    List<Anopheline> countries = daoProvider.get().search(firstResult,
        maxResults, searchParams, Anopheline.class);
    ArrayList<AnophelineProxy> dtos = new ArrayList<AnophelineProxy>();
    
    for (Anopheline anopheline : countries) {
      AnophelineProxy p = new AnophelineProxy();
      p.setId(anopheline.getId());
      p.setName(anopheline.getName());
      dtos.add(p);
      ExtentUtil.copyExtent(p, anopheline.getExtent());
    }
    return dtos;
  }
  
  @Override
  public Long searchCount(String searchParams) {
    return daoProvider.get().searchCount(searchParams, Anopheline.class);
  }
  
}
