package uk.ac.ox.map.explorer.client.base.view;

import uk.ac.ox.map.explorer.client.BasePresenter;
import uk.ac.ox.map.explorer.client.map.view.MapView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;
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
  HasWidgets breadcrumbs;

  @UiField
  Label currentPlace;

  @UiField
  Label successMessage;

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
  public void addBreadCrumb(String label, String historyToken) {
    Hyperlink hl = new Hyperlink(label, historyToken);
    hl.addStyleName(style.inlineHyperlink());
    breadcrumbs.add(hl);
    breadcrumbs.add(new InlineHTML("&nbsp;>&nbsp;"));
  }

  @Override
  public HasText getCurrentPlace() {
    return currentPlace;
  }

  @Override
  public void clearBreadCrumbs() {
    breadcrumbs.clear();
  }

  public void setSuccessMessage(String message) {
    successMessage.setVisible(true);
    successMessage.setText(message);
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