package uk.ac.ox.map.explorer.client.event;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Fired when a layer should be toggled. Handlers use the layer name and current
 * visibility state to acheive this.
 */
public class ToggleLayerRequestEvent extends GwtEvent<ToggleLayerRequestEvent.Handler> {

  public interface Handler extends EventHandler {
    void onLayerChangeRequest(ToggleLayerRequestEvent requestEvent);
  }

  public static final Type<Handler> TYPE = new Type<Handler>();

  /**
   * Register a {@link ToggleLayerRequestEvent.Handler} on an {@link EventBus}.
   * 
   * @param eventBus
   *          the {@link EventBus}
   * @param handler
   *          a {@link ToggleLayerRequestEvent.Handler}
   * @return a {@link HandlerRegistration} instance
   */
  public static HandlerRegistration register(EventBus eventBus, ToggleLayerRequestEvent.Handler handler) {
    return eventBus.addHandler(TYPE, handler);
  }

  private boolean isActive;
  private String layerName;

  public ToggleLayerRequestEvent(String layerName, boolean isActive) {
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
    handler.onLayerChangeRequest(this);
  }

}
