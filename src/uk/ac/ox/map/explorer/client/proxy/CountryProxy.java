package uk.ac.ox.map.explorer.client.proxy;

import java.io.Serializable;

public class CountryProxy implements EntityProxy, NamedProxy, Serializable {
  private static final long serialVersionUID = -4573926447852459528L; // auto
                                                                      // generated
  private String id;
  
  private String name;
  
  private Double minX;
  
  private Double minY;
  
  private Double maxX;
  
  private Double maxY;
  
  public String getId() {
    return id;
  }
  
  @Override
  public Double getMaxX() {
    return maxX;
  }
  
  @Override
  public Double getMaxY() {
    return maxY;
  }
  
  @Override
  public Double getMinX() {
    return minX;
  }
  
  @Override
  public Double getMinY() {
    return minY;
  }
  
  @Override
  public String getName() {
    return name;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  @Override
  public void setMaxX(Double maxX) {
    this.maxX = maxX;
  }
  
  @Override
  public void setMaxY(Double maxY) {
    this.maxY = maxY;
  }
  
  @Override
  public void setMinX(Double minX) {
    this.minX = minX;
  }
  
  @Override
  public void setMinY(Double minY) {
    this.minY = minY;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
}
