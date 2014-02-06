package uk.ac.ox.map.explorer.client.event;


import uk.ac.ox.map.explorer.client.proxy.CountryAllProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class DuffyCheckedEvent extends GwtEvent<DuffyCheckedEvent.Handler> {

	public interface Handler extends EventHandler {
	    void onCountryChecked(DuffyCheckedEvent requestEvent);
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
	      DuffyCheckedEvent.Handler handler) {
	    return eventBus.addHandler(TYPE, handler);
	  }
	  
	  private CountryAllProxy country;
	  private boolean isChecked;
	  
	  public DuffyCheckedEvent(CountryAllProxy country, boolean isChecked) {
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
