package uk.ac.ox.map.explorer.client.map.presenter;

import com.google.gwt.resources.client.ImageResource;

public class MapLayer {
  
  private String name;
  private String description;
  private ImageResource imageResource;
  
  public MapLayer(String name, String description, ImageResource imageResource) {
    this.name = name;
    this.description = description;
    this.imageResource = imageResource;
  }
  
  
  public String getWmsLayerName() {
    return name;
  }

  public String getName() {
    return description;
  }
  
  public ImageResource getImageResource() {
    return imageResource;
  }

}