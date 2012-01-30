package uk.ac.ox.map.explorer.client.filter.view;

import java.util.List;

import uk.ac.ox.map.explorer.client.filter.presenter.Filter;

import com.google.gwt.user.client.ui.IsWidget;

public interface FilterContainer extends IsWidget {
  
  List<Filter> getFilters();

}
