package uk.ac.ox.map.explorer.server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.inject.Singleton;

@Singleton
public class ExcelServlet extends HttpServlet {

  private static final long serialVersionUID = -4398576196050747719L;
  private static final String MIME_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  private static final String MIME_XLS = "application/vnd.ms-excel";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String countries = req.getParameter("country");
    String[] cIds = countries.split(",");
    
    Workbook outputWb = new XSSFWorkbook();
    String fileName = String.format("pr_%s.xlsx", getDateStamp());
    
    PrQuery prq = new PrQuery(outputWb, Arrays.asList(cIds));
    
    
    resp.setContentType(MIME_XLSX);
    resp.setHeader("Content-Disposition", String.format("attachment; filename=%s", fileName));
    outputWb.write(resp.getOutputStream());

  }

  public String getDateStamp() {
    return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
  }
}
