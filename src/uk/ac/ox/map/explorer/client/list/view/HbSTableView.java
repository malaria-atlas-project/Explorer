package uk.ac.ox.map.explorer.client.list.view;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import uk.ac.ox.map.explorer.client.proxy.CountryAllProxy;

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
public class HbSTableView extends TableView<CountryAllProxy>{
	
	Set<String> selected = new HashSet<String>();
	  
	  //Column<CountryAllProxy, Boolean> checkColumn = null;
	  
	  @Inject
	  public HbSTableView(@Named("TABLE_SIZE") Integer pageSize) {
	    super(pageSize);

	    //selected = new HashSet<String>();
	    
	    addIDSortableColumn(new TextColumn<CountryAllProxy>() {
	      
	      @Override
	      public String getValue(CountryAllProxy object) {
	        return getRepr(object.getId());
	      }
	    }, "Id", "id");
	        
	    addNameSortableColumn(new TextColumn<CountryAllProxy>() {
	    
	      @Override
	      public String getValue(CountryAllProxy object) {
	        return getRepr(object.getName());
	      }
	    }, "Name", "name");
	    
	        
	    Column<CountryAllProxy, Boolean> checkColumn = new Column<CountryAllProxy, Boolean>(
	        new CheckboxCell(false, false)) {
	      @Override
	      public Boolean getValue(CountryAllProxy object) {
	        return selected.contains(object.getId());
	      }
	    };
	             
	    CheckboxCell headerCheckbox = new CheckboxCell(false, true);
	    Header<Boolean> header = new Header<Boolean>((Cell<Boolean>) headerCheckbox) {

			@Override
			public Boolean getValue() {
				// TODO Auto-generated method stub
				//return null;
				for (CountryAllProxy item : dataGrid.getVisibleItems()) {
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
					
			    List<CountryAllProxy> items = dataGrid.getVisibleItems();
			   
			    //dataGrid.getSelectionModel().setSelected(object, selected)
			    for(CountryAllProxy cp : items) {
			    
			    	dataGrid.getSelectionModel().setSelected(cp, value);
			    	//dataGrid.getSelectionModel().
			    	Column<CountryAllProxy, ?> col = dataGrid.getColumn(2);
			        //((CheckboxCell)(col.getCell())).setValue(context, parent, true)
			    }
				
			}
		});	

	    
	      
	    checkColumn.setFieldUpdater(new FieldUpdater<CountryAllProxy, Boolean>() {
	      @Override
	      public void update(int index, CountryAllProxy object,
	          Boolean selectForDownload) {
	        presenter.fireObjectChecked(object, selectForDownload);
	        if (selectForDownload) {
	        	String id = object.getId();
	        	if (selected.contains(object.getId())) {
	             selected.add(object.getId());
	        	}
	        } else {
	             selected.remove(object.getId());
	        } 
	        
	      }

//		@Override
//		public boolean equals(Object obj) {
//			obj.
//			return super.equals(obj);
//		}

		
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
//		  List<CountryAllProxy> items = dataGrid.getVisibleItems();
//		  int i=0;
//		  for(CountryAllProxy cp : items) {
//			  //items.
//	          checkColumn.getFieldUpdater().update(i++, cp, selected);
//	          //checkColumn.getCell().setValue(context, , true)
//		  }
//	  }
	  
	  public int getRows() {
	  	return dataGrid.getRowCount();
	  }
	  
	}


