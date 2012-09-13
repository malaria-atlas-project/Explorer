package uk.ac.ox.map.explorer.client.base.view;

import uk.ac.ox.map.explorer.client.BasePresenter;
import uk.ac.ox.map.explorer.client.list.presenter.BaseTablePresenter;
import uk.ac.ox.map.explorer.client.map.presenter.BaseMapPresenter;

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

/**
 * The main application view. It consists of three main areas, the header, an
 * area for displaying maps (controlled by {@link BaseMapPresenter} subclasses)
 * and the area for displaying tables (controlled by {@link BaseTablePresenter}
 * subclasses).
 * 
 * @author will
 */
@Singleton
public class BaseView extends Composite implements BasePresenter.Display {
  
  interface BaseStyle extends CssResource {
    
    String inlineHyperlink();
    
    String spacer();
  }
  
  interface BaseViewUiBinder extends UiBinder<Widget, BaseView> {
  }
  
  private static BaseViewUiBinder uiBinder = GWT.create(BaseViewUiBinder.class);
  
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
  
  public ListBox getPerspectiveSelect() {
    return perspectiveSelect;
  }
  
  @Override
  public SimpleLayoutPanel getTableDisplay() {
    return tablePanel;
  }
  
}