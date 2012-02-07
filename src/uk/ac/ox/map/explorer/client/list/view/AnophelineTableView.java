package uk.ac.ox.map.explorer.client.list.view;

import java.util.HashSet;
import java.util.Set;

import uk.ac.ox.map.explorer.client.proxy.AnophelineProxy;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.Column;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class AnophelineTableView extends TableView<AnophelineProxy> {

  Set<Long> selected = new HashSet<Long>();

  @Inject
  public AnophelineTableView(@Named("TABLE_SIZE") Integer pageSize) {
    super(pageSize);
    
    Column<AnophelineProxy, SafeHtml> name = new Column<AnophelineProxy, SafeHtml>(new SafeHtmlCell()) {
      @Override
      public SafeHtml getValue(AnophelineProxy object) {
        return SafeHtmlUtils.fromTrustedString(object.getName());
      }
    };

    addSortableColumn(name, "Name", "name");
    
    

    Column<AnophelineProxy, Boolean> checkColumn = new Column<AnophelineProxy, Boolean>(new CheckboxCell(false, false)) {
      @Override
      public Boolean getValue(AnophelineProxy object) {
        return selected.contains(object.getId());
      }
    };

    checkColumn.setFieldUpdater(new FieldUpdater<AnophelineProxy, Boolean>() {
      @Override
      public void update(int index, AnophelineProxy object, Boolean selectForDownload) {
        presenter.fireObjectChecked(object, selectForDownload);
        if (selectForDownload) {
          selected.add(object.getId());
        } else {
          selected.remove(object.getId());
        }
      }
    });
    
    addColumn(checkColumn, "Select for download");

  }
  
}
