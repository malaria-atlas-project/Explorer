package uk.ac.ox.map.explorer.client.filter.view;

import uk.ac.ox.map.explorer.client.filter.presenter.Filter;
import uk.ac.ox.map.explorer.client.filter.presenter.Operator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Encapsulates a {@link TextBox} for use as a {@link Filter}
 * 
 * @author will
 * 
 */
public class TextFilter extends AbstractFilter {
  
  interface DiscreteOptionFilterUiBinder extends UiBinder<Widget, TextFilter> {
  }
  
  private static DiscreteOptionFilterUiBinder uiBinder = GWT
      .create(DiscreteOptionFilterUiBinder.class);
  
  @UiField
  TextBox textBox;
  
  @UiField
  Button goButton;
  
  private Operator op;
  
  @UiConstructor
  public TextFilter(String labelText, String propertyName) {
    super(propertyName);
    initWidget(uiBinder.createAndBindUi(this));
    filterLabel.setText(labelText);
    op = Operator.contains;
  }
  
  @Override
  public String getFilterString() {
    if (textBox.getValue().isEmpty()) {
      return "";
    }
    return FilterBuilder
        .getFilterString(filterProperty, op, textBox.getValue());
  }
  
  @UiHandler("goButton")
  void onClick(ClickEvent e) {
    filterListener.onFilterChange();
  }
  
  public void setOperator(Operator op) {
    this.op = op;
  }
  
  @Override
  public void setValue(String value) {
    textBox.setValue(value);
  }
  
}
