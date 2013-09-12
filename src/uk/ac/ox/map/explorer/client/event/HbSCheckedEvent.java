package uk.ac.ox.map.explorer.client.event;

import uk.ac.ox.map.explorer.client.event.HbSCheckedEvent.Handler;
import uk.ac.ox.map.explorer.client.proxy.CountryAllProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * Fired when a {@link CountryAllProxy} is checked
 */

public class HbSCheckedEvent extends GwtEvent<HbSCheckedEvent.Handler> {
	  
	  public interface Handler extends EventHandler {
	    void onCountryChecked(HbSCheckedEvent requestEvent);
	  }
	  
	  public static final Type<Handler> TYPE = new Type<Handler>();
	  
	  /**
	   * Register a {@link CountryCheckedEvent.Handler} on an {@link EventBus}.
	   * 
	   * @param eventBus
	   *          the {@link EventBus}
	   * @param handler
	   *          a {@link CountryCheckedEvent.Handler}
	   * @return a {@link HandlerRegistration} instance
	   */
	  public static HandlerRegistration register(EventBus eventBus,
	      HbSCheckedEvent.Handler handler) {
	    return eventBus.addHandler(TYPE, handler);
	  }
	  
	  private CountryAllProxy country;
	  private boolean isChecked;
	  
	  public HbSCheckedEvent(CountryAllProxy country, boolean isChecked) {
	    this.isChecked = isChecked;
	    this.country = country;
	  }
	  
	  @Override
	  protected void dispatch(Handler handler) {
	    handler.onCountryChecked(this);
	  }
	  
	  @Override
	  public GwtEvent.Type<Handler> getAssociatedType() {
	    return TYPE;
	  }
	  
	  public CountryAllProxy getCountry() {
	    return country;
	  }
	  
	  public boolean isChecked() {
	    return isChecked;
	  }
}
