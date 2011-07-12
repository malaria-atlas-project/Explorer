package uk.ac.ox.map.explorer.client.list.presenter;

import java.util.List;

import uk.ac.ox.map.explorer.client.event.ExtentChangeRequestEvent;
import uk.ac.ox.map.explorer.client.list.view.CountryTableView;
import uk.ac.ox.map.request.client.filter.presenter.FilterPresenter;
import uk.ac.ox.map.request.client.filter.view.composite.CountryFilterList;
import uk.ac.ox.map.request.client.proxy.CountryProxy;
import uk.ac.ox.map.request.client.proxy.ExtentProxy;
import uk.ac.ox.map.request.client.request.AppRequestFactory;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.web.bindery.requestfactory.shared.Request;

public class CountryPresenter extends AbstractTablePresenter<CountryProxy> {
  
//  private final CompositeListView listView;
  private final CountryFilterList filterList;
  
  @Inject
  private EventBus eventBus;

  @Inject
  public CountryPresenter(PlaceController placeController, CountryTableView tableView, CountryFilterList filterList, AppRequestFactory rf) {
    
    super(placeController, tableView);
    
    this.filterList = filterList;
    
    this.dataProvider = new AbstractDataProvider<CountryProxy>(rf, tableView){

      @Override
      protected Request<List<CountryProxy>> getSearchRequest(Integer i, Integer j, String searchString) {
        return marq.countryRequest().search(i, j, searchString).with("region", "extent");
      }

      @Override
      protected Request<Long> getSearchCountRequest(String searchString) {
        return marq.countryRequest().searchCount(searchString);
      }
      
    };
  }
  
  /*
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(listView);
    super.start(listView.getTablePanel(), eventBus);
    FilterPresenter filterPresenter = new FilterPresenter(place, filterList);
    filterPresenter.start(listView.getFilterPanel(), eventBus);
    this.eventBus = eventBus;
  };
  */
  
  @Override
  public void fireObjectSelected(CountryProxy obj) {
    ExtentProxy ex = obj.getExtent();
    eventBus.fireEvent(new ExtentChangeRequestEvent(ex));
  }

}
