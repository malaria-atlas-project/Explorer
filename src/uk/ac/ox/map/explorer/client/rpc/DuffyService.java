package uk.ac.ox.map.explorer.client.rpc;

import java.util.ArrayList;

import javax.inject.Singleton;

import uk.ac.ox.map.explorer.client.proxy.CountryAllProxy;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@Singleton
@RemoteServiceRelativePath("duffy")
public interface DuffyService extends RemoteService {

	ArrayList<CountryAllProxy> search(Integer firstResult, Integer maxResults, String searchParams);
		  
	Long searchCount(String searchParams);
}
