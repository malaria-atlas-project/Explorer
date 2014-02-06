package uk.ac.ox.map.explorer.server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uk.ac.ox.map.explorer.client.list.presenter.BaseSelectionPresenter;

import com.google.inject.Singleton;

@Singleton
public class ExcelServletAll extends HttpServlet {
  private static final long serialVersionUID = -4658523092720318133L; // auto
                                                                      // generated
  
  private static final String MIME_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  
  // private static final String MIME_XLS = "application/vnd.ms-excel";
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    
    //String ids = req.getParameter(BaseSelectionPresenter.ID_PARAM);
    String resource = req.getParameter(BaseSelectionPresenter.RESOURCE_PARAM);
    System.out.println(resource);
    
    //String[] cIds = ids.split(",");
    Workbook outputWb = new XSSFWorkbook();
    
    String fileName;
    if (resource.equals(BaseSelectionPresenter.COUNTRY_RESOURCE)) {
      
      fileName = String.format("pr_%s.xlsx", getDateStamp());
      new PrQuery(outputWb); // perform query
      
    } else if (resource.equals(BaseSelectionPresenter.G6PD_RESOURCE)) {
    	fileName = String.format("g6pd_%s.xlsx", getDateStamp());
        new G6PDQuery(outputWb);
    	
    } else if (resource.equals(BaseSelectionPresenter.HBS_RESOURCE)) {
    	fileName = String.format("hbs_%s.xlsx", getDateStamp());
        new HbSQuery(outputWb);
    }   
      else if (resource.equals(BaseSelectionPresenter.DUFFY_RESOURCE)) {
        fileName = String.format("duffy_%s.xlsx", getDateStamp());
        new DuffyQuery(outputWb);
        
    } else if (resource.equals(BaseSelectionPresenter.PVPR_RESOURCE)) {
        fileName = String.format("pvpr_%s.xlsx", getDateStamp());
        new PvPRQuery(outputWb);
        
    } 
        else if (resource.equals(BaseSelectionPresenter.ANO_RESOURCE)) {
      
      fileName = String.format("ano_%s.xlsx", getDateStamp());
//      List<Long> anoIds = new ArrayList<Long>();
//      for (int i = 0; i < cIds.length; i++) {
//        if (!cIds[i].isEmpty()) {
//          anoIds.add(Long.parseLong(cIds[i]));
//        }
//      }
      new AnoQuery(outputWb); // perform query
      
    } else {
      throw new ServletException();
    }
    
    resp.setContentType(MIME_XLSX);
    resp.setHeader("Content-Disposition",
        String.format("attachment; filename=%s", fileName));
    outputWb.write(resp.getOutputStream());
    
  }
  
  public String getDateStamp() {
    return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
  }
}

