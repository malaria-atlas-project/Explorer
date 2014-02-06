package uk.ac.ox.map.explorer.client.list.presenter;

import uk.ac.ox.map.explorer.client.event.PvPRCheckedEvent;
import uk.ac.ox.map.explorer.client.event.PvPRSelectedEvent;
import uk.ac.ox.map.explorer.client.list.view.CountryFilterList;
import uk.ac.ox.map.explorer.client.list.view.PvPRTableView;
import uk.ac.ox.map.explorer.client.proxy.CountryProxy;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class PvPRTablePresenter extends BaseTablePresenter<CountryProxy> {
	
	@Inject
	  public PvPRTablePresenter(PlaceController placeController,
	      PvPRTableView tableView, CountryFilterList filterList,
	      PvPRDataProvider dp, PvPRSelectionPresenter selectionPresenter) {
	    
	    super(placeController, tableView, filterList, selectionPresenter, dp);
	    
	  }
	  
	  @Override
	  public void fireObjectChecked(CountryProxy obj, boolean isChecked) {
	    eventBus.fireEvent(new PvPRCheckedEvent(obj, isChecked));
	  }
	  
	  @Override
	  public void fireObjectSelected(CountryProxy obj) {
	    eventBus.fireEvent(new PvPRSelectedEvent(obj));
	  }

}
