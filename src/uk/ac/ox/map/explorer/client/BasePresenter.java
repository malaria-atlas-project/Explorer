package uk.ac.ox.map.explorer.client;

import uk.ac.ox.map.explorer.client.activitymapper.MapActivityMapper;
import uk.ac.ox.map.explorer.client.activitymapper.TableActivityMapper;
import uk.ac.ox.map.explorer.client.base.view.BaseView;
import uk.ac.ox.map.explorer.client.place.EntityPlace;
import uk.ac.ox.map.explorer.client.proxy.seed.RetrieveVars;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.inject.Inject;

public class BasePresenter {

  private static final String ANOPHELES_DVS = "Anopheles DVS";
  private static final String PARASITE_RATE = "Parasite rate";

  /**
   * The display implemented by the associated view.
   * 
   * @author will
   * 
   */
  public interface Display extends IsWidget {

    SimpleLayoutPanel getMapDisplay();

    SimpleLayoutPanel getTableDisplay();

  }

  /**
   * Bootstraps application by starting up two activity mappers and populating
   * the perpective switcher.
   * 
   * @param display
   * @param eventBus
   * @param appMapper
   * @param mapMapper
   */
  @Inject
  public BasePresenter(final BaseView display, EventBus eventBus, MapActivityMapper appMapper, TableActivityMapper tableMapper) {

    /*
     * Create two activity mappers, one for each area, providing them with the
     * appropriate widgets.
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
