package uk.ac.ox.map.explorer.client.list.presenter;

import uk.ac.ox.map.explorer.client.event.HbSCheckedEvent;
import uk.ac.ox.map.explorer.client.event.HbSSelectedEvent;
import uk.ac.ox.map.explorer.client.list.view.CountryFilterList;
import uk.ac.ox.map.explorer.client.list.view.HbSTableView;
import uk.ac.ox.map.explorer.client.proxy.CountryAllProxy;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
/**
 * Manages display of {@link CountryAllProxy} objects
 * 
 * @author andy
 */

public class HbSTablePresenter extends BaseTablePresenter<CountryAllProxy> {
	  
	  @Inject
	  public HbSTablePresenter(PlaceController placeController,
			  HbSTableView tableView, CountryFilterList filterList,
			  HbSDataProvider dp, HbSSelectionPresenter selectionPresenter) {
	    
	    super(placeController, tableView, filterList, selectionPresenter, dp);
	    
	  }
	  
	  @Override
	  public void fireObjectChecked(CountryAllProxy obj, boolean isChecked) {
	    eventBus.fireEvent(new HbSCheckedEvent(obj, isChecked));
	  }
	  
	  @Override
	  public void fireObjectSelected(CountryAllProxy obj) {
	    eventBus.fireEvent(new HbSSelectedEvent(obj));
	  }
}

