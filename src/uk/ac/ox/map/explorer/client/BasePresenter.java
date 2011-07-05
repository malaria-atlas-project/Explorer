package uk.ac.ox.map.explorer.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.http.client.URL;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;

public class BasePresenter {

  public interface Display extends IsWidget {
    
    HasClickHandlers getLogout();

    HasText getCurrentPlace();

    void clearBreadCrumbs();

    void setSuccessMessage(String string);

    void addBreadCrumb(String label, String historyToken);
    
  }

  private Display display;
  private PlaceHistoryMapper historyMapper;

  @Inject
  public BasePresenter(final Display display, EventBus eventBus, PlaceHistoryMapper historyMapper) {
    this.display = display;
    this.historyMapper = historyMapper;
    
    display.getLogout().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        
        String href = Window.Location.getHref();
        StringBuilder sb = new StringBuilder();
        sb.append(href);
        sb.delete(href.lastIndexOf('/'), sb.length());
        sb.append("/login?action=logout");
        
        Window.Location.replace(sb.toString());
      }
    });

    eventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {
      
      /**
       * Updates the breadcrumbs using the current place.
       * 
       * TODO: consider base Place with a getDisplayName() method
       * 
       */
      @Override
      public void onPlaceChange(PlaceChangeEvent event) {
        Place place = event.getNewPlace();
        String placeName;

      }
    });
    
    
  }

  private void addHomeBreadCrumb() {
    display.clearBreadCrumbs();
    display.addBreadCrumb("Home", "");
  }
}
