package uk.ac.ox.map.explorer.client.list.presenter;

import java.util.ArrayList;

import uk.ac.ox.map.explorer.client.list.view.AnophelineTableView;
import uk.ac.ox.map.explorer.client.place.EntityPlace;
import uk.ac.ox.map.explorer.client.proxy.AnophelineProxy;
import uk.ac.ox.map.explorer.client.rpc.AnoServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.inject.Inject;

/**
 * Queries server for {@link AnophelineProxy} objects
 * 
 * @author will
 */
public class AnophelesDataProvider extends
    AbstractDataProvider<AnophelineProxy> {
  
  private AnoServiceAsync service;
  private EntityPlace entityPlace;
  
  @Inject
  public AnophelesDataProvider(AnoServiceAsync service,
      final AnophelineTableView view) {
    super(view);
    this.service = service;
    this.view = view;
  }
  
  @Override
  protected void onRangeChanged(final HasData<AnophelineProxy> display) {
    
    final Range range = display.getVisibleRange();
    
    service.search(range.getStart(), range.getLength(),
        entityPlace.getPayload(),
        new AsyncCallback<ArrayList<AnophelineProxy>>() {
          
          @Override
          public void onFailure(Throwable caught) {
            AnophelesDataProvider.this.onFailure(caught);
          }
          
          @Override
          public void onSuccess(ArrayList<AnophelineProxy> result) {
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
            AnophelesDataProvider.this.onFailure(caught);
          }
          
          @Override
          public void onSuccess(Long result) {
            view.getCellTable().setRowCount(result.intValue(), true);
          }
        });
  }
  
}
