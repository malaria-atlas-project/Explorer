package uk.ac.ox.map.explorer.client.list.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.ox.map.explorer.client.list.presenter.BaseTablePresenter;
import uk.ac.ox.map.explorer.client.proxy.NamedProxy;
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

/**
 * Encapsulates a {@link DataGrid} for the display of query data retrived with a
 * {@link BaseTablePresenter}
 * 
 * @author will
 * 
 * @param <T>
 *          the proxy to display
 */
public class TableView<T extends NamedProxy> extends Composite {
  
  @SuppressWarnings("rawtypes")
  interface TableViewUiBinder extends UiBinder<Widget, TableView> {
  }
  
  private static TableViewUiBinder uiBinder = GWT
      .create(TableViewUiBinder.class);
  
  @UiField(provided = true)
  DataGrid<T> dataGrid;
  
  protected BaseTablePresenter<T> presenter;
  
  protected SingleSelectionModel<T> selectionModel;
  
  private Map<Column<?, ?>, String> sortableColumns = new HashMap<Column<?, ?>, String>();
  
  public TableView(int pageSize) {
    DataGridResource resource = GWT.create(DataGridResource.class);
    dataGrid = new DataGrid<T>(pageSize, resource);
    
    initWidget(uiBinder.createAndBindUi(this));
    
    selectionModel = new SingleSelectionModel<T>();
    selectionModel
        .addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
          @Override
          public void onSelectionChange(SelectionChangeEvent event) {
            if (selectionModel.getSelectedObject() != null) {
              if (presenter != null) {
                presenter.fireObjectSelected(selectionModel.getSelectedObject());
              }
            }
          }
        });
    
    // DefaultSelectionEventManager<T> a =
    // DefaultSelectionEventManager.createBlacklistManager(1);
    // dataGrid.setSelectionModel(selectionModel, a);
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
  
  public void clearSelection() {
    selectionModel.setSelected(selectionModel.getSelectedObject(), false);
  }
  
  public DataGrid<T> getCellTable() {
    return dataGrid;
  }
  
  protected String getRepr(Object obj) {
    // Quick hack to avoid NPEs in cell columns
    if (obj == null) {
      return null;
    }
    return obj.toString();
  }
  
  public Map<Column<?, ?>, String> getSortableColumns() {
    return sortableColumns;
  }
  
  public void setPresenter(BaseTablePresenter<T> presenter) {
    this.presenter = presenter;
  }
  
  public void setResultCount(Long nResults) {
    dataGrid.setRowCount(nResults.intValue(), true);
  }
  
  public void setRowData(int i, List<T> values) {
    dataGrid.setRowData(i, values);
  }
  
  public void setSortedColumn(int i) {
    dataGrid.getColumnSortList().clear();
    dataGrid.getColumnSortList().push(dataGrid.getColumn(i));
  }
  
}
