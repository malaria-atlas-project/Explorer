package uk.ac.ox.map.explorer.client.request;

import java.util.List;

import uk.ac.ox.map.domain.Site;
import uk.ac.ox.map.explorer.client.proxy.SiteProxy;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Site.class)
public interface SiteRequest extends RequestContext {
	
	Request<SiteProxy> findSite(Long id);
	 
	Request<List<SiteProxy>> search(Integer i, Integer j, String jsonString);
	Request<Long> searchCount(String searchString);
	
	Request<List<SiteProxy>> getSitesByCoordinate(Double lon, Double lat, Double dist, Integer limit);
	
}
