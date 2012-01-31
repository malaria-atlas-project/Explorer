package uk.ac.ox.map.explorer.client.activitymapper;


import uk.ac.ox.map.explorer.client.list.presenter.AnophelesTablePresenter;
import uk.ac.ox.map.explorer.client.list.presenter.CountryTablePresenter;
import uk.ac.ox.map.explorer.client.map.presenter.CountryMapPresenter;
import uk.ac.ox.map.explorer.client.place.EntityPlace;
import uk.ac.ox.map.explorer.client.place.HomePagePlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class TableActivityMapper implements ActivityMapper {

  @Inject
  Provider<CountryTablePresenter> countryTableProvider;
  
  @Inject
  Provider<AnophelesTablePresenter> anophelineTableProvider;
  

  @Override
  public Activity getActivity(Place place) {
    
    EntityPlace p = (EntityPlace) place;
    String entityName = p.getEntityName();
    
    if (entityName.equals("Country")) {
	    return countryTableProvider.get().withPlace(p);
    } else if (entityName.equals("Anopheline")) {
	    return anophelineTableProvider.get().withPlace(p);
    }
    return null;
  }

}
