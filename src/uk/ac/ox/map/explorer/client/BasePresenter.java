package uk.ac.ox.map.explorer.client;

import uk.ac.ox.map.explorer.client.activitymapper.MapActivityMapper;
import uk.ac.ox.map.explorer.client.activitymapper.TableActivityMapper;
import uk.ac.ox.map.explorer.client.base.view.BaseView;
import uk.ac.ox.map.explorer.client.base.view.CompositeMapView;
import uk.ac.ox.map.explorer.client.base.view.CompositeTableView;
import uk.ac.ox.map.explorer.client.list.presenter.SelectionPresenter;
import uk.ac.ox.map.explorer.client.place.EntityPlace;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.inject.Inject;

public class BasePresenter {

  private static final String ANOPHELES_DVS = "Anopheles DVS";
  private static final String PARASITE_RATE = "Parasite rate";

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
  public BasePresenter(final BaseView display, EventBus eventBus, MapActivityMapper appMapper, TableActivityMapper tableMapper) {

    this.display = display;
    
    /*
     * Create two activity mappers, one for each area, providing them with the appropriate widgets.
     * 
     */
    new ActivityManager(appMapper, eventBus).setDisplay(display.getMapDisplay());
    new ActivityManager(tableMapper, eventBus).setDisplay(display.getTableDisplay());
    
    
    /*
     * Populate the perspective selector.
     * 
     * Lots of hard-coding -- but it works.
     */
    final ListBox select = display.getPerspectiveSelect();
    select.addItem(PARASITE_RATE);
    select.addItem(ANOPHELES_DVS);
    
    EntityPlace ep = new EntityPlace.Tokenizer().getPlace(History.getToken());
    if (ep != null) {
      String name = ep.getEntityName();
      if (name.equals("EntityPlace:Country")) {
        select.setSelectedIndex(0);
      } else if (name.equals("EntityPlace:Anopheline")) {
        select.setSelectedIndex(1);
      }
    }
    
    select.addChangeHandler(new ChangeHandler() {
      @Override
      public void onChange(ChangeEvent event) {
        String val = select.getValue(select.getSelectedIndex());
        if (val.equals(PARASITE_RATE)) {
          History.newItem("EntityPlace:Country");
        } else if (val.equals(ANOPHELES_DVS)) {
          History.newItem("EntityPlace:Anopheline");
        }
      }
    });
    
  }
}
