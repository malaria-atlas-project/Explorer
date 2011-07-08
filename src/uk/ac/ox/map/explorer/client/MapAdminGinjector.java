package uk.ac.ox.map.explorer.client;

import uk.ac.ox.map.explorer.client.base.view.BaseView;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.hellomulti.client.mvp.MvpModule;

@GinModules(MvpModule.class)
public interface MapAdminGinjector extends Ginjector {
  
  PlaceHistoryHandler getPlaceHistoryHandler();
  
  BasePresenter getBasePresenter();

  BaseView getBaseView();

}
