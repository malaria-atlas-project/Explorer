package com.hellomulti.client.mvp;

import uk.ac.ox.map.explorer.client.AppPlaceHistoryMapper;
import uk.ac.ox.map.explorer.client.BasePresenter;
import uk.ac.ox.map.explorer.client.base.view.BaseView;
import uk.ac.ox.map.request.client.place.HomePagePlace;
import uk.ac.ox.map.request.client.request.AppRequestFactory;
import uk.ac.ox.map.request.client.request.AppRequestTransport;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class MvpModule extends AbstractGinModule {

  @Override
  protected void configure() {
    
    bind(MainActivityMapper.class);
    bind(VerticalMasterActivityMapper.class);
    
      /*
       * Activity mappers
       */
      bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
      
  //    bind(ActivityMapper.class).to(AppActivityMapper.class).in(Singleton.class);
      
      bind(PlaceHistoryMapper.class).to(AppPlaceHistoryMapper.class).in(Singleton.class);
      
      /*
       * Bind loosely coupled presenter displays to their implementations
       */
      bind(BasePresenter.Display.class).to(BaseView.class);
      
      
      /*
       * Constants
       */
      bindConstant().annotatedWith(Names.named("TABLE_SIZE")).to(20);
    }

  /**
   * Creates a new PlaceHistoryHandler.  This object is responsible handling navigation based on the browser URL.
   * You only need one of those for the entire app.
   * 
   * @param placeController the place controller.
   * @param historyMapper This is used to map the URL to a Place object and vice versa.
   * @param eventBus the event bus.
   * @return   */
  @Provides 
  @Singleton  
  public PlaceHistoryHandler getHistoryHandler(PlaceController placeController,
      PlaceHistoryMapper historyMapper, EventBus eventBus) {
    PlaceHistoryHandler historyHandler =  new PlaceHistoryHandler(historyMapper);
    historyHandler.register(placeController, eventBus, new HomePagePlace());

    return historyHandler;
  }

  /**
   * Creates a new PlaceController. The place controller is used by the PlaceHistoryHandler and activities
   * to tell the app to navigate to a different place. You only need one for the
   * entire app. However, it is essential for it to get instantiated in order
   * for the application navigation to work.
   * 
   * @param eventBus the event bus.
   * @return
   */
  @Provides
  @Singleton
  public PlaceController getPlaceController(EventBus eventBus) {
    return new PlaceController(eventBus);
  }

  @Provides
  @Singleton
  public AppRequestFactory getRequestFactory(EventBus eventBus, AppRequestTransport transport) {
    AppRequestFactory rf = GWT.create(AppRequestFactory.class);
    rf.initialize(eventBus, transport);
    return rf;
  }

}
