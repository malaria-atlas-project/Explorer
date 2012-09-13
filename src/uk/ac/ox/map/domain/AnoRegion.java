package uk.ac.ox.map.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "explorer", name = "vector_region")
public class AnoRegion implements StringType {
  
  private String id;
  
  @Override
  @Id
  public String getId() {
    return id;
  }
  
  @Override
  public void setId(String id) {
    this.id = id;
  }
  
  @Override
  public String toString() {
    return id;
  }
  
}
