package uk.ac.ox.map.explorer.client.list.view;

import java.util.HashSet;
import java.util.Set;

import uk.ac.ox.map.request.client.proxy.CountryProxy;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class CountryTableView extends TableView<CountryProxy> {

  Set<CountryProxy> selected = new HashSet<CountryProxy>();

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

    Column<CountryProxy, Boolean> checkColumn = new Column<CountryProxy, Boolean>(new CheckboxCell(false, false)) {
      @Override
      public Boolean getValue(CountryProxy object) {
        return selected.contains(object);
      }
    };

    checkColumn.setFieldUpdater(new FieldUpdater<CountryProxy, Boolean>() {
      @Override
      public void update(int index, CountryProxy object, Boolean selectForDownload) {
        presenter.fireObjectChecked(object, selectForDownload);
      }
    });
    
    addColumn(checkColumn, "Select for download");

  }
  
}
