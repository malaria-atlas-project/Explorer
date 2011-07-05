package uk.ac.ox.map.explorer.list.presenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class TableView<T> extends Composite {

  private static TableViewUiBinder uiBinder = GWT.create(TableViewUiBinder.class);

  @SuppressWarnings("rawtypes")
  interface TableViewUiBinder extends UiBinder<Widget, TableView> {
  }

  public interface RowDataProvider<T> {
  
    void fireEditObject(T obj);
  
    void fireViewObject(T obj);
  
    void fireObjectSelected(T obj);
  
    void fireColumnSort(ColumnSortEvent colSortEvent);
  }

  @UiField
  CellTable<T> cellTable;

  @UiField(provided = true)
  SimplePager pager = new SimplePager();

  private AbstractTablePresenter<T> presenter;

  private SingleSelectionModel<T> selectionModel;
  
  private Map<Column<?, ?>, String> sortableColumns = new HashMap<Column<?,?>, String>();

  public TableView(int pageSize) {

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

    cellTable.setSelectionModel(selectionModel);
    cellTable.setPageSize(pageSize);
    
    cellTable.addColumnSortHandler(new ColumnSortEvent.Handler() {
      @Override
      public void onColumnSort(ColumnSortEvent event) {
        presenter.fireColumnSort(event);
      }
    });
    
  }

  public void addColumn(Column<T, ?> col, String title) {
    cellTable.addColumn(col, title);
  }
  
  public void addSortableColumn(Column<T, ?> col, String title, String fieldName) {
    cellTable.addColumn(col, title);
    col.setSortable(true);
    sortableColumns.put(col, fieldName);
  }

  public void setPresenter(AbstractTablePresenter<T> presenter) {
    this.presenter = presenter;
  }

  public void setRowData(int i, List<T> values) {
    cellTable.setRowData(i, values);
    pager.setPageStart(i);
  }

  public void setResultCount(Long nResults) {
    cellTable.setRowCount(nResults.intValue(), true);
  }

  public CellTable<T> getCellTable() {
    return cellTable;
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
