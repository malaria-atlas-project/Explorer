package uk.ac.ox.map.explorer.client.filter.view;

import uk.ac.ox.map.explorer.client.filter.presenter.FilterListener;

import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

public abstract class AbstractFilter extends Composite {
  
  protected final String filterProperty;
  protected FilterListener filterList;
  
  @UiField
  protected HTML filterLabel;
  
  public AbstractFilter(String propertyName) {
    this.filterProperty = propertyName;
  }
  
  public void setListener(FilterListener filterListener) {
    this.filterList = filterListener;
  }
  
  public String getParameterName() {
    return filterProperty;
  }

}
