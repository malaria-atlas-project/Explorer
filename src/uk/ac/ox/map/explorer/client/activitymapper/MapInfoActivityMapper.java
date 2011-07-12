package uk.ac.ox.map.explorer.client.activitymapper;


import uk.ac.ox.map.explorer.client.map.presenter.MapInfoPresenter;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class MapInfoActivityMapper implements ActivityMapper {

  @Inject
  Provider<MapInfoPresenter> mapInfoProvider;

  @Override
  public Activity getActivity(Place place) {
    return mapInfoProvider.get();
  }

}
