package uk.ac.ox.map.explorer.client.filter.view;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.filter.presenter.Filter;
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

public class DiscreteOptionFilter extends AbstractFilter implements Filter {

  private static DiscreteOptionFilterUiBinder uiBinder = GWT.create(DiscreteOptionFilterUiBinder.class);

  interface DiscreteOptionFilterUiBinder extends UiBinder<Widget, DiscreteOptionFilter> {
  }

  private final String rbGroupName = DOM.createUniqueId();
  
  private final String[] opts = { "all", "true", "false" };
  
  private List<RadioButton> radioButtons = new ArrayList<RadioButton>();
  
  private String currVal;
  
  @UiField
  FlowPanel base;

  @UiConstructor
  public DiscreteOptionFilter(String labelText, String propertyName) {
    super(propertyName);
    
    initWidget(uiBinder.createAndBindUi(this));
    //FIXME: hack as can't put HTML in uibinder
    labelText = labelText.replace("Pf", "<i>Pf</i>");
    labelText = labelText.replace("Pv", "<i>Pv</i>");
    filterLabel.setHTML(labelText);

    for (int i = 0; i < opts.length; i++) {

      RadioButton rb = new RadioButton(rbGroupName, opts[i]);
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
          filterList.onFilterChange();
        }
      });
    }
  }

  @Override
  public String getFilterString() {
    if(currVal.equals("all")) {
      return "";
    }
    return FilterBuilder.getFilterString(filterProperty, Operator.eq, currVal);
//    return filterProperty + "_" + Operator.eq + "=" + currVal;
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
