package uk.ac.ox.map.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "explorer", name = "continent")
public class Continent implements StringType {
  
  public static List<Continent> all() {
    return null;
  }
  
  public static Country findContinent(String id) {
    return null;
  }
  
  public static List<Continent> search(Integer firstResult, Integer maxResults,
      String searchParams) {
    return null;
  }
  
  public static Long searchCount(String searchParams) {
    return null;
  }
  
  private String id;
  
  @Override
  @Id
  public String getId() {
    return id;
  }
  
  @Transient
  public Integer getVersion() {
    return 0;
  }
  
  public void persist() {
  }
  
  public void remove() {
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
