package uk.ac.ox.map.explorer.client.filter.view;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.filter.presenter.Filter;
import uk.ac.ox.map.explorer.client.filter.presenter.FilterListener;
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

public class OptionFilter extends AbstractFilter implements Filter {

  private static OptionFilterUiBinder uiBinder = GWT.create(OptionFilterUiBinder.class);

  interface OptionFilterUiBinder extends UiBinder<Widget, OptionFilter> {
  }

  @UiField
  FlowPanel base;

  private ChoiceField<String> cf;

  private FilterListener filterListener;
  
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

	public native JsArrayString getValues(String varName) /*-{
	  var vals = $wnd["seedJson"][varName];
	  if (vals == null) return [];
	  return vals;
	}-*/;
    
  @Override
  public String getFilterString() {
    
    String val = cf.getValue();
    if (val == null) {
      return "";
    }
    return FilterBuilder.getFilterString(filterProperty, Operator.eq, val);
  }

  @Override
  public void setValue(String value) {
    
  }
  
  @Override
  public void setListener(FilterListener filterListener) {
    this.filterListener = filterListener;
  }


}
