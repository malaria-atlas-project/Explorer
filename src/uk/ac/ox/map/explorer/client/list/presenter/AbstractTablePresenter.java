package uk.ac.ox.map.explorer.client.list.presenter;

import java.util.Map;

import uk.ac.ox.map.request.client.filter.presenter.FilterChangedEvent;
import uk.ac.ox.map.request.client.filter.presenter.FilterChangedEventHandler;
import uk.ac.ox.map.request.client.place.EntityPlace;
import uk.ac.ox.map.request.client.place.Order;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.ColumnSortList.ColumnSortInfo;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public abstract class AbstractTablePresenter<T> extends AbstractActivity  {

  protected final TableView<T> tableView;
  private final PlaceController placeController;
  protected AbstractDataProvider<T> dataProvider;
  protected EntityPlace place;

  public AbstractTablePresenter(PlaceController placeController, TableView<T> view) {
    this.placeController = placeController;
    this.tableView = view;
    view.setPresenter(this);
  }
  
  public Activity withPlace(EntityPlace place) {
    this.place = place;
    dataProvider.start(place);
    /*
     * Set ordering of table according to Place
     */
    if (!place.getOrderBy().isEmpty()) {
      Order o = new Order(place.getOrderBy());
      ColumnSortList csl = tableView.getCellTable().getColumnSortList();
      Map<Column<?, ?>, String> cols = tableView.getSortableColumns();
      for (Column<?, ?> col : cols.keySet()) {
        if (cols.get(col).equals(o.getColName())) {
          csl.push(new ColumnSortInfo(col, o.isAscending()));
        }
      }
    }
    return this;
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    tableView.clearSelection();
    panel.setWidget(tableView);

    eventBus.addHandler(FilterChangedEvent.TYPE, new FilterChangedEventHandler() {
      @Override
      public void onFilterChanged(FilterChangedEvent event) {
        placeController.goTo(new EntityPlace(place.getEntityName(), event.getFilterString(), place.getOrderBy(), false));
      }
    });
  }

  @Override
  public void onStop() {
    dataProvider.removeDataDisplay(tableView.getCellTable());
//    handlerRegistration.removeHandler();
  }

  @Override
  public void onCancel() {
    onStop();
  }

  public abstract void fireObjectSelected(T obj);

  /**
   * Column sorting is separate from filtering.
   * Therefore the new place can use the previous query string.
   */
  public void fireColumnSort(ColumnSortEvent event) {

    CellTable<T> cellTable = tableView.getCellTable();

    /*
     * Field name comes from map of columns stored in view.
     */
    String fieldName = tableView.getSortableColumns().get(event.getColumn());
    ColumnSortInfo colSortInfo = cellTable.getColumnSortList().get(0);
    Order o = new Order(fieldName, colSortInfo.isAscending());

    placeController.goTo(new EntityPlace(place.getEntityName(), place.getQueryString(), o.serialize(), false));
  }

}
