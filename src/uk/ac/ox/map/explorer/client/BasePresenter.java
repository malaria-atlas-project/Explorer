package uk.ac.ox.map.explorer.client;

import uk.ac.ox.map.explorer.client.activitymapper.MapActivityMapper;
import uk.ac.ox.map.explorer.client.activitymapper.TableActivityMapper;
import uk.ac.ox.map.explorer.client.base.view.CompositeMapView;
import uk.ac.ox.map.explorer.client.base.view.CompositeTableView;
import uk.ac.ox.map.explorer.client.list.presenter.SelectionPresenter;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.inject.Inject;

public class BasePresenter {

  public interface Display extends IsWidget {

    HasText getCurrentPlace();

    void clearBreadCrumbs();

    void addBreadCrumb(String label, String historyToken);

    SimpleLayoutPanel getMapDisplay();

    SimpleLayoutPanel getTableDisplay();

  }

  private Display display;

  /**
   * Bootstraps application. TODO: new pattern of singleton presenter - probably
   * need to move to activity mapper. Also should re-think how to deal with
   * singleton presenters in ActivityMappers - currently they'll remove and add
   * themselves on each place change.
   * 
   * 
   * @param display
   * @param eventBus
   * @param appMapper
   * @param mapMapper
   */
  @Inject
  public BasePresenter(final Display display, EventBus eventBus, MapActivityMapper appMapper, TableActivityMapper tableMapper, SelectionPresenter selectionPresenter) {

    this.display = display;
//    RootLayoutPanel rlp = RootLayoutPanel.get();
//    SimpleLayoutPanel slp = new SimpleLayoutPanel();
//    rlp.add(slp);
    
    new ActivityManager(appMapper, eventBus).setDisplay(display.getMapDisplay());
    new ActivityManager(tableMapper, eventBus).setDisplay(display.getTableDisplay());

  }

}
