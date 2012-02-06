package uk.ac.ox.map.explorer.client.event;

import uk.ac.ox.map.explorer.client.proxy.AnophelineProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Fired when an {@link AnophelineProxy} is selected.
 */
public class AnophelineSelectedEvent extends GwtEvent<AnophelineSelectedEvent.Handler> {
  
  public interface Handler extends EventHandler {
    void onAnophelineSelected(AnophelineSelectedEvent requestEvent);
  }

  public static final Type<Handler> TYPE = new Type<Handler>();

  /**
   * Register a {@link AnophelineSelectedEvent.Handler} on an {@link EventBus}.
   *
   * @param eventBus the {@link EventBus}
   * @param handler a {@link AnophelineSelectedEvent.Handler}
   * @return a {@link HandlerRegistration} instance
   */
  public static HandlerRegistration register(EventBus eventBus,
      AnophelineSelectedEvent.Handler handler) {
    return eventBus.addHandler(TYPE, handler);
  }

  private AnophelineProxy anopheline;


  public AnophelineSelectedEvent(AnophelineProxy anopheline) {
    this.anopheline = anopheline;
  }

  @Override
  public GwtEvent.Type<Handler> getAssociatedType() {
    return TYPE;
  }
  
  
  public AnophelineProxy getAnopheline() {
    return anopheline;
  }

  @Override
  protected void dispatch(Handler handler) {
    handler.onAnophelineSelected(this);
  }

}
