package uk.ac.ox.map.explorer.server;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import uk.ac.ox.map.domain.CountryAll;
import uk.ac.ox.map.explorer.client.proxy.CountryAllProxy;
import uk.ac.ox.map.explorer.client.rpc.DuffyService;
import uk.ac.ox.map.request.server.SimpleDao;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Provider;

@Singleton
@SuppressWarnings("serial")
public class DuffyServiceImpl extends RemoteServiceServlet implements DuffyService{

	private Provider<SimpleDao> daoProvider;

	@Inject
	public DuffyServiceImpl(Provider<SimpleDao> dao) {
		daoProvider = dao;
	}

	@Override
	public ArrayList<CountryAllProxy> search(Integer firstResult,
			Integer maxResults, String searchParams) {
		List<CountryAll> countries = daoProvider.get().search(firstResult,
				maxResults, searchParams, CountryAll.class);
		ArrayList<CountryAllProxy> dtos = new ArrayList<CountryAllProxy>();

		for (CountryAll country : countries) {
			CountryAllProxy p = new CountryAllProxy();
			p.setId(country.getId());
			p.setName(country.getName());
			dtos.add(p);
			ExtentUtil.copyExtent(p, country.getExtent());
		}
		return dtos;
	}

	@Override
	public Long searchCount(String searchParams) {
		return daoProvider.get().searchCount(searchParams, CountryAll.class);
	}
}
