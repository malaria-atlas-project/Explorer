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

import uk.ac.ox.map.shared.EMF;

public class PrQuery  {

  private final Sheet sheet;
  private final CellStyle boldCellStyle;
  
  private int currRow = 0;

  private enum Cols {
    Permission_to_release,
    Source_id1, Citation1, 
    Source_id2, Citation2, 
    Source_id3, Citation3, 
    Country, Admin1_paper, Admin2_paper, 
    Site_name, Area_type, Rural_Urban, Latitude, Longitude, LatLong_source, Site_notes, 
    Method, RDT_type, XS, Survey_notes, 
    Month_start, Month_end, Year_start, Year_end, Lower_age, Upper_age, Examined, Pf_pos, Pv_pos, Pf_PR,
    surveyReplicateId
  }
  
  public PrQuery(Workbook wb, List<String> countryIds) {
    
    this.sheet = wb.createSheet();
    
    this.boldCellStyle = wb.createCellStyle();
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
    Query q = em.createNativeQuery(
    "select " +
    "not exists (select * from source sou join source_survey ssu on sou.id = ssu.source_id where (permission_type_id = 'Confidential' or permission_type_id = 'Neither') and su.id = ssu.survey_id), " +
    "so1.id as source_id1, so1.citation as citation1, " +
    "so2.id as source_id2, so2.citation as citation2, " +
    "so3.id as source_id3, so3.citation as citation3, " +
    "c.name, " +
    "su.admin1_paper, su.admin2_paper, si.name, si.area_type_id, si.rural_urban, si.latitude, si.longitude, si.latlong_source_id, si.notes, " +
    "su.method, su.rdt_type, su.nxs, su.notes, " +
    "sr.month_start, sr.year_start, sr.month_end, sr.year_end, sr.lower_age, sr.upper_age, sr.examined, sr.pf_pos, sr.pv_pos, sr.pf_pr, " +
    "sr.id " +
    "from pr.survey_replicate sr " +
    "join pr.survey su on su.id = sr.survey_id " +
    "join site si on si.id = su.site_id " +
    "join country c on c.id = si.country_id " +
    "join pr.source_survey ss1 on ss1.survey_id = su.id and ss1.ordinal = 1 " +
    "join source so1 on ss1.source_id = so1.id " +
    "left join pr.source_survey ss2 on ss2.survey_id = su.id and ss2.ordinal = 2 " +
    "left join source so2 on so2.id = ss2.source_id " +
    "left join pr.source_survey ss3 on ss3.survey_id = su.id and ss3.ordinal = 3 " +
    "left join source so3 on so3.id = ss3.source_id " +
    "where c.id in :countryList " +
    "order by so1.id, si.name"
    );
    
    q.setParameter("countryList", countryIds);
    
    @SuppressWarnings("unchecked")
    List<Object[]> l = q.getResultList();
    
    for (Object[] objects : l) {
      appendRow(objects);
    }
    
    /*
     * Hide id columns
     */
    sheet.setColumnHidden(Cols.surveyReplicateId.ordinal(), true);
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
  
  private Row getNewRow() {
    Row row = sheet.createRow(currRow);
    currRow ++;
    return row;
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


}
