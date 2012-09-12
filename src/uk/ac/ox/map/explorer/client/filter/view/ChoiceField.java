package uk.ac.ox.map.explorer.client.filter.view;

import java.util.List;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;

/**
 * Wrapper for a {@link ListBox} providing getters and setters (hiding ugly
 * standard code for setting values based on list index)
 * 
 * @author will
 * 
 * @param <T>
 */
public class ChoiceField<T> extends Composite implements HasValueChangeHandlers<T> {

  private ListBox lb = new ListBox();
  private boolean valueChangeHandlerInitialized = false;
  protected List<T> values;

  public ChoiceField(String labelText) {
    initWidget(lb);
    lb.getElement().setAttribute("style", lb.getElement().getAttribute("style")+" max-width:100%;");
    setBlankValue();
  }

  public void reset() {
    lb.setSelectedIndex(0);
  }

  private void setBlankValue() {
    lb.addItem("----------", "-1");
  }

  public void setSelectedIndex(Integer i) {
    lb.setSelectedIndex(i);
  }

  public void addItems(List<T> values) {
    this.values = values;
    if (values == null)
      return;
    for (T t : values) {
      lb.addItem(t.toString());
    }
  }

  public void clear() {
    lb.clear();
    this.values = null;
    setBlankValue();
  }

  public T getValue() {
    int selectedIdx = lb.getSelectedIndex();
    if (selectedIdx == 0) {
      return null;
    }
    // -1 as have one extra value in lb
    T val = values.get(selectedIdx - 1);
    return val;
  }

  public void setValue(T value) {
    setValue(value, false);
  }

  public void setValue(T value, boolean fireEvents) {
    if (value == null) {
      lb.setSelectedIndex(0);
      return;
    }
    lb.setSelectedIndex(values.indexOf(value) + 1);
  }

  public void setFocus(boolean b) {
    lb.setFocus(b);
  }

  public HandlerRegistration addChangeHandler(ChangeHandler handler) {
    return lb.addDomHandler(handler, ChangeEvent.getType());
  }

  public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> handler) {
    // Initialization code
    if (!valueChangeHandlerInitialized) {
      valueChangeHandlerInitialized = true;
      addChangeHandler(new ChangeHandler() {
        public void onChange(ChangeEvent event) {
          ValueChangeEvent.fire(ChoiceField.this, getValue());
        }
      });
    }
    return addHandler(handler, ValueChangeEvent.getType());
  }

}
