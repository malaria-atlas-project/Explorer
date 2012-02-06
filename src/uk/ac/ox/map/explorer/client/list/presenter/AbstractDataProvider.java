package uk.ac.ox.map.explorer.client.list.presenter;


import uk.ac.ox.map.explorer.client.list.view.TableView;
import uk.ac.ox.map.explorer.client.place.EntityPlace;
import uk.ac.ox.map.explorer.client.proxy.NamedProxy;

import com.google.gwt.view.client.AsyncDataProvider;

/**
 * Base class for data providers
 * 
 * @author will
 *
 * @param <T> The proxy being displayed
 */
public abstract class AbstractDataProvider<T extends NamedProxy> extends AsyncDataProvider<T> {
	
  protected EntityPlace entityPlace;
  
  protected TableView<T> view;

	public AbstractDataProvider(final TableView<T> view) {
		this.view = view;
	}
	
	protected abstract void start(EntityPlace place);
	

	/**
	 * Can be called by all subclasses to log failure. Currently no-op.
	 * 
	 * @param caught
	 */
	protected void onFailure(Throwable caught) {
	}

}
