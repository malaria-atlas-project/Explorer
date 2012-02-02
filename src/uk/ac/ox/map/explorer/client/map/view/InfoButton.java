package uk.ac.ox.map.explorer.client.map.view;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.CustomButton;
import com.google.gwt.user.client.ui.Image;

public class InfoButton extends CustomButton {
  
  public InfoButton(ImageResource imageResource, ImageResource imageResource2) {
    super(new Image(imageResource), new Image(imageResource2));
  }
  
}
