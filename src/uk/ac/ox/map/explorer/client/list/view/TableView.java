package uk.ac.ox.map.explorer.client.list.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.ox.map.explorer.client.list.presenter.AbstractTablePresenter;
import uk.ac.ox.map.explorer.client.resource.DataGridResource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class TableView<T> extends Composite {

  private static TableViewUiBinder uiBinder = GWT.create(TableViewUiBinder.class);

  @SuppressWarnings("rawtypes")
  interface TableViewUiBinder extends UiBinder<Widget, TableView> {
  }

  @UiField(provided = true)
  DataGrid<T> dataGrid;


  protected AbstractTablePresenter<T> presenter;

  protected SingleSelectionModel<T> selectionModel;
  
  private Map<Column<?, ?>, String> sortableColumns = new HashMap<Column<?,?>, String>();

  public TableView(int pageSize) {
    DataGridResource resource = GWT.create(DataGridResource.class);
    dataGrid = new DataGrid<T>(pageSize, resource);

    initWidget(uiBinder.createAndBindUi(this));
    
    selectionModel = new SingleSelectionModel<T>();
    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      @Override
      public void onSelectionChange(SelectionChangeEvent event) {
        if (selectionModel.getSelectedObject() != null) {
          if (presenter != null) {
            presenter.fireObjectSelected(selectionModel.getSelectedObject());
          }
        }
      }
    });

    dataGrid.setSelectionModel(selectionModel);
    dataGrid.setPageSize(pageSize);
    
    dataGrid.addColumnSortHandler(new ColumnSortEvent.Handler() {
      @Override
      public void onColumnSort(ColumnSortEvent event) {
        presenter.fireColumnSort(event);
      }
    });
    
  }

  public void addColumn(Column<T, ?> col, String title) {
    dataGrid.addColumn(col, title);
  }
  
  
  public void addSortableColumn(Column<T, ?> col, String title, String fieldName) {
    dataGrid.addColumn(col, title);
    col.setSortable(true);
    sortableColumns.put(col, fieldName);
  }

  public void setPresenter(AbstractTablePresenter<T> presenter) {
    this.presenter = presenter;
  }

  public void setRowData(int i, List<T> values) {
    dataGrid.setRowData(i, values);
  }

  public void setResultCount(Long nResults) {
    dataGrid.setRowCount(nResults.intValue(), true);
  }

  public DataGrid<T> getCellTable() {
    return dataGrid;
  }
  
  public void clearSelection() {
    selectionModel.setSelected(selectionModel.getSelectedObject(), false);
  }
  
  public Map<Column<?, ?>, String> getSortableColumns() {
    return sortableColumns;
  }
  
  protected String getRepr(Object obj) {
  // Quick hack to avoid NPEs in cell columns
    if (obj == null) {
      return null;
    }
    return obj.toString();
  }

}
