package uk.ac.ox.map.explorer.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.metamodel.SingularAttribute;
import javax.servlet.ServletException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uk.ac.ox.map.domain.Duffy;
import uk.ac.ox.map.domain.EMF;

public class DuffyQuery {

//	private String templateFile = "/home/andy/workspace/Duffy_template.xlsx";
	
	Sheet sheet;
	
	CellStyle boldCellStyle;

	private int currRow = 0;

	// Retrieve country subset
	public DuffyQuery(Workbook wb, List<String> countryIds) {

		
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
			Query q = em
					.createNativeQuery("select gid,country,latitude,longitude,area_type,no_examine,diagnostic,prom_posit,prom_negat,phe_no_a_b,phe_no_a_1,phe_no_a_2,phe_no_a_3,aphe_no_a_,aphe_no_a1,bphe_no_b_,bphe_no_b1,genfyafya,fya_fyb,fyb_fyb,fybesfybes,fya_fybes,fyb_fybes,fya_fyaes,fyb_fyaes,fybesfyaes,fyaesfyaes,citation from explorer.duffy_data where country_id in :countryList order by country");
			
			q.setParameter("countryList", countryIds);

			@SuppressWarnings("unchecked")
			List<Object[]> l = q.getResultList();

			for (Object[] objects : l) {
				appendRow(objects);
			}

				
	}

	// Retrieve all
	public DuffyQuery(Workbook wb) {

	
//			FileInputStream inputStream = new FileInputStream(new File(
//					templateFile));
//			XSSFWorkbook wb = new XSSFWorkbook(inputStream);

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
			Query q = em
					.createNativeQuery("select gid,country,latitude,longitude,area_type,no_examine,diagnostic,prom_posit,prom_negat,phe_no_a_b,phe_no_a_1,phe_no_a_2,phe_no_a_3,aphe_no_a_,aphe_no_a1,bphe_no_b_,bphe_no_b1,genfyafya,fya_fyb,fyb_fyb,fybesfybes,fya_fybes,fyb_fybes,fya_fyaes,fyb_fyaes,fybesfyaes,fyaesfyaes,citation from explorer.duffy_data");
			
			@SuppressWarnings("unchecked")
			List<Object[]> l = q.getResultList();

			for (Object[] objects : l) {
				appendRow(objects);
			}

		
	}

	

	private void addHeaderRow() {
		Row row = getNewRow();
		String[] header1 = {"","","","","","","","","diagnostic method = prom","","diagnostic method = phe","","","","diagnostic method = aphe","","diagnostic method = bphe","","diagnostic method = gen"};
		for (int i = 0; i < header1.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(header1[i]);
			cell.setCellStyle(boldCellStyle);
		}
		
		row = getNewRow();
		String[] header2 = {"id","country","latitude","longitude","area type","no. examined","diagnostic method [see Notes]","positives","negatives","no. a+b+","no. a+b-","no. a-b+","no. a-b-","no. a+","no. a-","no. b+","no. b-","FY*A/FY*A","FY*A/FY*B","FY*B/FY*B","FY*BES/FY*BES","FY*A/FY*BES","FY*B/FY*BES","FY*A/FY*AES","FY*B/FY*AES","FY*BES/FY*AES","FY*AES/FY*AES","Citation"};
		for (int i = 0; i < header2.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(header2[i]);
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
