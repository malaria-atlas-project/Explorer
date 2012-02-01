package uk.ac.ox.map.explorer.client.list.presenter;

import java.util.List;

import uk.ac.ox.map.explorer.client.base.view.CompositeTableView;
import uk.ac.ox.map.explorer.client.event.CountryCheckedEvent;
import uk.ac.ox.map.explorer.client.event.CountrySelectedEvent;
import uk.ac.ox.map.explorer.client.filter.presenter.FilterPresenter;
import uk.ac.ox.map.explorer.client.list.view.CountryFilterList;
import uk.ac.ox.map.explorer.client.list.view.CountryTableView;
import uk.ac.ox.map.explorer.client.proxy.CountryProxy;
import uk.ac.ox.map.explorer.client.request.AppRequestFactory;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Request;

public class CountryTablePresenter extends AbstractTablePresenter<CountryProxy> {
  
  private final CountryFilterList filterList;
  
  @Inject
  private EventBus eventBus;
  
  @Inject
  private CountrySelectionPresenter selectionPresenter;

  private final CompositeTableView compositeTableView;
  
  @Inject
  public CountryTablePresenter(PlaceController placeController, CountryTableView tableView, CountryFilterList filterList, AppRequestFactory rf) {
    
    super(placeController, tableView);
    
    this.compositeTableView = new CompositeTableView();
    
    this.filterList = filterList;
    
    this.dataProvider = new AbstractDataProvider<CountryProxy>(rf, tableView){

      @Override
      protected Request<List<CountryProxy>> getSearchRequest(Integer i, Integer j, String searchString) {
        return requestFactory.countryRequest().search(i, j, searchString).with("region", "extent");
      }

      @Override
      protected Request<Long> getSearchCountRequest(String searchString) {
        return requestFactory.countryRequest().searchCount(searchString);
      }
      
    };
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
