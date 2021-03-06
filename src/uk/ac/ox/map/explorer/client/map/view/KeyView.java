package uk.ac.ox.map.explorer.client.map.view;

import java.util.List;

import uk.ac.ox.map.explorer.client.map.presenter.KeyPresenter;
import uk.ac.ox.map.explorer.client.proxy.MapLayer;
import uk.ac.ox.map.explorer.client.resource.ResourceBundle;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class KeyView extends Composite {
  
  interface KeyViewUiBinder extends UiBinder<Widget, KeyView> {
  }
  
  private static KeyViewUiBinder uiBinder = GWT.create(KeyViewUiBinder.class);
  
  @UiField
  SimplePanel keyContainer;
  
  private KeyPresenter listener;
  
  private final ResourceBundle resources;
  
  private final LayerInfoPopup popup;
  
  @Inject
  public KeyView(ResourceBundle resources, LayerInfoPopup popup) {
    initWidget(uiBinder.createAndBindUi(this));
    this.resources = resources;
    this.popup = popup;
  }
  
  public void setLayers(List<MapLayer> mls) {
    Grid grid = new Grid(mls.size(), 4);
    keyContainer.setWidget(grid);
    
    for (int i = 0; i < mls.size(); i++) {
      final MapLayer lyr = mls.get(i);
      
      /*
       * Add a checkbox to toggle layer visibility
       */
      CheckBox checkBox = new CheckBox();
      checkBox.setValue(true);
      checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
        @Override
        public void onValueChange(ValueChangeEvent<Boolean> event) {
          listener.toggleLayerVisibility(lyr.getWmsLayerName(),
              event.getValue());
        }
      });
      grid.setWidget(i, 0, checkBox);
      
      /*
       * Add image
       */
      grid.setWidget(i, 1, new Image(lyr.getImageUrl()));
      
      /*
       * Add key text
       */
      grid.setWidget(i, 2,
          new HTML(SafeHtmlUtils.fromTrustedString(lyr.getName())));
      
      /*
       * Info button
       */
      final InfoButton infoButton = new InfoButton(resources.infoButtonUp(),
          resources.infoButtonDown());
      
      infoButton.addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          
          popup.setMessage(lyr.getInfoText());
          popup.showRelativeTo(infoButton);
          
        }
      });
      
      grid.setWidget(i, 3, infoButton);
    }
  }
  
  public void setListener(KeyPresenter keyPresenter) {
    listener = keyPresenter;
  }
}
