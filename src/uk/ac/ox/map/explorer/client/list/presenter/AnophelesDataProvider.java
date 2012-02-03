package uk.ac.ox.map.explorer.client.list.presenter;

import java.util.ArrayList;

import uk.ac.ox.map.explorer.client.list.view.AnophelineTableView;
import uk.ac.ox.map.explorer.client.list.view.TableView;
import uk.ac.ox.map.explorer.client.place.EntityPlace;
import uk.ac.ox.map.explorer.client.proxy.seed.AnophelineProxy;
import uk.ac.ox.map.explorer.client.rpc.AnoServiceAsync;
import uk.ac.ox.map.explorer.client.rpc.EntityServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.inject.Inject;

public class AnophelesDataProvider extends AbstractDataProvider2<AnophelineProxy>{
  
  private AnoServiceAsync service;
  private EntityPlace entityPlace;

  @Inject
  public AnophelesDataProvider(AnoServiceAsync service, final AnophelineTableView view) {
    super(view);
    this.service = service;
		this.view = view;
	}
  
	public void start(EntityPlace place) {
	  
		this.entityPlace = place;
    addDataDisplay(view.getCellTable());
		
    service.searchCount(entityPlace.getQueryString(), new AsyncCallback<Long>() {

      @Override
      public void onFailure(Throwable caught) {
      }

      @Override
      public void onSuccess(Long result) {
			  view.getCellTable().setRowCount(result.intValue(), true);
      }
		});
	}
  

  @Override
  protected void onRangeChanged(final HasData<AnophelineProxy> display) {
    
		final Range range = display.getVisibleRange();
    
    service.search(range.getStart(), range.getLength(), entityPlace.getQueryString(), new AsyncCallback<ArrayList<AnophelineProxy>>() {
      
      @Override
      public void onSuccess(ArrayList<AnophelineProxy> result) {
				display.setRowData(range.getStart(), result);
      }
      
      @Override
      public void onFailure(Throwable caught) {
        
      }
    });
    
  }

}
