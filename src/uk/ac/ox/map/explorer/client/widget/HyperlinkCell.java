package uk.ac.ox.map.explorer.client.widget;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.Hyperlink;

/**
 * @author will
 * 
 * {@link Cell} that wraps a {@link Hyperlink} WARNING: make sure the contents
 * of your Hyperlink really are safe from XSS!
 * 
 */
public class HyperlinkCell extends AbstractCell<Hyperlink> {

  @Override
  public void render(Context context, Hyperlink h, SafeHtmlBuilder sb) {
    sb.append(SafeHtmlUtils.fromTrustedString(h.toString()));
  }

}
