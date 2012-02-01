package uk.ac.ox.map.explorer.client.base.view;

import uk.ac.ox.map.explorer.client.BasePresenter;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BaseView extends Composite implements BasePresenter.Display {

  private static BaseViewUiBinder uiBinder = GWT.create(BaseViewUiBinder.class);

  interface BaseViewUiBinder extends UiBinder<Widget, BaseView> {
  }

  interface BaseStyle extends CssResource {
    
    String inlineHyperlink();

    String spacer();
  }

  @UiField
  BaseStyle style;
  
  @UiField
  SimpleLayoutPanel tablePanel;
  
  @UiField
  SimpleLayoutPanel mapPanel;
  
  @UiField
  ListBox perspectiveSelect;

  @Inject
  public BaseView() {
    initWidget(uiBinder.createAndBindUi(this));
    
  }

  @Override
  public SimpleLayoutPanel getMapDisplay() {
    return mapPanel;
  }

  @Override
  public SimpleLayoutPanel getTableDisplay() {
    return tablePanel;
  }

  public ListBox getPerspectiveSelect() {
    return perspectiveSelect;
  }

}