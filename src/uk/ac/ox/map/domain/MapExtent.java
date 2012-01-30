package uk.ac.ox.map.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import uk.ac.ox.map.domain.Extent;

@Entity
@Table(schema = "vector", name = "anopheline_mapextent")
public class MapExtent  {
  
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
  
  private Anopheline anopheline;

  @OneToOne
  public Anopheline getAnopheline() {
    return anopheline;
  }

  public void setAnopheline(Anopheline anopheline) {
    this.anopheline = anopheline;
  }
  
}
