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

public class AnoQuery {
  
  private enum Cols {
    id, latitude, longitude, country_id, species, year_start, year_end, month_start, month_end, id_method1, id_method2, sample_method1, sample_method2, sample_method3, sample_method4, ASSI, citation, is_present
  }
  
  private Sheet sheet;
  
  private CellStyle boldCellStyle;
  
  private int currRow = 0;
  
  public AnoQuery(Workbook wb, List<Long> itemIds) {
    
    sheet = wb.createSheet();
    
    boldCellStyle = wb.createCellStyle();
    Font f = wb.createFont();
    f.setBoldweight(Font.BOLDWEIGHT_BOLD);
    boldCellStyle.setFont(f);
    
    /*
     * Header row
     */
    addHeaderRow();
    
    if (itemIds == null || itemIds.size() < 1) {
      return;
    }
    
    /*
     * Retrieve data
     */
    EntityManager em = EMF.get().createEntityManager();
    Query q = em
        .createNativeQuery("select id, latitude, longitude, country_id, species, year_start, year_end, month_start, month_end, id_method1, id_method2, sample_method1, sample_method2, sample_method3, sample_method4, \"ASSI\", citation, is_present from explorer.anopheline_export where anopheline_id in :ano order by anopheline_id, id");
    
    q.setParameter("ano", itemIds);
    
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
