package uk.ac.ox.map.explorer.client.map.view;

import uk.ac.ox.map.explorer.client.resource.ResourceBundle;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Simple popup panel to display information to user about layers.
 * 
 * @author will
 */
@Singleton
public class LayerInfoPopup extends PopupPanel {

  private final Label title = new Label();
  private final Label label = new Label();
  private final VerticalPanel panel = new VerticalPanel();

  @Inject
  public LayerInfoPopup(ResourceBundle resources) {
    super(true);
    setWidget(panel);
    panel.add(title);
    panel.add(label);
    title.setStyleName(resources.panelCss().panelHeader());
    title.setText("Layer description");
    addStyleName(resources.popupCss().panel());
  }

  public void setMessage(String message) {
    label.setText(message);
  }

}
