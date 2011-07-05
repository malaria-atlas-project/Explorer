package uk.ac.ox.map.explorer.list.presenter;

import java.util.List;

import uk.ac.ox.map.request.client.request.AppRequestFactory;

import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.Request;

public abstract class AbstractDataProvider<T> extends AsyncDataProvider<T> {
	
	protected final AppRequestFactory marq;
  private TableView<T> view;
  private String qs;
	
  protected abstract Request<List<T>> getSearchRequest(Integer i, Integer j, String searchString);
	protected abstract Request<Long> getSearchCountRequest(String searchString);

	/**
	 * 
	 * @param marq
	 * @param place
	 * @param view
	 * 
	 */
	public AbstractDataProvider(AppRequestFactory marq, final TableView<T> view) {
		this.marq = marq;
		this.view = view;
	}
	
	public void start(String qs) {
	  
	  this.qs = qs;
	  
    addDataDisplay(view.getCellTable());
		
		Request<Long> req = getSearchCountRequest(qs);
		req.fire(new Receiver<Long>() {
			@Override
			public void onSuccess(Long response) {
			  view.getCellTable().setRowCount(response.intValue(), true);
			}
		});
	}
	

	@Override
	protected void onRangeChanged(final HasData<T> display) {
		
		final Range range = display.getVisibleRange();
		
		Request<List<T>> req = getSearchRequest(range.getStart(), range.getLength(), qs);
		req.fire(new Receiver<List<T>>() {
			@Override
			public void onSuccess(List<T> response) {
				display.setRowData(range.getStart(), response);
			}
		});
		
	}


}
