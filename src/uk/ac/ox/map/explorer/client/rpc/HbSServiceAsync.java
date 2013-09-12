package uk.ac.ox.map.explorer.client.rpc;

import java.util.ArrayList;

import uk.ac.ox.map.explorer.client.proxy.CountryAllProxy;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface HbSServiceAsync {

	void search(Integer firstResult, Integer maxResults, String searchParams,
			AsyncCallback<ArrayList<CountryAllProxy>> callback);

	void searchCount(String searchParams, AsyncCallback<Long> callback);

}

