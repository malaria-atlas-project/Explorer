package uk.ac.ox.map.explorer.client.filter.view.composite;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.filter.presenter.Filter;
import uk.ac.ox.map.explorer.client.filter.view.FilterContainer;

import com.google.gwt.user.client.ui.Composite;

/**
 * @author will
 * 
 * Simple base class for composite filters which provides a list of filters to clients.
 *
 */
public abstract class BaseCompositeFilter extends Composite implements FilterContainer {

  protected List<Filter> filters = new ArrayList<Filter>();

  @Override
  public List<Filter> getFilters() {
    return filters;
  }

}