package uk.ac.ox.map.explorer.client.list.view;

import uk.ac.ox.map.explorer.client.event.AnophelineSelectedEvent;
import uk.ac.ox.map.explorer.client.filter.presenter.Filter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class AnophelineFilterList extends BaseCompositeFilter {
	
  @Inject
  protected EventBus eventBus;
  
  private static FilterListUiBinder uiBinder = GWT.create(FilterListUiBinder.class);

  interface FilterListUiBinder extends UiBinder<Widget, AnophelineFilterList> {
  }
  
  @UiField
  Filter region;
  
  @UiField
  Button resetButton;
  
  public AnophelineFilterList() {
    initWidget(uiBinder.createAndBindUi(this));
    
    filters.add(region);
  }
  
  @UiHandler("resetButton")
  void handleClick(ClickEvent e) {
    eventBus.fireEvent(new AnophelineSelectedEvent(null));
  }

  
}
