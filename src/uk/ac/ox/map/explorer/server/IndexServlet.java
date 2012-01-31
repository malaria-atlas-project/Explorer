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

import uk.ac.ox.map.domain.AnoRegion;
import uk.ac.ox.map.domain.Continent;
import uk.ac.ox.map.domain.StringType;
import uk.ac.ox.map.explorer.client.request.AnophelineRequest;
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
    stringTypes.add(AnoRegion.class);
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
