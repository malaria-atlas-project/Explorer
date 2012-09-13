package uk.ac.ox.map.explorer.client.rpc;

import java.util.ArrayList;

import uk.ac.ox.map.explorer.client.proxy.CountryProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface EntityServiceAsync {
  
  void search(Integer firstResult, Integer maxResults, String searchParams,
      AsyncCallback<ArrayList<CountryProxy>> callback);
  
  void searchCount(String searchParams, AsyncCallback<Long> callback);
  
}
