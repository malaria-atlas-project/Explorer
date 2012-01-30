package uk.ac.ox.map.explorer.client.event;

import uk.ac.ox.map.explorer.client.proxy.ExtentProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * An event posted when an authentication failure is detected.
 */
public class ExtentChangeRequestEvent extends GwtEvent<ExtentChangeRequestEvent.Handler> {
  
  public interface Handler extends EventHandler {
    void onExtentChangeRequest(ExtentChangeRequestEvent requestEvent);
  }

  public static final Type<Handler> TYPE = new Type<Handler>();

  /**
   * Register a {@link ExtentChangeRequestEvent.Handler} on an {@link EventBus}.
   *
   * @param eventBus the {@link EventBus}
   * @param handler a {@link ExtentChangeRequestEvent.Handler}
   * @return a {@link HandlerRegistration} instance
   */
  public static HandlerRegistration register(EventBus eventBus,
      ExtentChangeRequestEvent.Handler handler) {
    return eventBus.addHandler(TYPE, handler);
  }

  private ExtentProxy extent;


  public ExtentChangeRequestEvent(ExtentProxy extent) {
    this.extent = extent;
  }

  @Override
  public GwtEvent.Type<Handler> getAssociatedType() {
    return TYPE;
  }

  public ExtentProxy getExtent() {
    return extent;
  }

  @Override
  protected void dispatch(Handler handler) {
    handler.onExtentChangeRequest(this);
  }

}
