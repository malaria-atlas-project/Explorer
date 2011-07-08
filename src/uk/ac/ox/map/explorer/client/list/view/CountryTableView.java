package uk.ac.ox.map.explorer.client.list.view;

import com.google.gwt.user.cellview.client.TextColumn;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

import uk.ac.ox.map.explorer.client.list.presenter.TableView;
import uk.ac.ox.map.request.client.proxy.CountryProxy;

@Singleton
public class CountryTableView extends TableView<CountryProxy> {

  @Inject
  public CountryTableView(@Named("TABLE_SIZE") Integer pageSize) {
    super(pageSize);

    addSortableColumn(new TextColumn<CountryProxy>() {

      public String getValue(CountryProxy object) {
        return getRepr(object.getId());
      }
    }, "Id", "id");

    addSortableColumn(new TextColumn<CountryProxy>() {

      public String getValue(CountryProxy object) {
        return getRepr(object.getName());
      }
    }, "Name", "name");

    addSortableColumn(new TextColumn<CountryProxy>() {

      public String getValue(CountryProxy object) {
        return getRepr(object.getContinent());
      }
    }, "Continent", "continent");

    addColumn(new TextColumn<CountryProxy>() {

      public String getValue(CountryProxy object) {
        if (object.getRegion() != null) {
          return getRepr(object.getRegion().getName());
        }
        return null;
      }
    }, "Region");
  }

}
