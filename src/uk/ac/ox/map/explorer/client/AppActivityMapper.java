package uk.ac.ox.map.explorer.client;


import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class AppActivityMapper implements ActivityMapper {

  /*
   * Editors
   */
  @Inject
  Provider<HomePagePresenter> homePageProvider;

  @Override
  public Activity getActivity(Place place) {
    return homePageProvider.get();
  }

}
