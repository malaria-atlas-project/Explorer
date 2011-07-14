package uk.ac.ox.map.explorer.client.map.presenter;

import java.util.List;

import uk.ac.ox.map.explorer.client.map.view.KeyView;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class KeyPresenter extends AbstractActivity {
  
  private KeyView display;

  @Inject
  public KeyPresenter(KeyView keyView, EventBus eventBus, ResourceBundle resources) {
    this.display = keyView;
    
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(display);
  }

  public void setLayers(List<MapLayer> layers) {
    for (MapLayer mapLayer : layers) {
      display.addLayer(mapLayer);
    }
  }

}
