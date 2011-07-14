package uk.ac.ox.map.explorer.client;

import uk.ac.ox.map.explorer.client.activitymapper.MapActivityMapper;
import uk.ac.ox.map.explorer.client.activitymapper.TableActivityMapper;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;

public class BasePresenter {

  public interface Display extends IsWidget {
    
    HasText getCurrentPlace();

    void clearBreadCrumbs();

    void setSuccessMessage(String string);

    void addBreadCrumb(String label, String historyToken);

    AcceptsOneWidget getMapDisplay();

    AcceptsOneWidget getTableDisplay();
    
  }

  private Display display;

  /**
   * @param appWidget probably pointless as could just have it in base view. //TODO- remove this. 
   * @param display
   * @param compositeMapView
   * @param eventBus
   * @param appMapper
   * @param subMapper
   */
  @Inject
  public BasePresenter(final Display display, EventBus eventBus, MapActivityMapper appMapper, TableActivityMapper subMapper) {
    
    this.display = display;
    
    new ActivityManager(appMapper, eventBus).setDisplay(display.getMapDisplay());
    new ActivityManager(subMapper, eventBus).setDisplay(display.getTableDisplay());
    
  }

}
