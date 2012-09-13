package uk.ac.ox.map.explorer.client.base.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Simple view for holding table components.
 * 
 */
public class CompositeTableView extends Composite {
  
  interface CompositeTableViewUiBinder extends
      UiBinder<Widget, CompositeTableView> {
  }
  
  private static CompositeTableViewUiBinder uiBinder = GWT
      .create(CompositeTableViewUiBinder.class);
  
  @UiField
  SimplePanel tablePanel;
  
  @UiField
  SimplePanel filterPanel;
  
  @UiField
  SimplePanel selectionPanel;
  
  public CompositeTableView() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  public AcceptsOneWidget getFilterPanel() {
    return filterPanel;
  }
  
  public AcceptsOneWidget getSelectionPanel() {
    return selectionPanel;
  }
  
  public AcceptsOneWidget getTablePanel() {
    return tablePanel;
  }
  
}
