package uk.ac.ox.map.explorer.server;

import uk.ac.ox.map.domain.Extent;
import uk.ac.ox.map.explorer.client.proxy.seed.EntityProxy;

public class ExtentUtil {
  
  public static void copyExtent(EntityProxy extentProxy, Extent extent) {
    
    extentProxy.setMaxX(extent.getMaxx());
    extentProxy.setMaxY(extent.getMaxy());
    extentProxy.setMinX(extent.getMinx());
    extentProxy.setMinY(extent.getMiny());
    
  }

}
