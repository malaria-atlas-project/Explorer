package uk.ac.ox.map.explorer.client;

import uk.ac.ox.map.explorer.client.resource.ResourceBundle;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MapExplorer implements EntryPoint {

  @Override
  public void onModuleLoad() {
    
    ResourceBundle.INSTANCE.formCss().ensureInjected();
    ResourceBundle.INSTANCE.baseCss().ensureInjected();
    ResourceBundle.INSTANCE.panelCss().ensureInjected();
    
    MapAdminGinjector maj = GWT.create(MapAdminGinjector.class);
    
    RootLayoutPanel.get().add(maj.getBaseView());

    //Not really a base presenter - more an app controller?
    BasePresenter bp = maj.getBasePresenter();
    
    // Goes to place represented on URL or default place
    PlaceHistoryHandler historyHandler = maj.getPlaceHistoryHandler();
    historyHandler.handleCurrentHistory();
    
  }
  
}
