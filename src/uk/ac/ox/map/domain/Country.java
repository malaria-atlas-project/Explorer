package uk.ac.ox.map.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema="explorer", name="country")
public class Country  {
  
  private String id;

  @Id
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  
  private Region region;
  
  @ManyToOne
  @JoinColumn(name="map_region_id")
  public Region getRegion() {
    return region;
  }

  public void setRegion(Region region) {
    this.region = region;
  }
  
  private String name;

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  private Boolean pfEndemic;

  @Column(name = "pf_endemic")
  public Boolean getPfEndemic() {
    return pfEndemic;
  }

  public void setPfEndemic(Boolean pfEndemic) {
    this.pfEndemic = pfEndemic;
  }

  private Boolean pvEndemic;

  @Column(name = "pv_endemic")
  public Boolean getPvEndemic() {
    return pvEndemic;
  }

  public void setPvEndemic(Boolean pvEndemic) {
    this.pvEndemic = pvEndemic;
  }

  private String continent;

  @Column(name="continent_id")
  public String getContinent() {
    return continent;
  }

  public void setContinent(String continent) {
    this.continent = continent;
  }

  private Extent extent;

  @Embedded
  public Extent getExtent() {
    return extent;
  }

  public void setExtent(Extent extent) {
    this.extent = extent;
  }
  
  @Override
  public String toString() {
    return name;
  }
  
  @Transient
  public String getRepr() {
    return name;
  }
  
  @Transient
  public Integer getVersion() {
    return 0;
  }

}
