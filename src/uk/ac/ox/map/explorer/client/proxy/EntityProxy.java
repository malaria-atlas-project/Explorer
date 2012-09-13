package uk.ac.ox.map.explorer.client.proxy;

public interface EntityProxy {
  
  public abstract Double getMaxX();
  
  public abstract Double getMaxY();
  
  public abstract Double getMinX();
  
  public abstract Double getMinY();
  
  public abstract void setMaxX(Double maxX);
  
  public abstract void setMaxY(Double maxY);
  
  public abstract void setMinX(Double minX);
  
  public abstract void setMinY(Double minY);
  
}