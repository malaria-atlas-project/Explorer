package uk.ac.ox.map.explorer.client.list.presenter;

import uk.ac.ox.map.explorer.client.base.view.CompositeTableView;
import uk.ac.ox.map.explorer.client.event.AnophelineCheckedEvent;
import uk.ac.ox.map.explorer.client.event.AnophelineSelectedEvent;
import uk.ac.ox.map.explorer.client.filter.presenter.FilterPresenter;
import uk.ac.ox.map.explorer.client.list.view.AnophelineFilterList;
import uk.ac.ox.map.explorer.client.list.view.AnophelineTableView;
import uk.ac.ox.map.explorer.client.proxy.AnophelineProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class AnophelesTablePresenter extends AbstractTablePresenter<AnophelineProxy> {
  
  private final AnophelineFilterList filterList;
  
  @Inject
  private EventBus eventBus;

  private final CompositeTableView compositeTableView;
  
  @Inject
  private AnophelesSelectionPresenter selectionPresenter;
  
  @Inject
  public AnophelesTablePresenter(PlaceController placeController, AnophelineTableView tableView, AnophelineFilterList filterList, AnophelesDataProvider dp) {
    
    super(placeController, tableView);
    
    this.compositeTableView = new CompositeTableView();
    
    this.filterList = filterList;
    
    this.dataProvider = dp;
  }
  
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    
    panel.setWidget(compositeTableView);
    super.start(compositeTableView.getTablePanel(), eventBus);
    
    FilterPresenter filterPresenter = new FilterPresenter(place, filterList);
    filterPresenter.start(compositeTableView.getFilterPanel(), eventBus);
    
    selectionPresenter.start(compositeTableView.getSelectionPanel(), eventBus);
    
    this.eventBus = eventBus;
  };
  
  @Override
  public void fireObjectSelected(AnophelineProxy obj) {
    eventBus.fireEvent(new AnophelineSelectedEvent(obj));
  }

  @Override
  public void fireObjectChecked(AnophelineProxy obj, boolean isChecked) {
    eventBus.fireEvent(new AnophelineCheckedEvent(obj, isChecked));
  }

}
