package uk.ac.ox.map.explorer.client.list.view;

import java.util.HashSet;
import java.util.Set;

import uk.ac.ox.map.explorer.client.proxy.AnophelineProxy;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class AnophelineTableView extends TableView<AnophelineProxy> {

  Set<AnophelineProxy> selected = new HashSet<AnophelineProxy>();

  @Inject
  public AnophelineTableView(@Named("TABLE_SIZE") Integer pageSize) {
    super(pageSize);

    addSortableColumn(new TextColumn<AnophelineProxy>() {

      public String getValue(AnophelineProxy object) {
        return getRepr(object.getName());
      }
    }, "Name", "name");

    Column<AnophelineProxy, Boolean> checkColumn = new Column<AnophelineProxy, Boolean>(new CheckboxCell(false, false)) {
      @Override
      public Boolean getValue(AnophelineProxy object) {
        return selected.contains(object);
      }
    };

    checkColumn.setFieldUpdater(new FieldUpdater<AnophelineProxy, Boolean>() {
      @Override
      public void update(int index, AnophelineProxy object, Boolean selectForDownload) {
        presenter.fireObjectChecked(object, selectForDownload);
      }
    });
    
    addColumn(checkColumn, "Select for download");

  }
  
}
