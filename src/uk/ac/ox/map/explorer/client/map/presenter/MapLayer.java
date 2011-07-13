package uk.ac.ox.map.explorer.client.map.presenter;

import com.google.gwt.resources.client.ImageResource;

public class MapLayer {
  
  private String layerName;
  
  public String getLayerName() {
    return layerName;
  }

  public void setLayerName(String layerName) {
    this.layerName = layerName;
  }

  private String layerType;

  public String getLayerType() {
    return layerType;
  }

  public void setLayerType(String layerType) {
    this.layerType = layerType;
  }
  
  private ImageResource imageResource;
  
  public ImageResource getImageResource() {
    return imageResource;
  }


  public void setImageResource(ImageResource imageResource) {
    this.imageResource = imageResource;
  }

}
