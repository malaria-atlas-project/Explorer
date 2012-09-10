package uk.ac.ox.map.explorer.client.proxy;

import java.io.Serializable;



public class AnophelineProxy  implements EntityProxy, NamedProxy, Serializable{
  private static final long serialVersionUID = -6308673010475937442L; //auto generated
  private Long id;
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  private Double minX;
  
  public Double getMinX() {
    return minX;
  }

  public void setMinX(Double minX) {
    this.minX = minX;
  }

  private Double minY;

  public Double getMinY() {
    return minY;
  }

  public void setMinY(Double minY) {
    this.minY = minY;
  }

  private Double maxX;

  public Double getMaxX() {
    return maxX;
  }

  public void setMaxX(Double maxX) {
    this.maxX = maxX;
  }

  private Double maxY;

  public Double getMaxY() {
    return maxY;
  }

  public void setMaxY(Double maxY) {
    this.maxY = maxY;
  }
  

}
