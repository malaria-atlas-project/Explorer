package uk.ac.ox.map.explorer.client.proxy.seed;

public interface EntityProxy {

  public abstract Double getMinX();

  public abstract void setMinX(Double minX);

  public abstract Double getMinY();

  public abstract void setMinY(Double minY);

  public abstract Double getMaxX();

  public abstract void setMaxX(Double maxX);

  public abstract Double getMaxY();

  public abstract void setMaxY(Double maxY);

}