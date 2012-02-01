package uk.ac.ox.map.explorer.client.base.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class CompositeTableView extends Composite {

  private static CompositeTableViewUiBinder uiBinder = GWT.create(CompositeTableViewUiBinder.class);

  interface CompositeTableViewUiBinder extends UiBinder<Widget, CompositeTableView> {
  }
  
  @UiField
  SimplePanel tablePanel;
  
  @UiField
  SimplePanel filterPanel;
  
  @UiField
  SimplePanel selectionPanel;

  public CompositeTableView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public AcceptsOneWidget getTablePanel() {
    return tablePanel;
  }
  
  public AcceptsOneWidget getFilterPanel() {
    return filterPanel;
  }

  public AcceptsOneWidget getSelectionPanel() {
    return selectionPanel;
  }

}
