package uk.ac.ox.map.explorer.client.map.view;

import uk.ac.ox.map.explorer.client.rpc.MapInfo;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class MapInfoView extends Composite {
  
  interface MapInfoViewUiBinder extends UiBinder<Widget, MapInfoView> {
  }
  
  private static MapInfoViewUiBinder uiBinder = GWT
      .create(MapInfoViewUiBinder.class);
  
  @UiField
  Label siteCount;
  
  @UiField
  Label surveyReplicateCount;
  
  public MapInfoView() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  public void showMapInfo(MapInfo mi) {
    siteCount.setText("" + mi.getSiteCount());
    surveyReplicateCount.setText("" + mi.getSurveyReplicateCount());
  }
  
}
