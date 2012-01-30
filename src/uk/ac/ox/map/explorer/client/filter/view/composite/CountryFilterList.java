package uk.ac.ox.map.explorer.client.filter.view.composite;

import uk.ac.ox.map.explorer.client.filter.presenter.Filter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class CountryFilterList extends BaseCompositeFilter {

  private static FilterListUiBinder uiBinder = GWT.create(FilterListUiBinder.class);

  interface FilterListUiBinder extends UiBinder<Widget, CountryFilterList> {
  }
  
  @UiField
  Filter pfEndemic;
  
  @UiField
  Filter pvEndemic;
  
  @UiField
  Filter name;

  @UiField
  Filter continent;
  
  public CountryFilterList() {
    initWidget(uiBinder.createAndBindUi(this));
    
    filters.add(pfEndemic);
    filters.add(pvEndemic);
    filters.add(name);
    filters.add(continent);
  }


}
