package uk.ac.ox.map.explorer.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface ResourceBundle extends ClientBundle {
  
  public interface BaseCss extends CssResource {
    String header();
    
    String help();
    
    String perspective();
  }
  
  public interface FormCss extends CssResource {
    String errorItem();
  }
  
  public interface PanelCss extends CssResource {
    String decoratedPanel();
    
    String largePanelHeader();
    
    String panelHeader();
    
    String scrollable();
  }
  
  public interface PopupCss extends CssResource {
    String panel();
  }
  
  public static final ResourceBundle INSTANCE = GWT
      .create(ResourceBundle.class);
  
  @Source("base.css")
  public BaseCss baseCss();
  
  @Source("info-button-down.png")
  public ImageResource infoButtonDown();
  
  @Source("info-button-up.png")
  public ImageResource infoButtonUp();
  
  @Source("map_logo.png")
  public ImageResource mapLogo();
  
  @Source("panel.css")
  public PanelCss panelCss();
  
  @Source("popup.css")
  public PopupCss popupCss();
  
}
