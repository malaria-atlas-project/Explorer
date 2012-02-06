package uk.ac.ox.map.explorer.client.filter.view;

import uk.ac.ox.map.explorer.client.filter.presenter.Filter;
import uk.ac.ox.map.explorer.client.filter.presenter.FilterListener;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

/**
 * Base class for filter views that provides most of the implementation of {@link Filter}
 * 
 * @author will
 */
public abstract class AbstractFilter extends Composite implements Filter {
  
  protected final String filterProperty;
  
  @UiField
  protected HTML filterLabel;

  protected FilterListener filterListener;
  
  public AbstractFilter(String propertyName) {
    this.filterProperty = propertyName;
  }
  
  public void setListener(FilterListener filterListener) {
    this.filterListener = filterListener;
  }
  
  public String getParameterName() {
    return filterProperty;
  }

}
