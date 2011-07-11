package uk.ac.ox.map.explorer.client;


import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;


/**
 * 
 * Currently a placeholder, just returns a map widget. Will be useful in the future if different map widgets are required.
 * 
 * @author will
 *
 */
@Singleton
public class AppActivityMapper implements ActivityMapper {

  @Inject
  Provider<MapPresenter> homePageProvider;

  @Override
  public Activity getActivity(Place place) {
	    return homePageProvider.get();
  }

}
