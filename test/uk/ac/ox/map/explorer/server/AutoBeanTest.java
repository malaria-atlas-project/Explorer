package uk.ac.ox.map.explorer.server;

import junit.framework.Assert;

import org.junit.Test;

public class AutoBeanTest {
  
  @Test
  public void testBeans() {
    
    LayerAutoBeanEncoder abs = new LayerAutoBeanEncoder();
    Assert.assertNotNull(abs);
    
    
  }

}
