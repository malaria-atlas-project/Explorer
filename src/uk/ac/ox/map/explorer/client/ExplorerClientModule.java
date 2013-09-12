package uk.ac.ox.map.explorer.client;

import uk.ac.ox.map.explorer.client.base.view.BaseView;
import uk.ac.ox.map.explorer.client.place.EntityPlace;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class ExplorerClientModule extends AbstractGinModule {
  
  @Override
  protected void configure() {
    
    /*
     * Activity mappers
     */
    bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
    
    bind(PlaceHistoryMapper.class).to(AppPlaceHistoryMapper.class).in(
        Singleton.class);
    
    /*
     * Bind loosely coupled presenter displays to their implementations
     */
    bind(BasePresenter.Display.class).to(BaseView.class);
    
    bind(ResourceBundle.class).in(Singleton.class);
    
    /*
     * Constants
     */
    bindConstant().annotatedWith(Names.named("TABLE_SIZE")).to(222);
  }
  
  @SuppressWarnings("deprecation")
  @Provides
  @Singleton
  public PlaceHistoryHandler getHistoryHandler(PlaceController placeController,
      PlaceHistoryMapper historyMapper, EventBus eventBus) {
    PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
    historyHandler.register(placeController, eventBus, new EntityPlace(
        "Country"));
    return historyHandler;
  }
  
  @SuppressWarnings("deprecation")
  @Provides
  @Singleton
  public PlaceController getPlaceController(EventBus eventBus) {
    
    return new PlaceController(eventBus);
  }
  
}
