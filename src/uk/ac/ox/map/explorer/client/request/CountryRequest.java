package uk.ac.ox.map.explorer.client.request;

import java.util.List;

import uk.ac.ox.map.domain.Country;
import uk.ac.ox.map.explorer.client.proxy.CountryProxy;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Country.class)
public interface CountryRequest extends RequestContext {
	
	Request<CountryProxy> findCountry(String id);
	
	Request<List<CountryProxy>> search(Integer i, Integer j, String rp);
	Request<Long> searchCount(String rp);
	
  Request<List<CountryProxy>> all();
}
