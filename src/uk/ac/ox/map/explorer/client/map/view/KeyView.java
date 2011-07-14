package uk.ac.ox.map.explorer.client.map.view;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.map.presenter.KeyPresenter;
import uk.ac.ox.map.explorer.client.map.presenter.MapLayer;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class KeyView extends Composite {

  private static KeyViewUiBinder uiBinder = GWT.create(KeyViewUiBinder.class);

  interface KeyViewUiBinder extends UiBinder<Widget, KeyView> {
  }
  
  @UiField
  SimplePanel keyContainer;
  
  private CellList<MapLayer> layers;
  private List<MapLayer> layerList = new ArrayList<MapLayer>();

  private KeyPresenter listener;

  private LayerCell lc;


  public KeyView() {
    initWidget(uiBinder.createAndBindUi(this));
    lc = new LayerCell();
    layers = new CellList<MapLayer>(lc);
    keyContainer.add(layers);
  }
  
  public void setListener(KeyPresenter listener) {
    lc.setListener(listener);
  }
  
  public void addLayer(MapLayer ml) {
    layerList.add(ml);
    layers.setRowData(layerList);
  }

}
