package uk.ac.ox.map.explorer.client.event;

import uk.ac.ox.map.explorer.client.event.DuffySelectedEvent.Handler;
import uk.ac.ox.map.explorer.client.proxy.CountryAllProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.GwtEvent.Type;

public class DuffySelectedEvent extends GwtEvent<DuffySelectedEvent.Handler> {

	public interface Handler extends EventHandler {
		void onCountrySelected(DuffySelectedEvent requestEvent);
	}

	public static final Type<Handler> TYPE = new Type<Handler>();

	/**
	 * Register a {@link CountrySelectedEvent.Handler} on an {@link EventBus}.
	 * 
	 * @param eventBus
	 *            the {@link EventBus}
	 * @param handler
	 *            a {@link CountrySelectedEvent.Handler}
	 * @return a {@link HandlerRegistration} instance
	 */
	public static HandlerRegistration register(EventBus eventBus,
			DuffySelectedEvent.Handler handler) {
		return eventBus.addHandler(TYPE, handler);
	}

	private CountryAllProxy country;

	public DuffySelectedEvent(CountryAllProxy Country) {
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

	public CountryAllProxy getCountry() {
		return country;
	}
}
