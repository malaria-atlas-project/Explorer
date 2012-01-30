package uk.ac.ox.map.explorer.client.proxy;

import uk.ac.ox.map.domain.Extent;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

@ProxyFor(Extent.class)
public interface ExtentProxy extends ValueProxy {

  public Double getMinx();

  public void setMinx(Double minx);

  public Double getMiny();

  public void setMiny(Double miny);

  public Double getMaxx();

  public void setMaxx(Double maxx);

  public Double getMaxy();

  public void setMaxy(Double maxy);

}
