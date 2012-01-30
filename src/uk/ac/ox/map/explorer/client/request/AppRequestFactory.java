package uk.ac.ox.map.explorer.client.request;

import com.google.web.bindery.requestfactory.shared.RequestFactory;


public interface AppRequestFactory extends RequestFactory {
  
	CountryRequest countryRequest();
	
	SiteRequest siteRequest();
  
}
