package uk.ac.ox.map.explorer.client;

import uk.ac.ox.map.explorer.client.base.view.BaseView;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;

@GinModules(ExplorerClientModule.class)
public interface ExplorerGinjector extends Ginjector {
  
  BasePresenter getBasePresenter();
  
  BaseView getBaseView();
  
  PlaceHistoryHandler getPlaceHistoryHandler();
  
}
