package uk.ac.ox.map.explorer.client.list.presenter;

import uk.ac.ox.map.explorer.client.event.CountryCheckedEvent;
import uk.ac.ox.map.explorer.client.event.CountrySelectedEvent;
import uk.ac.ox.map.explorer.client.list.view.CountryFilterList;
import uk.ac.ox.map.explorer.client.list.view.CountryTableView;
import uk.ac.ox.map.explorer.client.proxy.CountryProxy;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

/**
 * Manages display of {@link CountryProxy} objects
 * 
 * @author will
 */
public class CountryTablePresenter extends BaseTablePresenter<CountryProxy> {
  
  @Inject
  public CountryTablePresenter(PlaceController placeController, CountryTableView tableView, CountryFilterList filterList, CountryDataProvider dp, CountrySelectionPresenter selectionPresenter) {
    
    super(placeController, tableView, filterList, selectionPresenter, dp);
    
  }
  
  @Override
  public void fireObjectSelected(CountryProxy obj) {
    eventBus.fireEvent(new CountrySelectedEvent(obj));
  }

  @Override
  public void fireObjectChecked(CountryProxy obj, boolean isChecked) {
    eventBus.fireEvent(new CountryCheckedEvent(obj, isChecked));
  }

}
