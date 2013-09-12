package uk.ac.ox.map.explorer.server;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import uk.ac.ox.map.domain.EMF;

public class HbSQuery {

	private enum Cols {
		id, country, latitude, longitude, area_type, population_estimates, sample_size, hbaa, hbas, hbss, malaria_hypothesis, citation, source
		}
	  
	  private final Sheet sheet;
	  
	  private final CellStyle boldCellStyle;
	  
	  private int currRow = 0;
	  
	  public HbSQuery(Workbook wb, List<String> countryIds) {
	    
	    sheet = wb.createSheet();
	    
	    boldCellStyle = wb.createCellStyle();
	    Font f = wb.createFont();
	    f.setBoldweight(Font.BOLDWEIGHT_BOLD);
	    boldCellStyle.setFont(f);
	    
	    /*
	     * Header row
	     */
	    addHeaderRow();
	    
	    if (countryIds == null || countryIds.size() < 1) {
	      return;
	    }
	    
	    /*
	     * Retrieve data
	     */
	    EntityManager em = EMF.get().createEntityManager();
	    Query q = em.createNativeQuery("select id, country, latitude, longitude, area_type, population_estimates, sample_size, hbaa, hbas, hbss, malaria_hypothesis, citation, source from explorer.hbs_data where country_id in :countryList order by country");
	    
	    q.setParameter("countryList", countryIds);
	    
	    @SuppressWarnings("unchecked")
	    List<Object[]> l = q.getResultList();
	    
	    for (Object[] objects : l) {
	      appendRow(objects);
	    }
	    
	    /*
	     * Hide id columns
	     */
	    // sheet.setColumnHidden(Cols.surveyReplicateId.ordinal(), true);
	  }
	  
	  public HbSQuery(Workbook wb) {
		    
		    sheet = wb.createSheet();
		    
		    boldCellStyle = wb.createCellStyle();
		    Font f = wb.createFont();
		    f.setBoldweight(Font.BOLDWEIGHT_BOLD);
		    boldCellStyle.setFont(f);
		    
		    /*
		     * Header row
		     */
		    addHeaderRow();
		   
		    /*
		     * Retrieve data
		     */
		    EntityManager em = EMF.get().createEntityManager();
		    Query q = em.createNativeQuery("select id, country, latitude, longitude, area_type, population_estimates, sample_size, hbaa, hbas, hbss, malaria_hypothesis, citation, source from explorer.hbs_data");
		    
		    @SuppressWarnings("unchecked")
		    List<Object[]> l = q.getResultList();
		    
		    for (Object[] objects : l) {
		      appendRow(objects);
		    }
		    
		    /*
		     * Hide id columns
		     */
		    // sheet.setColumnHidden(Cols.surveyReplicateId.ordinal(), true);
		  }
	  
	  private void addHeaderRow() {
	    Row row = getNewRow();
	    
	    Cols[] cols = Cols.values();
	    for (int i = 0; i < cols.length; i++) {
	      Cell cell = row.createCell(i);
	      cell.setCellValue(cols[i].name().replace("_", " "));
	      cell.setCellStyle(boldCellStyle);
	    }
	  }
	  
	  private void appendRow(Object[] objects) {
	    
	    Row row = getNewRow();
	    
	    for (int i = 0; i < objects.length; i++) {
	      Object obj = objects[i];
	      
	      if (obj != null) {
	        if (obj instanceof String) {
	          Cell cell = row.createCell(i, Cell.CELL_TYPE_STRING);
	          cell.setCellValue((String) obj);
	        } else if (obj instanceof Number) {
	          Cell cell = row.createCell(i, Cell.CELL_TYPE_NUMERIC);
	          if (obj instanceof Long) {
	            cell.setCellValue((Long) obj);
	          } else if (obj instanceof Double) {
	            cell.setCellValue((Double) obj);
	          } else if (obj instanceof Integer) {
	            cell.setCellValue((Integer) obj);
	          } else if (obj instanceof BigDecimal) {
	            cell.setCellValue(((BigDecimal) obj).doubleValue());
	          } else {
	            System.out.println(obj.getClass().toString());
	            throw new RuntimeException("Unknown numeric type.");
	          }
	        } else if (obj instanceof Boolean) {
	          Cell cell = row.createCell(i, Cell.CELL_TYPE_BOOLEAN);
	          cell.setCellValue((Boolean) obj);
	        }
	      } else {
	        /*
	         * Create blank cell for null values.
	         */
	        row.createCell(i, Cell.CELL_TYPE_BLANK);
	      }
	    }
	    
	  }
	  
	  private Row getNewRow() {
	    Row row = sheet.createRow(currRow);
	    currRow++;
	    return row;
	  }
	
}
