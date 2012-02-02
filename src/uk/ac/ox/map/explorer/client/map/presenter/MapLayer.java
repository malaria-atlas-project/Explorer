package uk.ac.ox.map.explorer.client.map.presenter;

import com.google.gwt.resources.client.ImageResource;

public class MapLayer {

  private String name;
  private String description;
  private ImageResource imageResource;
  private boolean useWebCache;

  public MapLayer(String wmsName, String description, ImageResource imageResource, boolean useWebCache) {
    this.name = wmsName;
    this.description = description;
    this.imageResource = imageResource;
    this.useWebCache = useWebCache;
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

  public boolean getUseWebCache() {
    return useWebCache;
  }

}
