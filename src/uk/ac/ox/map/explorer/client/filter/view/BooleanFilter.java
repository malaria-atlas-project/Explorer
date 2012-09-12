package uk.ac.ox.map.explorer.client.filter.view;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.filter.presenter.Operator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * Provides true, false and all radio buttons.
 * 
 * @author will
 */
public class BooleanFilter extends AbstractFilter {

  private static BooleanFilterUiBinder uiBinder = GWT.create(BooleanFilterUiBinder.class);

  interface BooleanFilterUiBinder extends UiBinder<Widget, BooleanFilter> {
  }

  /*
   * Using a random but guaranteed unique group name for radio buttons is a
   * quick way of ensuring they're grouped properly.
   */
  private final String rbGroupName = DOM.createUniqueId();

  private final String[] opts = { "all", "true", "false" };

  private List<RadioButton> radioButtons = new ArrayList<RadioButton>();

  private String currVal;

  @UiField
  FlowPanel base;

  @UiConstructor
  public BooleanFilter(String labelText, String propertyName) {
    super(propertyName);

    initWidget(uiBinder.createAndBindUi(this));
    // FIXME: hack as can't put HTML in uibinder
    labelText = labelText.replace("P.falciparum", "<i>P.falciparum</i>");
    labelText = labelText.replace("P.vivax", "<i>P.vivax</i>");
    filterLabel.setHTML(labelText);

    for (int i = 0; i < opts.length; i++) {

      RadioButton rb = new RadioButton(rbGroupName, opts[i]);
      rb.getElement().setAttribute("style", rb.getElement().getAttribute("style")+" display: inline-block;"); //FIXME: tmp hack
      radioButtons.add(rb);
      base.add(rb);
      if (i == 0) {
        currVal = opts[i];
        rb.setValue(true);
      }
      rb.addClickHandler(new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
          String val = ((RadioButton) event.getSource()).getText();
          currVal = val;
          filterListener.onFilterChange();
        }
      });
    }
  }

  @Override
  public String getFilterString() {
    if (currVal.equals("all")) {
      return "";
    }
    return FilterBuilder.getFilterString(filterProperty, Operator.eq, currVal);
  }

  @Override
  public void setValue(String value) {
    for (int i = 0; i < opts.length; i++) {
      if (value.equals(opts[i])) {
        radioButtons.get(i).setValue(true);
      } else {
        radioButtons.get(i).setValue(false);
      }
    }
  }

}
