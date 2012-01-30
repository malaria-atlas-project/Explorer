package uk.ac.ox.map.explorer.client.filter.view.composite;

import uk.ac.ox.map.explorer.client.filter.presenter.Filter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class SurveyFilterList extends BaseCompositeFilter {

  private static SurveyFilterListUiBinder uiBinder = GWT.create(SurveyFilterListUiBinder.class);

  interface SurveyFilterListUiBinder extends UiBinder<Widget, SurveyFilterList> {
  }

  @UiField
  Filter method;
  
  public SurveyFilterList() {
    initWidget(uiBinder.createAndBindUi(this));
    filters.add(method);
  }

}
