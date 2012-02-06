package uk.ac.ox.map.explorer.client.list.view;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.filter.presenter.Filter;
import uk.ac.ox.map.explorer.client.filter.view.FilterContainer;

import com.google.gwt.user.client.ui.Composite;

/**
 * 
 * Simple base class for composite filters which provides a list of filters to
 * clients.
 * 
 * Subclasses define a UI with a number of widgets that implement
 * {@link Filter}. These must be added to the list of filters in the
 * subclass constructor.
 * 
 * @author will
 * 
 */
public abstract class BaseCompositeFilter extends Composite implements FilterContainer {

  protected List<Filter> filters = new ArrayList<Filter>();

  @Override
  public List<Filter> getFilters() {
    return filters;
  }

}