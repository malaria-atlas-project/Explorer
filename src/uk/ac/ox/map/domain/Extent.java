package uk.ac.ox.map.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.vividsolutions.jts.geom.Envelope;

@Embeddable
@Access(AccessType.FIELD)
public class Extent {
  
  private Double minx;
  
  private Double miny;
  
  private Double maxx;
  
  private Double maxy;
  
  @Transient
  public Envelope getEnvelope() {
    return new Envelope(minx, maxx, miny, maxy);
  }
  
  @Column(name = "maxx")
  public Double getMaxx() {
    return maxx;
  }
  
  @Column(name = "maxy")
  public Double getMaxy() {
    return maxy;
  }
  
  @Column(name = "minx")
  public Double getMinx() {
    return minx;
  }
  
  @Column(name = "miny")
  public Double getMiny() {
    return miny;
  }
  
  @Transient
  public Integer getVersion() {
    return 0;
  }
  
  public void setMaxx(Double maxx) {
    this.maxx = maxx;
  }
  
  public void setMaxy(Double maxy) {
    this.maxy = maxy;
  }
  
  public void setMinx(Double minx) {
    this.minx = minx;
  }
  
  public void setMiny(Double miny) {
    this.miny = miny;
  }
  
}
