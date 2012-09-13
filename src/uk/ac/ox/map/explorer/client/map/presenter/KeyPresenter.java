package uk.ac.ox.map.explorer.client.map.presenter;

import java.util.List;

import uk.ac.ox.map.explorer.client.event.ToggleLayerRequestEvent;
import uk.ac.ox.map.explorer.client.map.view.KeyView;
import uk.ac.ox.map.explorer.client.proxy.MapLayer;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * Manages display of layer information and toggles layers.
 * 
 * @author will
 * 
 */
public class KeyPresenter extends AbstractActivity {
  
  private KeyView display;
  private EventBus eventBus;
  
  @Inject
  public KeyPresenter(KeyView keyView, EventBus eventBus,
      ResourceBundle resources) {
    display = keyView;
    keyView.setListener(this);
  }
  
  public void setLayers(List<MapLayer> layers) {
    display.setLayers(layers);
  }
  
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    this.eventBus = eventBus;
    panel.setWidget(display);
  }
  
  public void toggleLayerVisibility(String name, boolean checked) {
    eventBus.fireEvent(new ToggleLayerRequestEvent(name, checked));
  }
  
}
