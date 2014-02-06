package uk.ac.ox.map.explorer.client.list.view;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.ac.ox.map.explorer.client.proxy.CountryProxy;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
/**
 * @author andy
 */
@Singleton
public class G6PDTableView extends TableView<CountryProxy>{
	
	Set<String> selected = new HashSet<String>();
	  
	  //Column<CountryProxy, Boolean> checkColumn = null;
	  
	  @Inject
	  public G6PDTableView(@Named("TABLE_SIZE") Integer pageSize) {
	    super(pageSize);
	//    
//	    int rows;
	//    
//	    public int getRows() {
//	    	return dataGrid.getRowCount();
//	    }
	    
	    
	    addIDSortableColumn(new TextColumn<CountryProxy>() {
	      
	      @Override
	      public String getValue(CountryProxy object) {
	        return getRepr(object.getId());
	      }
	    }, "Id", "id");
	        
	    addNameSortableColumn(new TextColumn<CountryProxy>() {
	    
	      @Override
	      public String getValue(CountryProxy object) {
	        return getRepr(object.getName());
	      }
	    }, "Name", "name");
	    
	        
	    Column<CountryProxy, Boolean> checkColumn = new Column<CountryProxy, Boolean>(
	        new CheckboxCell(false, false)) {
	      @Override
	      public Boolean getValue(CountryProxy object) {
	        return selected.contains(object.getId());
	      }
	    };
	             
	    CheckboxCell headerCheckbox = new CheckboxCell(false, true);
	    Header<Boolean> header = new Header<Boolean>((Cell<Boolean>) headerCheckbox) {

			@Override
			public Boolean getValue() {
				// TODO Auto-generated method stub
				//return null;
				for (CountryProxy item : dataGrid.getVisibleItems()) {
					if(!selectionModel.isSelected(item)) {
						return false;
					}
				}
				return dataGrid.getVisibleItems().size() > 0;
				
			}
	    }; 	
	    
	    
	    header.setUpdater(new ValueUpdater<Boolean>() {
					@Override
			public void update(Boolean value) {
//				public void componentSelected() {
//					NodeList<Element> nl = Document.get().get getElementsByTagName(tagName);
//				}
					
			    List<CountryProxy> items = dataGrid.getVisibleItems();
			   
			    //dataGrid.getSelectionModel().setSelected(object, selected)
			    for(CountryProxy cp : items) {
			    
			    	dataGrid.getSelectionModel().setSelected(cp, value);
			    	//dataGrid.getSelectionModel().
			    	Column<CountryProxy, ?> col = dataGrid.getColumn(2);
			        //((CheckboxCell)(col.getCell())).setValue(context, parent, true)
			    }
				
			}
		});	

	    
	      
	    checkColumn.setFieldUpdater(new FieldUpdater<CountryProxy, Boolean>() {
	      @Override
	      public void update(int index, CountryProxy object,
	          Boolean selectForDownload) {
	        presenter.fireObjectChecked(object, selectForDownload);
	        if (selectForDownload) {
	        	String id = object.getId();
	        	if (selected.contains(object.getId())) {
	             selected.add(object.getId());
	        	}
	        } else {
	        	if (selected.contains(object.getId())) {
	             selected.remove(object.getId());
	        }
	        } 
	        
	      }

	    });
	  
	    
	    addColumn(checkColumn, "Select for download");
	    setWidth(checkColumn, "22%");
	    checkColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
	    //addColumn(checkColumn, header);
	    
	    TextColumn textColumn1 = new TextColumn<Object>() {
			@Override
			public String getValue(Object object) {
				return null;
			}
		};
		
		
		TextColumn textColumn2 = new TextColumn<Object>() {
			@Override
			public String getValue(Object object) {
				return null;
			}
		};

		
	  }
	  
//	  public void setAllCheckBoxes(boolean selected) {
//		  List<CountryProxy> items = dataGrid.getVisibleItems();
//		  int i=0;
//		  for(CountryProxy cp : items) {
//			  //items.
//	          checkColumn.getFieldUpdater().update(i++, cp, selected);
//	          //checkColumn.getCell().setValue(context, , true)
//		  }
//	  }
	  
	  public int getRows() {
	  	return dataGrid.getRowCount();
	  }
	  
	}


