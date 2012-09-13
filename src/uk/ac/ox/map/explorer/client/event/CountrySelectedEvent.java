package uk.ac.ox.map.explorer.client.event;

import uk.ac.ox.map.explorer.client.proxy.CountryProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * Fired when a {@link CountryProxy} is selected.
 */
public class CountrySelectedEvent extends
    GwtEvent<CountrySelectedEvent.Handler> {
  
  public interface Handler extends EventHandler {
    void onCountrySelected(CountrySelectedEvent requestEvent);
  }
  
  public static final Type<Handler> TYPE = new Type<Handler>();
  
  /**
   * Register a {@link CountrySelectedEvent.Handler} on an {@link EventBus}.
   * 
   * @param eventBus
   *          the {@link EventBus}
   * @param handler
   *          a {@link CountrySelectedEvent.Handler}
   * @return a {@link HandlerRegistration} instance
   */
  public static HandlerRegistration register(EventBus eventBus,
      CountrySelectedEvent.Handler handler) {
    return eventBus.addHandler(TYPE, handler);
  }
  
  private CountryProxy country;
  
  public CountrySelectedEvent(CountryProxy Country) {
    country = Country;
  }
  
  @Override
  protected void dispatch(Handler handler) {
    handler.onCountrySelected(this);
  }
  
  @Override
  public GwtEvent.Type<Handler> getAssociatedType() {
    return TYPE;
  }
  
  public CountryProxy getCountry() {
    return country;
  }
  
}
