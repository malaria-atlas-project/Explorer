package uk.ac.ox.map.explorer.client.event;

import uk.ac.ox.map.explorer.client.proxy.seed.CountryProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * An event posted when an authentication failure is detected.
 */
public class CountryCheckedEvent extends GwtEvent<CountryCheckedEvent.Handler> {
  
  public interface Handler extends EventHandler {
    void onCountryChecked(CountryCheckedEvent requestEvent);
  }

  public static final Type<Handler> TYPE = new Type<Handler>();

  /**
   * Register a {@link CountryCheckedEvent.Handler} on an {@link EventBus}.
   *
   * @param eventBus the {@link EventBus}
   * @param handler a {@link CountryCheckedEvent.Handler}
   * @return a {@link HandlerRegistration} instance
   */
  public static HandlerRegistration register(EventBus eventBus,
      CountryCheckedEvent.Handler handler) {
    return eventBus.addHandler(TYPE, handler);
  }

  private CountryProxy country;
  private boolean isChecked;


  public CountryCheckedEvent(CountryProxy country, boolean isChecked) {
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
  
  public CountryProxy getCountry() {
    return country;
  }

  @Override
  protected void dispatch(Handler handler) {
    handler.onCountryChecked(this);
  }

}
