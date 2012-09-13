package uk.ac.ox.map.explorer.client.filter.presenter;

import java.util.List;
import java.util.Map;

import uk.ac.ox.map.explorer.client.filter.view.FilterContainer;
import uk.ac.ox.map.explorer.client.place.EntityPlace;
import uk.ac.ox.map.explorer.client.place.PlaceUtils;
import uk.ac.ox.map.explorer.client.place.QueryStringBuilder;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

/**
 * Listens for filter changes (via {@link FilterListener} interface.
 * 
 * May be many instances of a {@link Filter} to one {@link FilterListener}.
 * 
 * @author will
 */
public class FilterPresenter extends AbstractActivity implements FilterListener {
  
  private final List<Filter> filters;
  private EventBus eventBus;
  private FilterContainer filterContainer;
  
  public FilterPresenter(EntityPlace place, FilterContainer filterListener) {
    
    filterContainer = filterListener;
    filters = filterContainer.getFilters();
    
    Map<String, String> filterParams = PlaceUtils.getMapFromParams2(place
        .getQueryString());
    for (Filter filter : filters) {
      
      /*
       * filter.
       */
      filter.setListener(this);
      
      /*
       * Set the filter values
       */
      String pred = filterParams.get(filter.getParameterName());
      if (pred != null) {
        String[] parts = pred.split("=");
        filter.setValue(parts[1]);
      }
    }
  }
  
  @Override
  public void onFilterChange() {
    
    QueryStringBuilder qsb = new QueryStringBuilder('&');
    for (Filter f : filters) {
      qsb.addParam(f.getFilterString());
    }
    eventBus.fireEvent(new FilterChangedEvent(qsb.finish()));
    
  }
  
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(filterContainer);
    this.eventBus = eventBus;
  }
  
}
