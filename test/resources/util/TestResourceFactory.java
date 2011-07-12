package resources.util;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.jpa.JpaPersistModule;

public class TestResourceFactory {
  
  public static Injector getInjector() {
    
    Injector injector = Guice.createInjector(new TestPersistModule(), new JpaPersistModule("p1"));
    PersistService ps = injector.getInstance(PersistService.class);
    ps.start();
    return injector;
  }

}
