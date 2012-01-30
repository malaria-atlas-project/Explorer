package uk.ac.ox.map.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "types", name = "continent")
public class Continent implements StringType {
  
  public static List<Continent> search(Integer firstResult, Integer maxResults, String searchParams) {
    return null;
  }

  public static Long searchCount(String searchParams) {
    return null;
  }

  public static Country findContinent(String id) {
    return null;
  }
  
  public static List<Continent> all() {
    return null;
  }

  public void persist() {
  }

  public void remove() {
  }
  
  private String id;

  @Id
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  
  @Override
  public String toString() {
    return id;
  }
  
  @Transient
  public Integer getVersion() {
    return 0;
  }

}
