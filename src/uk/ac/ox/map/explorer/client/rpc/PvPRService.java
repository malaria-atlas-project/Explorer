package uk.ac.ox.map.explorer.client.rpc;

import java.util.ArrayList;
import javax.inject.Singleton;
import uk.ac.ox.map.explorer.client.proxy.CountryProxy;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@Singleton
@RemoteServiceRelativePath("pvpr")
public interface PvPRService extends RemoteService {
	
	ArrayList<CountryProxy> search(Integer firstResult, Integer maxResults,
		      String searchParams);
		  
		  Long searchCount(String searchParams);

}
