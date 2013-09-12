package uk.ac.ox.map.explorer.client.list.view;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import uk.ac.ox.map.explorer.client.proxy.NamedProxy;
import uk.ac.ox.map.explorer.client.list.view.CountryTableView;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class SelectionView extends Composite {
  
  interface SelectionViewUiBinder extends UiBinder<Widget, SelectionView> {
  }
  
  private static SelectionViewUiBinder uiBinder = GWT
      .create(SelectionViewUiBinder.class);
  
  
  @UiField
  SimplePanel cellListContainer;

  
  @UiField
  Button downloadButton;
 
  @UiField
  Button downloadAllButton;
  
  @Inject
  CountryTableView countryTableView;
  
  
  private CellList<NamedProxy> cellList;
  
  public SelectionView() {
    
	initWidget(uiBinder.createAndBindUi(this));
    	     
    Cell<NamedProxy> cell = new AbstractCell<NamedProxy>() {
      @Override
      public void render(Context context, NamedProxy value, SafeHtmlBuilder sb) {
        sb.append(SafeHtmlUtils.fromTrustedString(value.getName()));
      }
      
    };
    
    cellList = new CellList<NamedProxy>(cell);
    cellListContainer.add(cellList);

  }
  
  public HasClickHandlers getDownloadButton() {
    return downloadButton;
  }
  
  public HasClickHandlers getDownloadAllButton() {
	  return downloadAllButton;
  }
  
  public void setRowData(List<? extends NamedProxy> values) {
    cellList.setRowData(values);
  }
  
}
