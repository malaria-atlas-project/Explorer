package uk.ac.ox.map.explorer.client.filter.presenter;


import com.google.gwt.event.shared.EventHandler;

public interface FilterChangedEventHandler extends EventHandler {
  void onFilterChanged(FilterChangedEvent event);
}
