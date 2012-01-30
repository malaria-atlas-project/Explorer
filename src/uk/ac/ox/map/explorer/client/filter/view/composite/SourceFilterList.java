package uk.ac.ox.map.explorer.client.filter.view.composite;


import uk.ac.ox.map.explorer.client.filter.presenter.Filter;
import uk.ac.ox.map.explorer.client.filter.presenter.Operator;
import uk.ac.ox.map.explorer.client.filter.view.TextFilter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class SourceFilterList extends BaseCompositeFilter {

  private static SourceFilterListUiBinder uiBinder = GWT.create(SourceFilterListUiBinder.class);

  interface SourceFilterListUiBinder extends UiBinder<Widget, SourceFilterList> {
  }
  
  @UiField
  TextFilter id;
  
  @UiField
  Filter title;
  
  @UiField
  Filter sourceType;
  
  public SourceFilterList() {
    initWidget(uiBinder.createAndBindUi(this));
    id.setOperator(Operator.eq);
    filters.add(id);
    filters.add(title);
    filters.add(sourceType);
  }

}
