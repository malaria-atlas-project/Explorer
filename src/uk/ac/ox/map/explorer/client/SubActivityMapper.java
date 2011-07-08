package uk.ac.ox.map.explorer.client;


import uk.ac.ox.map.explorer.client.list.presenter.CountryPresenter;
import uk.ac.ox.map.request.client.place.EntityPlace;
import uk.ac.ox.map.request.client.place.HomePagePlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class SubActivityMapper implements ActivityMapper {

  @Inject
  Provider<CountryPresenter> countryProvider;

  @Override
  public Activity getActivity(Place place) {
    if (place instanceof EntityPlace) {
	    return countryProvider.get().withPlace((EntityPlace) place);
    }
    return null;
  }

}
