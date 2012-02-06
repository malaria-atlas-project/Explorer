package uk.ac.ox.map.explorer.client.list.presenter;

import java.util.Map;

import uk.ac.ox.map.explorer.client.base.view.CompositeTableView;
import uk.ac.ox.map.explorer.client.filter.presenter.FilterChangedEvent;
import uk.ac.ox.map.explorer.client.filter.presenter.FilterPresenter;
import uk.ac.ox.map.explorer.client.list.view.BaseCompositeFilter;
import uk.ac.ox.map.explorer.client.list.view.TableView;
import uk.ac.ox.map.explorer.client.place.EntityPlace;
import uk.ac.ox.map.explorer.client.place.Order;
import uk.ac.ox.map.explorer.client.proxy.NamedProxy;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.ColumnSortList.ColumnSortInfo;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * Manages overall wiring of the table-based UI, including filtering, display and selection.
 * 
 * @author will
 */
public abstract class BaseTablePresenter<T extends NamedProxy> extends AbstractActivity {

  protected final TableView<T> tableView;
  private final PlaceController placeController;
  protected final AbstractDataProvider<T> dataProvider;
  protected EntityPlace place;
  protected final BaseCompositeFilter filterList;

  @Inject
  protected EventBus eventBus;

  protected BaseSelectionPresenter<T> selectionPresenter;

  protected final CompositeTableView compositeTableView;

  public BaseTablePresenter(PlaceController placeController, TableView<T> view, BaseCompositeFilter filterList, BaseSelectionPresenter<T> baseSelectionPresenter, AbstractDataProvider<T> dp) {
    this.placeController = placeController;
    this.tableView = view;
    this.compositeTableView = new CompositeTableView();
    this.selectionPresenter = baseSelectionPresenter;
    this.dataProvider = dp;

    this.filterList = filterList;
    view.setPresenter(this);

  }

  /**
   * Convenience method for providing the place as well as the view when calling
   * start on an {@link AbstractActivity}. This just makes it a one-liner.
   * 
   * @param place
   * @return
   */
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

    panel.setWidget(compositeTableView);
    tableView.clearSelection();
    compositeTableView.getTablePanel().setWidget(tableView);

    eventBus.addHandler(FilterChangedEvent.TYPE, new FilterChangedEvent.Handler() {
      @Override
      public void onFilterChanged(FilterChangedEvent event) {
        placeController.goTo(new EntityPlace(place.getEntityName(), event.getFilterString(), place.getOrderBy(), false));
      }
    });

    FilterPresenter filterPresenter = new FilterPresenter(place, filterList);
    filterPresenter.start(compositeTableView.getFilterPanel(), eventBus);

    selectionPresenter.start(compositeTableView.getSelectionPanel(), eventBus);

    this.eventBus = eventBus;
  }

  @Override
  public void onStop() {
    dataProvider.removeDataDisplay(tableView.getCellTable());
    // handlerRegistration.removeHandler();
  }

  @Override
  public void onCancel() {
    onStop();
  }

  public abstract void fireObjectSelected(T obj);

  public abstract void fireObjectChecked(T obj, boolean isChecked);

  /**
   * Column sorting is separate from filtering. Therefore the new place can use
   * the previous query string.
   */
  public void fireColumnSort(ColumnSortEvent event) {

    DataGrid<T> cellTable = tableView.getCellTable();

    /*
     * Field name comes from map of columns stored in view.
     */
    String fieldName = tableView.getSortableColumns().get(event.getColumn());
    ColumnSortInfo colSortInfo = cellTable.getColumnSortList().get(0);
    Order o = new Order(fieldName, colSortInfo.isAscending());

    placeController.goTo(new EntityPlace(place.getEntityName(), place.getQueryString(), o.serialize(), false));
  }

}
