package uk.ac.ox.map.explorer.client.activitymapper;

import uk.ac.ox.map.explorer.client.list.presenter.AnophelesTablePresenter;
import uk.ac.ox.map.explorer.client.list.presenter.CountryTablePresenter;
import uk.ac.ox.map.explorer.client.list.presenter.G6PDTablePresenter;
import uk.ac.ox.map.explorer.client.list.presenter.HbSTablePresenter;
import uk.ac.ox.map.explorer.client.place.EntityPlace;

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
  
  @Inject
  Provider<G6PDTablePresenter> g6PDTableProvider;
  
  @Inject
  Provider<HbSTablePresenter> hbSTableProvider;
  
  @Override
  public Activity getActivity(Place place) {
    
    EntityPlace p = (EntityPlace) place;
    String entityName = p.getEntityName();
    
    if (entityName.equals("Country")) {
      return countryTableProvider.get().withPlace(p);
    } else if (entityName.equals("Anopheline")) {
      return anophelineTableProvider.get().withPlace(p);
    } else if (entityName.equals("G6PD")) {
        return g6PDTableProvider.get().withPlace(p);
      } else if (entityName.equals("HbS")) {
          return hbSTableProvider.get().withPlace(p);
        }
    return null;
  }
  
}
