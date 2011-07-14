package uk.ac.ox.map.explorer.client.event;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * An event posted when an authentication failure is detected.
 */
public class LayerChangeRequestEvent extends GwtEvent<LayerChangeRequestEvent.Handler> {
  
  public interface Handler extends EventHandler {
    void onExtentChangeRequest(LayerChangeRequestEvent requestEvent);
  }

  public static final Type<Handler> TYPE = new Type<Handler>();

  /**
   * Register a {@link LayerChangeRequestEvent.Handler} on an {@link EventBus}.
   *
   * @param eventBus the {@link EventBus}
   * @param handler a {@link LayerChangeRequestEvent.Handler}
   * @return a {@link HandlerRegistration} instance
   */
  public static HandlerRegistration register(EventBus eventBus,
      LayerChangeRequestEvent.Handler handler) {
    return eventBus.addHandler(TYPE, handler);
  }

  private boolean isActive;
  private String layerName;


  public LayerChangeRequestEvent(String layerName, boolean isActive) {
    this.isActive = isActive;
    this.layerName = layerName;
  }

  @Override
  public GwtEvent.Type<Handler> getAssociatedType() {
    return TYPE;
  }
  
  public boolean isActive() {
    return isActive;
  }
  
  public String getLayerName() {
    return layerName;
  }
  


  @Override
  protected void dispatch(Handler handler) {
    handler.onExtentChangeRequest(this);
  }

}
