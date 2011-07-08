package com.hellomulti.client.mvp;

import uk.ac.ox.map.explorer.client.HomePagePresenter;
import uk.ac.ox.map.request.client.place.HomePagePlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class MainActivityMapper implements ActivityMapper {

  @Inject
  Provider<HomePagePresenter> homePageProvider;


  @Override
  public Activity getActivity(Place place) {
    
    if (place instanceof HomePagePlace) {
      return homePageProvider.get();
    }
    return null;
  }

}
