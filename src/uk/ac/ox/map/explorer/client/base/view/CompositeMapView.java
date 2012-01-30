package uk.ac.ox.map.explorer.client.base.view;

import uk.ac.ox.map.explorer.client.map.view.MapView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

public class CompositeMapView extends Composite {

  private static CompositeMapViewUiBinder uiBinder = GWT.create(CompositeMapViewUiBinder.class);

  interface CompositeMapViewUiBinder extends UiBinder<Widget, CompositeMapView> {
  }
  
  @UiField
  SimplePanel keyPanel;
  
  @UiField
  SimplePanel mapInfoPanel;
  
  @UiField
  SimplePanel mapPanel;

  @Inject
  public CompositeMapView(MapView mapExample) {
    initWidget(uiBinder.createAndBindUi(this));
    mapPanel.add(mapExample);
  }
  
  public SimplePanel getKeyPanel() {
    return keyPanel;
  }
  
  public SimplePanel getInfoPanel() {
    return mapInfoPanel;
  }
  
  public AcceptsOneWidget getMapPanel() {
    return mapPanel;
  }


}
