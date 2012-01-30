package uk.ac.ox.map.explorer.client.filter.presenter;


import com.google.gwt.event.shared.GwtEvent;

public class FilterChangedEvent extends GwtEvent<FilterChangedEventHandler>{
  public static Type<FilterChangedEventHandler> TYPE = new Type<FilterChangedEventHandler>();
  private String filterString;
  
  public FilterChangedEvent(String filterString) {
    this.filterString = filterString;
  }
  
  @Override
  public Type<FilterChangedEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(FilterChangedEventHandler handler) {
    handler.onFilterChanged(this);
  }

  public String getFilterString() {
    return filterString;
  }
}
