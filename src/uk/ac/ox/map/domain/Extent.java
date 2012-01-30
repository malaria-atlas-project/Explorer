package uk.ac.ox.map.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import com.vividsolutions.jts.geom.Envelope;

@Embeddable @Access(AccessType.FIELD)
public class Extent {
  
  private Double minx;

  @Column(name = "minx")
  public Double getMinx() {
    return minx;
  }

  public void setMinx(Double minx) {
    this.minx = minx;
  }

  private Double miny;

  @Column(name = "miny")
  public Double getMiny() {
    return miny;
  }

  public void setMiny(Double miny) {
    this.miny = miny;
  }

  private Double maxx;

  @Column(name = "maxx")
  public Double getMaxx() {
    return maxx;
  }

  public void setMaxx(Double maxx) {
    this.maxx = maxx;
  }

  private Double maxy;

  @Column(name = "maxy")
  public Double getMaxy() {
    return maxy;
  }

  public void setMaxy(Double maxy) {
    this.maxy = maxy;
  }
  
  @Transient
  public Envelope getEnvelope() {
    return new Envelope(minx, maxx, miny, maxy);
  }
  
  @Transient
  public Integer getVersion() {
    return 0;
  }
  
}
