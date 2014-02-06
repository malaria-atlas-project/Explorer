package uk.ac.ox.map.explorer.client.list.presenter;

import uk.ac.ox.map.explorer.client.event.AnophelineCheckedEvent;
import uk.ac.ox.map.explorer.client.event.AnophelineSelectedEvent;
import uk.ac.ox.map.explorer.client.list.view.AnophelineFilterList;
import uk.ac.ox.map.explorer.client.list.view.AnophelineTableView;
import uk.ac.ox.map.explorer.client.proxy.AnophelineProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * 
 * 
 * @author will
 */
@Singleton
public class AnophelesTablePresenter extends
    BaseTablePresenter<AnophelineProxy> {
  
  @Inject
  public AnophelesTablePresenter(PlaceController placeController,
      AnophelineTableView tableView, AnophelineFilterList filterList,
      AnophelesDataProvider dp, AnophelesSelectionPresenter selectionPresenter) {
    
    super(placeController, tableView, filterList, selectionPresenter, dp);
    
  }
  
  @Override
  public void fireObjectChecked(AnophelineProxy obj, boolean isChecked) {
    eventBus.fireEvent(new AnophelineCheckedEvent(obj, isChecked));
  }
  
  @Override
  public void fireObjectSelected(AnophelineProxy obj) {
    eventBus.fireEvent(new AnophelineSelectedEvent(obj));
  }
  
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    super.start(panel, eventBus);
    
    eventBus.addHandler(AnophelineSelectedEvent.TYPE,
        new AnophelineSelectedEvent.Handler() {
          
          @Override
          public void onAnophelineSelected(AnophelineSelectedEvent requestEvent) {
            if (requestEvent.getAnopheline() == null) {
              // An AnophelineSelectedEvent with a null payload indicated the
              // reset button has been pressed
              tableView.clearSelection();
            }
          }
        });
  }
  
}
