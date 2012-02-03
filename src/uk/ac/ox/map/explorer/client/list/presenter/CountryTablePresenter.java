package uk.ac.ox.map.explorer.client.list.presenter;

import uk.ac.ox.map.explorer.client.base.view.CompositeTableView;
import uk.ac.ox.map.explorer.client.event.CountryCheckedEvent;
import uk.ac.ox.map.explorer.client.event.CountrySelectedEvent;
import uk.ac.ox.map.explorer.client.filter.presenter.FilterPresenter;
import uk.ac.ox.map.explorer.client.list.view.CountryFilterList;
import uk.ac.ox.map.explorer.client.list.view.CountryTableView;
import uk.ac.ox.map.explorer.client.proxy.CountryProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class CountryTablePresenter extends AbstractTablePresenter<CountryProxy> {
  
  private final CountryFilterList filterList;
  
  @Inject
  private EventBus eventBus;
  
  @Inject
  private CountrySelectionPresenter selectionPresenter;

  private final CompositeTableView compositeTableView;
  
  @Inject
  public CountryTablePresenter(PlaceController placeController, CountryTableView tableView, CountryFilterList filterList, CountryDataProvider dp) {
    
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
  public void fireObjectSelected(CountryProxy obj) {
    eventBus.fireEvent(new CountrySelectedEvent(obj));
  }

  @Override
  public void fireObjectChecked(CountryProxy obj, boolean isChecked) {
    eventBus.fireEvent(new CountryCheckedEvent(obj, isChecked));
  }

}
