package uk.ac.ox.map.explorer.client.event;

import uk.ac.ox.map.explorer.client.proxy.AnophelineProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * An event posted when an authentication failure is detected.
 */
public class AnophelineCheckedEvent extends GwtEvent<AnophelineCheckedEvent.Handler> {
  
  public interface Handler extends EventHandler {
    void onObjectChecked(AnophelineCheckedEvent requestEvent);
  }

  public static final Type<Handler> TYPE = new Type<Handler>();

  /**
   * Register a {@link AnophelineCheckedEvent.Handler} on an {@link EventBus}.
   *
   * @param eventBus the {@link EventBus}
   * @param handler a {@link AnophelineCheckedEvent.Handler}
   * @return a {@link HandlerRegistration} instance
   */
  public static HandlerRegistration register(EventBus eventBus,
      AnophelineCheckedEvent.Handler handler) {
    return eventBus.addHandler(TYPE, handler);
  }

  private AnophelineProxy country;
  private boolean isChecked;


  public AnophelineCheckedEvent(AnophelineProxy country, boolean isChecked) {
    this.isChecked = isChecked;
    this.country = country;
  }

  @Override
  public GwtEvent.Type<Handler> getAssociatedType() {
    return TYPE;
  }
  
  public boolean isChecked() {
    return isChecked;
  }
  
  public AnophelineProxy getAnopheline() {
    return country;
  }

  @Override
  protected void dispatch(Handler handler) {
    handler.onObjectChecked(this);
  }

}
