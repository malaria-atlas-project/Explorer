package uk.ac.ox.map.explorer.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

/**
 * Think of the ServletModule as an in-code replacement for the web.xml
 * deployment descriptor. Filters and servlets are configured here using normal
 * Java method calls
 * 
 */
public class GuiceServletModule extends ServletModule {
  
  @Override
  protected void configureServlets() {
    
    install(new JpaPersistModule("p1"));
    
    bind(RequestFactoryServlet.class).in(Singleton.class);
    
    /*
     * Bind constants
     */
    Names.bindProperties(binder(), getProperties());
    
    /*
     * Persistence filters
     */
    filter("/*").through(PersistFilter.class);
    
    serve("/app").with(IndexServlet.class);
    
    serve("/gwtRequest").with(RequestFactoryServlet.class);
    serve("/mapexplorer/mapInfo").with(MapInfoServiceImpl.class);
    serve("/dataDownload").with(ExcelServlet.class);
    
  }
  
  private Properties getProperties() {
    Properties props = new Properties();
    InputStream stream = this.getClass().getClassLoader().getResourceAsStream("urls.properties");
    try {
      props.load(stream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return props;
  }
  
  @Provides
  @Singleton
  Configuration templateConfiguration(ServletContext context) {
    Configuration configInstance = new Configuration();
    configInstance.setServletContextForTemplateLoading(context, "WEB-INF/templates");
    configInstance.setObjectWrapper(new DefaultObjectWrapper());
    return configInstance;
  }

}