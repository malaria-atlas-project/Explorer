package uk.ac.ox.map.explorer.client.activitymapper;


import uk.ac.ox.map.explorer.client.map.presenter.MapPresenter;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Singleton;


/**
 * Essentially have a singleton pattern for mapPresenter (as the activity mapper is a singleton).
 * 
 * May in future 
 * 
 * @author will
 *
 */
@Singleton
public class MapActivityMapper implements ActivityMapper {

  @Inject
  MapPresenter mapPresenter;

  @Override
  public Activity getActivity(Place place) {
    return mapPresenter;
  }

}
