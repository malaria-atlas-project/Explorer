package uk.ac.ox.map.explorer.client.activitymapper;

import uk.ac.ox.map.explorer.client.map.presenter.AnophelesMapPresenter;
import uk.ac.ox.map.explorer.client.map.presenter.CountryMapPresenter;
import uk.ac.ox.map.explorer.client.map.presenter.G6PDMapPresenter;
import uk.ac.ox.map.explorer.client.map.presenter.HbSMapPresenter;
import uk.ac.ox.map.explorer.client.map.presenter.DuffyMapPresenter;
import uk.ac.ox.map.explorer.client.map.presenter.PvPRMapPresenter;
import uk.ac.ox.map.explorer.client.place.EntityPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * A singleton {@link ActivityMapper} means singleton map
 * 
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
  
  @Inject
  G6PDMapPresenter g6PDMapPresenter;
  
  @Inject
  HbSMapPresenter hbSMapPresenter;
  
  @Inject
  DuffyMapPresenter duffyMapPresenter;
  
  @Inject
  PvPRMapPresenter pvPRMapPresenter;
  
  
  @Override
  public Activity getActivity(Place place) {
    
    EntityPlace p = (EntityPlace) place;
    String entityName = p.getEntityName();
    
    if (entityName.equals("Country")) {
      return countryMapPresenter;
    } else if (entityName.equals("Anopheline")) {
      return anoMapPresenter;
    } else if (entityName.equals("G6PD")) {
        return g6PDMapPresenter;
    } else if (entityName.equals("HbS")) {
          return hbSMapPresenter;
    } else if (entityName.equals("Duffy")) {
		return duffyMapPresenter;
	} else if (entityName.equals("PvPR")) {
		return pvPRMapPresenter;
	}
    return countryMapPresenter;
  }
  
}
