package uk.ac.ox.map.explorer.client.map.presenter;

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
    
    MapLayer ml = new MapLayer();
    ml.setLayerName("test");
    ml.setImageResource(resources.endemicityScale());
    display.addLayer(ml);
    
    MapLayer ml2 = new MapLayer();
    ml2.setLayerName("test2");
    ml2.setImageResource(resources.successIcon());
    
    display.addLayer(ml2);
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(display);
  }

}
