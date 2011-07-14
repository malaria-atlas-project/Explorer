package uk.ac.ox.map.explorer.client.map.view;

import uk.ac.ox.map.explorer.client.map.presenter.MapInfoPresenter;
import uk.ac.ox.map.explorer.client.rpc.MapInfo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class MapInfoView extends Composite {

  private static MapInfoViewUiBinder uiBinder = GWT.create(MapInfoViewUiBinder.class);

  interface MapInfoViewUiBinder extends UiBinder<Widget, MapInfoView> {
  }

  public MapInfoView() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  @UiField
  Label siteCount;
  
  @UiField
  Label surveyCount;
  
  @UiField
  Label surveyReplicateCount;

  private MapInfoPresenter listener;

  public void showMapInfo(MapInfo mi) {
    siteCount.setText("" + mi.getSiteCount());
    surveyCount.setText("" + mi.getSurveyCount());
    surveyReplicateCount.setText("" + mi.getSurveyReplicateCount());
  }
  
  public void setListener(MapInfoPresenter mip) {
    this.listener = mip;
  }

}
