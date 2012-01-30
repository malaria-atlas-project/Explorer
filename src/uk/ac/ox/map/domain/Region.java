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
@Table(schema = "public", name = "map_region")
public class Region {
  
  public static List<Region> search(Integer firstResult, Integer maxResults, String searchParams) {
    return null;
  }

  public static Long searchCount(String searchParams) {
    return null;
  }

  public static Region findRegion(Long id) {
    return null;
  }
  
  public static List<Region> all() {
    return null;
  }

  private Long id;

  @Id
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  private String name;

  @Column
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  private String description;
  
  @Column
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  private Extent extent;

  @Embedded
  public Extent getExtent() {
    return extent;
  }

  public void setExtent(Extent extent) {
    this.extent = extent;
  }
  
  private List<Country> countries;

  @OneToMany(mappedBy = "region")
  public List<Country> getCountries() {
    return countries;
  }
  
  public void setCountries(List<Country> countries) {
    this.countries = countries;
  }
  
  @Override
  public String toString() {
    return name;
  }
  
  @Transient
  public Integer getVersion() {
    return 0;
  }

}
