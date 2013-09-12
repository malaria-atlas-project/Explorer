package uk.ac.ox.map.explorer.server;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import uk.ac.ox.map.domain.Country;
import uk.ac.ox.map.explorer.client.proxy.CountryProxy;
import uk.ac.ox.map.explorer.client.rpc.G6PDService;
import uk.ac.ox.map.request.server.SimpleDao;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Provider;

@Singleton
@SuppressWarnings("serial")
public class G6PDServiceImpl extends RemoteServiceServlet implements
		G6PDService {

	private Provider<SimpleDao> daoProvider;

	@Inject
	public G6PDServiceImpl(Provider<SimpleDao> dao) {
		daoProvider = dao;
	}

	@Override
	public ArrayList<CountryProxy> search(Integer firstResult,
			Integer maxResults, String searchParams) {
		List<Country> countries = daoProvider.get().search(firstResult,
				maxResults, searchParams, Country.class);
		ArrayList<CountryProxy> dtos = new ArrayList<CountryProxy>();

		for (Country country : countries) {
			CountryProxy p = new CountryProxy();
			p.setId(country.getId());
			p.setName(country.getName());
			dtos.add(p);
			ExtentUtil.copyExtent(p, country.getExtent());
		}
		return dtos;
	}

	@Override
	public Long searchCount(String searchParams) {
		return daoProvider.get().searchCount(searchParams, Country.class);
	}
}
