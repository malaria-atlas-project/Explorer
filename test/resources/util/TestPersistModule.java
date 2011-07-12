package resources.util;

import uk.ac.ox.map.explorer.server.MapInfoServiceImpl;
import uk.ac.ox.map.request.server.AppRequestFactoryServlet;
import uk.ac.ox.map.request.server.AppServiceLayerDecorator;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class TestPersistModule extends AbstractModule {
  
  private static String REALM =  "http://localhost:8888"; 
  private static String CONTEXT_PATH =  "mapadmin";

  @Override
  protected void configure() {
    
    bind(String.class).annotatedWith(Names.named("context_path")).toInstance(CONTEXT_PATH);
    bind(String.class).annotatedWith(Names.named("realm")).toInstance(REALM);

    bind(AppServiceLayerDecorator.class);
    bind(AppRequestFactoryServlet.class);
    bind(MapInfoServiceImpl.class);
  }

}
