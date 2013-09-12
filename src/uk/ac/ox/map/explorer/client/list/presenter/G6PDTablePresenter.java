package uk.ac.ox.map.explorer.client.list.presenter;

import uk.ac.ox.map.explorer.client.event.G6PDCheckedEvent;
import uk.ac.ox.map.explorer.client.event.G6PDSelectedEvent;
import uk.ac.ox.map.explorer.client.list.view.CountryFilterList;
import uk.ac.ox.map.explorer.client.list.view.G6PDTableView;
import uk.ac.ox.map.explorer.client.proxy.CountryProxy;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
/**
 * Manages display of {@link CountryProxy} objects
 * 
 * @author andy
 */

public class G6PDTablePresenter extends BaseTablePresenter<CountryProxy> {
	  
	  @Inject
	  public G6PDTablePresenter(PlaceController placeController,
	      G6PDTableView tableView, CountryFilterList filterList,
	      G6PDDataProvider dp, G6PDSelectionPresenter selectionPresenter) {
	    
	    super(placeController, tableView, filterList, selectionPresenter, dp);
	    
	  }
	  
	  @Override
	  public void fireObjectChecked(CountryProxy obj, boolean isChecked) {
	    eventBus.fireEvent(new G6PDCheckedEvent(obj, isChecked));
	  }
	  
	  @Override
	  public void fireObjectSelected(CountryProxy obj) {
	    eventBus.fireEvent(new G6PDSelectedEvent(obj));
	  }
}
