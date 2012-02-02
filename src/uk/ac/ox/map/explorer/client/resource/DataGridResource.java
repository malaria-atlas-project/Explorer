package uk.ac.ox.map.explorer.client.resource;

import com.google.gwt.user.cellview.client.DataGrid;

/**
 * Overrides a few default CSS styles
 * 
 * @author will
 *
 */
public interface DataGridResource extends DataGrid.Resources {

  @Source({ DataGrid.Style.DEFAULT_CSS, "DataGridOverride.css" })
  DataGrid.Style dataGridStyle();
};
