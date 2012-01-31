package uk.ac.ox.map.explorer.client.request;

import java.util.List;

import uk.ac.ox.map.domain.Anopheline;
import uk.ac.ox.map.explorer.client.proxy.AnophelineProxy;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Anopheline.class)
public interface AnophelineRequest extends RequestContext {
	
	Request<AnophelineProxy> findAnopheline(Long id);
	
	Request<List<AnophelineProxy>> search(Integer i, Integer j, String rp);
	
	Request<Long> searchCount(String rp);
	
  Request<List<AnophelineProxy>> all();
}
