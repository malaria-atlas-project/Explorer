package uk.ac.ox.map.explorer.client.filter.view;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.filter.presenter.Filter;
import uk.ac.ox.map.explorer.client.filter.presenter.Operator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Encapsulates a {@link ChoiceField} for use as a {@link Filter}
 * 
 * @author will
 */
public class OptionFilter extends AbstractFilter {
  
  interface OptionFilterUiBinder extends UiBinder<Widget, OptionFilter> {
  }
  
  private static OptionFilterUiBinder uiBinder = GWT
      .create(OptionFilterUiBinder.class);
  
  @UiField
  FlowPanel base;
  
  private ChoiceField<String> cf;
  
  @UiConstructor
  public OptionFilter(String labelText, String propertyName, String jsVar) {
    super(propertyName);
    
    initWidget(uiBinder.createAndBindUi(this));
    filterLabel.setText(labelText);
    
    cf = new ChoiceField<String>(labelText);
    base.add(cf);
    
    cf.addValueChangeHandler(new ValueChangeHandler<String>() {
      @Override
      public void onValueChange(ValueChangeEvent<String> event) {
        filterListener.onFilterChange();
      }
    });
    
    JsArrayString vals = getValues(jsVar);
    List<String> v = new ArrayList<String>();
    for (int i = 0; i < vals.length(); i++) {
      v.add(vals.get(i));
    }
    cf.addItems(v);
  }
  
  @Override
  public String getFilterString() {
    
    String val = cf.getValue();
    if (val == null) {
      return "";
    }
    return FilterBuilder.getFilterString(filterProperty, Operator.eq, val);
  }
  
  public native JsArrayString getValues(String varName) /*-{
    var vals = $wnd["seedJson"][varName];
    if (vals == null) { 
      return [];
    }
    return vals;
  }-*/;
  
  @Override
  public void setValue(String value) {
    cf.setValue(value, false);
  }
  
}
