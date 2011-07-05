package uk.ac.ox.map.explorer.client.base.view;

import org.adamtacy.client.ui.effects.impl.Fade;

import uk.ac.ox.map.explorer.client.AppWidget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import uk.ac.ox.map.explorer.client.BasePresenter;

@Singleton
public class BaseView extends Composite implements BasePresenter.Display {

  private static BaseViewUiBinder uiBinder = GWT.create(BaseViewUiBinder.class);

  interface BaseViewUiBinder extends UiBinder<Widget, BaseView> {
  }

  interface BaseStyle extends CssResource {
    String inlineHyperlink();

    String spacer();

    String tools();
  }

  @UiField(provided = true)
  SimpleLayoutPanel centerPanel;

  @UiField
  HasWidgets breadcrumbs;

  @UiField
  Label currentPlace;

  @UiField
  Label successMessage;

  @UiField
  Anchor logout;

  @UiField
  BaseStyle style;

  @Inject
  public BaseView(AppWidget appWidget) {
    this.centerPanel = appWidget;
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
  public HasClickHandlers getLogout() {
    return logout;
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
    Fade effect = new Fade(successMessage.getElement());
    effect.play(200);
  }

}