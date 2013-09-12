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
@Table(schema = "explorer", name = "country_all")
public class CountryAll {
  
  private String id;
  
  private Region region;
  
  private String name;
  
  private Boolean pfEndemic;
  
  private Boolean pvEndemic;
  
  private String continent;
  
  private Extent extent;
  
  @Column(name = "continent_id")
  public String getContinent() {
    return continent;
  }
  
  @Embedded
  public Extent getExtent() {
    return extent;
  }
  
  @Id
  public String getId() {
    return id;
  }
  
  @Column(name = "name")
  public String getName() {
    return name;
  }
  
  @Column(name = "pf_endemic")
  public Boolean getPfEndemic() {
    return pfEndemic;
  }
  
  @Column(name = "pv_endemic")
  public Boolean getPvEndemic() {
    return pvEndemic;
  }
  
  @ManyToOne
  @JoinColumn(name = "map_region_id")
  public Region getRegion() {
    return region;
  }
  
  @Transient
  public String getRepr() {
    return name;
  }
  
  @Transient
  public Integer getVersion() {
    return 0;
  }
  
  public void setContinent(String continent) {
    this.continent = continent;
  }
  
  public void setExtent(Extent extent) {
    this.extent = extent;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setPfEndemic(Boolean pfEndemic) {
    this.pfEndemic = pfEndemic;
  }
  
  public void setPvEndemic(Boolean pvEndemic) {
    this.pvEndemic = pvEndemic;
  }
  
  public void setRegion(Region region) {
    this.region = region;
  }
  
  @Override
  public String toString() {
    return name;
  }
  
}

