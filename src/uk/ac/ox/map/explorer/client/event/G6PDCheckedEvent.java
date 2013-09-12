package uk.ac.ox.map.explorer.client.event;

import uk.ac.ox.map.explorer.client.event.G6PDCheckedEvent.Handler;
import uk.ac.ox.map.explorer.client.proxy.CountryProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent.Type;

/**
 * Fired when a {@link CountryProxy} is checked
 */

public class G6PDCheckedEvent extends GwtEvent<G6PDCheckedEvent.Handler> {
	  
	  public interface Handler extends EventHandler {
	    void onCountryChecked(G6PDCheckedEvent requestEvent);
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
	      G6PDCheckedEvent.Handler handler) {
	    return eventBus.addHandler(TYPE, handler);
	  }
	  
	  private CountryProxy country;
	  private boolean isChecked;
	  
	  public G6PDCheckedEvent(CountryProxy country, boolean isChecked) {
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
	  
	  public CountryProxy getCountry() {
	    return country;
	  }
	  
	  public boolean isChecked() {
	    return isChecked;
	  }
}
