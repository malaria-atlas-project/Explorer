package uk.ac.ox.map.explorer.client;

import uk.ac.ox.map.explorer.client.base.view.BaseView;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;
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
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class MapAdminClientModule extends AbstractGinModule {

  @Override
  protected void configure() {
    
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
    
    bind(ResourceBundle.class).in(Singleton.class);
    
    /*
     * Constants
     */
    bindConstant().annotatedWith(Names.named("TABLE_SIZE")).to(100);
  }


  @Provides
  @Singleton
  public PlaceHistoryHandler getHistoryHandler(PlaceController placeController, PlaceHistoryMapper historyMapper, EventBus eventBus) {
    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
    historyHandler.register(placeController, eventBus, new HomePagePlace());
    return historyHandler;
  }

  @Provides
  @Singleton
  public PlaceController getPlaceController(EventBus eventBus) {
    return new PlaceController(eventBus);
  }

  /*
  @Provides
  @Singleton
  public ActivityManager getActivityManager(AppActivityMapper mapper, EventBus eventBus, AppWidget display) {
    ActivityManager activityManager = new ActivityManager(mapper, eventBus);
    activityManager.setDisplay(display);
    return activityManager;
  }
  */
  
  @Provides
  @Singleton
  public AppRequestFactory getRequestFactory(EventBus eventBus, AppRequestTransport transport) {
    AppRequestFactory rf = GWT.create(AppRequestFactory.class);
    rf.initialize(eventBus, transport);
    return rf;
  }
  

}
