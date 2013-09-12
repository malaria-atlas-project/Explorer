package uk.ac.ox.map.explorer.server;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import uk.ac.ox.map.domain.EMF;

public class PrQuery {
  
  private enum Cols {
    id, missing_data, citation1, citation2, citation3, country, country_id, latitude, longitude, method, rdt_type, month_start, year_start, month_end, year_end, lower_age, upper_age, pf_pos, examined, dhs_id
  }
  
  private final Sheet sheet;
  
  private final CellStyle boldCellStyle;
  
  private int currRow = 0;
  
  public PrQuery(Workbook wb, List<String> countryIds) {
    
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
    Query q = em.createNativeQuery("select * from explorer.pr_export where country_id in :countryList order by country_id, CASE missing_data WHEN 'Confidential location' THEN 3 WHEN 'No permission to release data' THEN 2 ELSE 1 END, id");
    
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
  
  public PrQuery(Workbook wb) {
	  
		sheet = wb.createSheet();

		boldCellStyle = wb.createCellStyle();
		Font f = wb.createFont();
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);
		boldCellStyle.setFont(f);
		
		addHeaderRow();
		
		EntityManager em = EMF.get().createEntityManager();
	    Query q = em.createNativeQuery("select * from explorer.pr_export order by country_id, CASE missing_data WHEN 'Confidential location' THEN 3 WHEN 'No permission to release data' THEN 2 ELSE 1 END");
	      
	    @SuppressWarnings("unchecked")
	    List<Object[]> l = q.getResultList();
	    
	    for (Object[] objects : l) {
	      appendRow(objects);
	}
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
