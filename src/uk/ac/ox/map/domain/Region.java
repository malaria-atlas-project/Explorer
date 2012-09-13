package uk.ac.ox.map.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "explorer", name = "map_region")
public class Region {
  
  public static List<Region> all() {
    return null;
  }
  
  public static Region findRegion(Long id) {
    return null;
  }
  
  public static List<Region> search(Integer firstResult, Integer maxResults,
      String searchParams) {
    return null;
  }
  
  public static Long searchCount(String searchParams) {
    return null;
  }
  
  private Long id;
  
  private String name;
  
  private String description;
  
  private Extent extent;
  
  private List<Country> countries;
  
  @OneToMany(mappedBy = "region")
  public List<Country> getCountries() {
    return countries;
  }
  
  @Column
  public String getDescription() {
    return description;
  }
  
  @Embedded
  public Extent getExtent() {
    return extent;
  }
  
  @Id
  public Long getId() {
    return id;
  }
  
  @Column
  public String getName() {
    return name;
  }
  
  @Transient
  public Integer getVersion() {
    return 0;
  }
  
  public void setCountries(List<Country> countries) {
    this.countries = countries;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public void setExtent(Extent extent) {
    this.extent = extent;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public String toString() {
    return name;
  }
  
}
