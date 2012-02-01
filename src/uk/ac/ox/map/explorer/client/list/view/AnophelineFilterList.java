package uk.ac.ox.map.explorer.client.list.view;

import uk.ac.ox.map.explorer.client.filter.presenter.Filter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class AnophelineFilterList extends BaseCompositeFilter {

  private static FilterListUiBinder uiBinder = GWT.create(FilterListUiBinder.class);

  interface FilterListUiBinder extends UiBinder<Widget, AnophelineFilterList> {
  }
  
  @UiField
  Filter region;
  
  public AnophelineFilterList() {
    initWidget(uiBinder.createAndBindUi(this));
    
    filters.add(region);
  }


}
