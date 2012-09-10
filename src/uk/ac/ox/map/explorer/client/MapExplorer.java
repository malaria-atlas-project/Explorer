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
    
    /*
     * 
     */
    ResourceBundle.INSTANCE.formCss().ensureInjected();
    ResourceBundle.INSTANCE.baseCss().ensureInjected();
    ResourceBundle.INSTANCE.panelCss().ensureInjected();
    ResourceBundle.INSTANCE.popupCss().ensureInjected();
    
    ExplorerGinjector injector = GWT.create(ExplorerGinjector.class);
    
    RootLayoutPanel.get().add(injector.getBaseView());
    RootLayoutPanel.get().getElement().setId("ROOT");    
    RootLayoutPanel.get().getElement().setAttribute("style", RootLayoutPanel.get().getElement().getAttribute("style")+ "min-width: 792px; min-height: 700px;");

    /*
     * Asking injector to create base presenter bootstraps app.
     */
    injector.getBasePresenter();
    
    /* 
     * Go to place represented on URL or default place
     */
    PlaceHistoryHandler historyHandler = injector.getPlaceHistoryHandler();
    historyHandler.handleCurrentHistory();
    
  }
  
}
