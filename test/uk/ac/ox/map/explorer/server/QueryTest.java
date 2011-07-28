package uk.ac.ox.map.explorer.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class QueryTest {
  
  @Test
  public void bobQuery() throws FileNotFoundException, IOException {
    Workbook wb = new XSSFWorkbook();
    
    List<String> cids = Arrays.asList(new String[] {"KEN", "BRA"});
    
    PrQuery bq = new PrQuery(wb, cids);
    
    wb.write(new FileOutputStream(new File("c:/Temp/test.xlsx")));
    
  }

}
