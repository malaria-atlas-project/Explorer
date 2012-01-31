package uk.ac.ox.map.explorer.client.activitymapper;


import uk.ac.ox.map.explorer.client.map.presenter.AnophelesMapPresenter;
import uk.ac.ox.map.explorer.client.map.presenter.CountryMapPresenter;
import uk.ac.ox.map.explorer.client.place.EntityPlace;

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
  CountryMapPresenter countryMapPresenter;
  
  @Inject
  AnophelesMapPresenter anoMapPresenter;

  @Override
  public Activity getActivity(Place place) {
    
    EntityPlace p = (EntityPlace) place;
    String entityName = p.getEntityName();
    
    if (entityName.equals("Country")) {
      return countryMapPresenter;
    } else if (entityName.equals("Anopheline")) {
      return anoMapPresenter;
    }
    return countryMapPresenter;
  }

}
