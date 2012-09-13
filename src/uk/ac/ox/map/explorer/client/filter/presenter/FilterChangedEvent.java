package uk.ac.ox.map.explorer.client.filter.presenter;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Generally fired by a {@link FilterPresenter} when a {@link Filter} has
 * changed, which may trigger immediate data reloading.
 * 
 * @author will
 */
public class FilterChangedEvent extends GwtEvent<FilterChangedEvent.Handler> {
  
  public interface Handler extends EventHandler {
    void onFilterChanged(FilterChangedEvent event);
  }
  
  public static Type<FilterChangedEvent.Handler> TYPE = new Type<FilterChangedEvent.Handler>();
  private String filterString;
  
  public FilterChangedEvent(String filterString) {
    this.filterString = filterString;
  }
  
  @Override
  protected void dispatch(FilterChangedEvent.Handler handler) {
    handler.onFilterChanged(this);
  }
  
  @Override
  public Type<FilterChangedEvent.Handler> getAssociatedType() {
    return TYPE;
  }
  
  public String getFilterString() {
    return filterString;
  }
}
