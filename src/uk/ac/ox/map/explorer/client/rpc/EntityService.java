package uk.ac.ox.map.explorer.client.rpc;

import java.util.ArrayList;

import javax.inject.Singleton;

import uk.ac.ox.map.explorer.client.proxy.seed.CountryProxy;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@Singleton
@RemoteServiceRelativePath("entity")
public interface EntityService extends RemoteService {
  ArrayList<CountryProxy> search(Integer firstResult, Integer maxResults, String searchParams);
  Long searchCount(String searchParams);
}
