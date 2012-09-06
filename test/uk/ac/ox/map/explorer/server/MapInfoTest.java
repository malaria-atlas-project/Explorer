package uk.ac.ox.map.explorer.server;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Injector;

import resources.util.TestResourceFactory;

public class MapInfoTest {
  
  private Injector injector;
  private EntityManager em;
  private MapInfoServiceImpl serv;

  @Before
  public void initialize() {
    injector = TestResourceFactory.getInjector();
    em = injector.getInstance(EntityManager.class);
    serv = injector.getInstance(MapInfoServiceImpl.class);
  };
  
  @Test
  public void mapInfo() {
    String x = serv.getPrExtentInfo(true, true, -40d, -40d, 40d, 40d);
    System.out.println(x);
    String y = serv.getAnophelineExtentInfo(true, true, null, -40d, -40d, 40d, 40d);
    System.out.println(y);
    String z = serv.getAnophelineExtentInfo(true, true, 7l, -40d, -40d, 40d, 40d);
    System.out.println(z);
  }
  
}
