package uk.ac.ox.map.explorer.client.list.presenter;

import uk.ac.ox.map.explorer.client.event.DuffyCheckedEvent;
import uk.ac.ox.map.explorer.client.event.DuffySelectedEvent;
import uk.ac.ox.map.explorer.client.list.view.CountryFilterList;
import uk.ac.ox.map.explorer.client.list.view.DuffyTableView;
import uk.ac.ox.map.explorer.client.proxy.CountryAllProxy;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class DuffyTablePresenter extends BaseTablePresenter<CountryAllProxy> {
	
	@Inject
	  public DuffyTablePresenter(PlaceController placeController,
	      DuffyTableView tableView, CountryFilterList filterList,
	      DuffyDataProvider dp, DuffySelectionPresenter selectionPresenter) {
	    
	    super(placeController, tableView, filterList, selectionPresenter, dp);
	    
	  }
	  
	  @Override
	  public void fireObjectChecked(CountryAllProxy obj, boolean isChecked) {
	    eventBus.fireEvent(new DuffyCheckedEvent(obj, isChecked));
	  }
	  
	  @Override
	  public void fireObjectSelected(CountryAllProxy obj) {
	    eventBus.fireEvent(new DuffySelectedEvent(obj));
	  }
}
