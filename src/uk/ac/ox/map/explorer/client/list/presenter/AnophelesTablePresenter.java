package uk.ac.ox.map.explorer.client.list.presenter;

import uk.ac.ox.map.explorer.client.event.AnophelineCheckedEvent;
import uk.ac.ox.map.explorer.client.event.AnophelineSelectedEvent;
import uk.ac.ox.map.explorer.client.list.view.AnophelineFilterList;
import uk.ac.ox.map.explorer.client.list.view.AnophelineTableView;
import uk.ac.ox.map.explorer.client.proxy.AnophelineProxy;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

/**
 * 
 * 
 * @author will
 */
public class AnophelesTablePresenter extends BaseTablePresenter<AnophelineProxy> {
  
  @Inject
  public AnophelesTablePresenter(PlaceController placeController, AnophelineTableView tableView, AnophelineFilterList filterList, AnophelesDataProvider dp, AnophelesSelectionPresenter selectionPresenter) {
    
    super(placeController, tableView, filterList, selectionPresenter, dp);
    
  }
  
  
  @Override
  public void fireObjectSelected(AnophelineProxy obj) {
    eventBus.fireEvent(new AnophelineSelectedEvent(obj));
  }

  @Override
  public void fireObjectChecked(AnophelineProxy obj, boolean isChecked) {
    eventBus.fireEvent(new AnophelineCheckedEvent(obj, isChecked));
  }

}
