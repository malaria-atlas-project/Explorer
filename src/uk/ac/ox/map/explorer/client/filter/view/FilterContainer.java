package uk.ac.ox.map.explorer.client.filter.view;

import java.util.List;

import uk.ac.ox.map.explorer.client.filter.presenter.Filter;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * A {@link FilterContainer} has a number of {@link Filter}s which can be used
 * to build a query string.
 * 
 * @author will
 */
public interface FilterContainer extends IsWidget {
  
  List<Filter> getFilters();
  
}
