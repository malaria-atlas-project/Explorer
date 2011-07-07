package uk.ac.ox.map.explorer.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uk.ac.ox.map.domain.type.Continent;
import uk.ac.ox.map.domain.type.DiagnosticMethod;
import uk.ac.ox.map.domain.type.JournalType;
import uk.ac.ox.map.domain.type.LatLongSource;
import uk.ac.ox.map.domain.type.McmcRegion;
import uk.ac.ox.map.domain.type.PdfStatus;
import uk.ac.ox.map.domain.type.PermissionType;
import uk.ac.ox.map.domain.type.RdtType;
import uk.ac.ox.map.domain.type.SiteAreaType;
import uk.ac.ox.map.domain.type.SourceType;
import uk.ac.ox.map.domain.type.StringType;
import uk.ac.ox.map.domain.type.SubgenusType;
import uk.ac.ox.map.domain.type.ThesisType;
import uk.ac.ox.map.request.server.SimpleDao;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Singleton
public class IndexServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final Provider<SimpleDao> daoProvider;
  private List<Class<? extends StringType>> stringTypes;
  private Configuration conf;
  
	@Inject
	public IndexServlet(Provider<SimpleDao> daoProvider, Configuration conf) {
	  this.daoProvider = daoProvider;
	  this.conf = conf;
    this.stringTypes = new ArrayList<Class<? extends StringType>>();
    stringTypes.add(Continent.class);
    stringTypes.add(DiagnosticMethod.class);
    stringTypes.add(LatLongSource.class);
    stringTypes.add(McmcRegion.class);
    stringTypes.add(PdfStatus.class);
    stringTypes.add(RdtType.class);
    stringTypes.add(SiteAreaType.class);
    stringTypes.add(SourceType.class);
    stringTypes.add(SubgenusType.class);
    stringTypes.add(ThesisType.class);
    stringTypes.add(JournalType.class);
    stringTypes.add(PermissionType.class);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    resp.setContentType("text/html");
    
    SimpleDao dao = daoProvider.get();
    
    Map<String, String> root = new HashMap<String, String>();
    root.put("seedJson", getJson(dao));
    
    /*
     * Output template
     */
    Template ftl = conf.getTemplate("MapExplorer.ftl");
    
    resp.setCharacterEncoding("UTF-8");
    PrintWriter out = resp.getWriter();

    try {
      ftl.process(root, out);
      out.flush();
    } catch (TemplateException e) {
      e.printStackTrace(out);
    }

  }
  
  private String getJson(SimpleDao dao) {
    Map<String, List<String>> vars = new HashMap<String, List<String>>();

    for (Class<? extends StringType> clazz : stringTypes) {
      
      String key = clazz.getSimpleName();

      List<? extends StringType> objList = dao.all(clazz);

      List<String> var = new ArrayList<String>();

      for (StringType st : objList) {
        var.add(st.getId());
      }

      vars.put(key, var);
    }
    
    final Gson gson = new Gson();
    String json = gson.toJson(vars);
    return json;
  }

}
