package uk.ac.ox.map.explorer.client.list.presenter;

import java.util.ArrayList;

import uk.ac.ox.map.explorer.client.list.view.PvPRTableView;
import uk.ac.ox.map.explorer.client.place.EntityPlace;
import uk.ac.ox.map.explorer.client.proxy.CountryProxy;
import uk.ac.ox.map.explorer.client.rpc.PvPRServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.inject.Inject;

public class PvPRDataProvider extends AbstractDataProvider<CountryProxy> {
	
	private PvPRServiceAsync service;
	  private EntityPlace entityPlace;
	  
	  @Inject
	  public PvPRDataProvider(PvPRServiceAsync service,
	      final PvPRTableView view) {
	    super(view);
	    this.service = service;
	    this.view = view;
	  }
	  
	  @Override
	  protected void onRangeChanged(final HasData<CountryProxy> display) {
	    
	    final Range range = display.getVisibleRange();
	    
	    service.search(range.getStart(), range.getLength(),
	        entityPlace.getPayload(), new AsyncCallback<ArrayList<CountryProxy>>() {
	          
	          @Override
	          public void onFailure(Throwable caught) {
	        	  PvPRDataProvider.this.onFailure(caught);
	          }
	          
	          @Override
	          public void onSuccess(ArrayList<CountryProxy> result) {
	            display.setRowData(range.getStart(), result);
	          }
	        });
	    
	  }
	  
	  @Override
	  public void start(EntityPlace place) {
	    if (place.getOrderBy().isEmpty()) {
	      // default to name order (EntityPlace is immutable so make a new one)
	      entityPlace = new EntityPlace(place.getEntityName(),
	          place.getQueryString(), "col=name&asc=true", place.isNamedQuery());
	    } else {
	      entityPlace = place;
	    }
	    addDataDisplay(view.getCellTable());
	    
	    service.searchCount(entityPlace.getQueryString(),
	        new AsyncCallback<Long>() {
	          
	          @Override
	          public void onFailure(Throwable caught) {
	        	  PvPRDataProvider.this.onFailure(caught);
	          }
	          
	          @Override
	          public void onSuccess(Long result) {
	            view.getCellTable().setRowCount(result.intValue(), true);
	          }
	        });
	  }

}
