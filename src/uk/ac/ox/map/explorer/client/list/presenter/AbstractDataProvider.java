package uk.ac.ox.map.explorer.client.list.presenter;


import uk.ac.ox.map.explorer.client.list.view.TableView;
import uk.ac.ox.map.explorer.client.place.EntityPlace;

import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

public abstract class AbstractDataProvider<T> extends AsyncDataProvider<T> {
	
  protected EntityPlace entityPlace;
  protected TableView<T> view;

	/**
	 * 
	 * @param marq
	 * @param place
	 * @param view
	 * 
	 */
	public AbstractDataProvider(final TableView<T> view) {
		this.view = view;
	}
	
	
	protected abstract void start(EntityPlace place);
	

	@Override
	protected abstract void onRangeChanged(final HasData<T> display);


}
