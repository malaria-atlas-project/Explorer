package uk.ac.ox.map.explorer.client.base.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class MapView extends Composite {

  private static MapViewUiBinder uiBinder = GWT.create(MapViewUiBinder.class);

  interface MapViewUiBinder extends UiBinder<Widget, MapView> {
  }
  
  @UiField
  SimplePanel filterPanel;
  
  @UiField
  SimplePanel tablePanel;
  
  @UiField
  SimplePanel detailPanel;

  public MapView() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  public SimplePanel getFilterPanel() {
    return filterPanel;
  }
  
  public AcceptsOneWidget getTablePanel() {
    return tablePanel;
  }
  
  public AcceptsOneWidget getDetailPanel() {
    return detailPanel;
  }

}
