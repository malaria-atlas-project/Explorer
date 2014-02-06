package uk.ac.ox.map.explorer.client;

import uk.ac.ox.map.explorer.client.activitymapper.MapActivityMapper;
import uk.ac.ox.map.explorer.client.activitymapper.TableActivityMapper;
import uk.ac.ox.map.explorer.client.base.view.BaseView;
import uk.ac.ox.map.explorer.client.place.EntityPlace;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.inject.Inject;

/**
 * Bootstraps application by starting up two activity mappers and populating the
 * perpective switcher.
 * 
 * @param display
 * @param eventBus
 * @param appMapper
 * @param mapMapper
 */
public class BasePresenter {
  
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
  
  private static final String ANOPHELES_DVS = "Anopheles DVS";
  
  private static final String PARASITE_RATE = "Pf Parasite Rate";
  
  private static final String G6PD_DEFICIENCY = "G6PD Deficiency";
  
  private static final String HBS_FREQ = "Sickle Cell";
  
  private static final String DUFFY_NEG = "Duffy Negativity";
  
  private static final String PV_PARASITE_RATE = "Pv Parasite Rate";
  
  @Inject
  public BasePresenter(final BaseView display, EventBus eventBus,
      MapActivityMapper appMapper, TableActivityMapper tableMapper) {
    
    /*
     * Create two activity mappers, one for each area, providing them with the
     * appropriate widgets.
     */
    new ActivityManager(appMapper, eventBus)
        .setDisplay(display.getMapDisplay());
    new ActivityManager(tableMapper, eventBus).setDisplay(display
        .getTableDisplay());
    
    /*
     * Populate the perspective selector.
     * 
     * Lots of hard-coding -- but it works.
     */
    final ListBox select = display.getPerspectiveSelect();
    select.addItem(PARASITE_RATE);
    select.addItem(ANOPHELES_DVS);
    select.addItem(G6PD_DEFICIENCY);
    select.addItem(HBS_FREQ);
    select.addItem(DUFFY_NEG);
    select.addItem(PV_PARASITE_RATE);
    
    EntityPlace ep = new EntityPlace.Tokenizer().getPlace(History.getToken());
    if (ep != null) {
      String name = ep.getEntityName();
      if (name.equals("EntityPlace:Country")) {
        select.setSelectedIndex(0);
      } else if (name.equals("EntityPlace:Anopheline")) {
        select.setSelectedIndex(1);
      } else if (name.equals("EntityPlace:G6PD")) {
          select.setSelectedIndex(2);
      } else if (name.equals("EntityPlace:HbS")) {
          select.setSelectedIndex(3);
      } else if (name.equals("EntityPlace:Duffy")) {
    	  select.setSelectedIndex(4);
      } else if (name.equals("EntityPlace:PvPR")) {
    	  select.setSelectedIndex(5);
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
        } else if (val.equals(G6PD_DEFICIENCY)) {
            History.newItem("EntityPlace:G6PD");
        } else if (val.equals(HBS_FREQ)) {
            History.newItem("EntityPlace:HbS");
        } else if (val.equals(DUFFY_NEG)) {
        	History.newItem("EntityPlace:Duffy");
		} else if (val.equals(PV_PARASITE_RATE)) {
			History.newItem("EntityPlace:PvPR");
		}
			
      }
    });
    
  }
}
