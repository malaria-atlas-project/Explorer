package uk.ac.ox.map.explorer.client.map.view;

import uk.ac.ox.map.explorer.client.map.presenter.MapLayer;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.client.SafeHtmlTemplates.Template;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;

/**
 * <p>
 * An {@link AbstractCell} used to render an image. The String value is the url
 * of the image.
 * </p>
 * <p>
 * If the images being displayed are static or available at compile time, using
 * {@link ImageResourceCell} will usually be more efficient.
 * </p>
 * 
 * @see ImageResourceCell
 */
public class LayerCell extends AbstractCell<MapLayer> {

  interface Template extends SafeHtmlTemplates {
    @Template("<div>{0}</div>")
    SafeHtml img(String layerName);
  }

  private static Template template;

  public LayerCell() {
    super("change", "keydown");
    if (template == null) {
      template = GWT.create(Template.class);
    }
  }

  @Override
  public void render(Context context, MapLayer value, SafeHtmlBuilder sb) {
    if (value != null) {
      sb.append(template.img(value.getName()));
      sb.appendHtmlConstant(AbstractImagePrototype.create(value.getImageResource()).getHTML());
      sb.appendHtmlConstant("<input type=\"checkbox\" checked=checked/>");
    }
  }

  @Override
  public void onBrowserEvent(Context context, Element parent, MapLayer value,
      NativeEvent event, ValueUpdater<MapLayer> valueUpdater) {
    // Check that the value is not null.
    if (value == null) {
      return;
    }
  
    // Call the super handler, which handlers the enter key.
    super.onBrowserEvent(context, parent, value, event, valueUpdater);
  
    // Handle click events.
    if ("change".equals(event.getType())) {
      InputElement el = getInputElement(parent);
      System.out.println(el.isChecked());
    }
  }

  /**
   * Get the checkbox input element from the parent element that wraps our
   * cell.
   * 
   * @param parent the parent element
   * @return the checkbox
   */
  private InputElement getInputElement(Element parent) {
    // We need to navigate down to our input element.
    return (InputElement) parent.getChildNodes().getItem(2);
    
  }
  
  
}
