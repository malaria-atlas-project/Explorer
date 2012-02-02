package uk.ac.ox.map.explorer.client.resource;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;
import com.google.gwt.resources.client.ImageResource.RepeatStyle;

public interface ResourceBundle extends ClientBundle {
  
  public static final ResourceBundle INSTANCE =  GWT.create(ResourceBundle.class);

  public interface FormCss extends CssResource {
    String errorItem();
  }

  public interface BaseCss extends CssResource {
    String successMessage();
    String header();
    String nav();
    String perspective();
  }
  
  public interface PanelCss extends CssResource {
    String decoratedPanel();
    String largePanelHeader();
    String panelHeader();
    String scrollable();
  }
  
  @Source("form.css")
  public FormCss formCss();

  @Source("base.css")
  public BaseCss baseCss();
  
  @Source("panel.css")
  public PanelCss panelCss();

  @Source("success_icon.gif")
  public ImageResource successIcon();
  
  @Source("map_logo.png")
  public ImageResource mapLogo();
  
  @Source("loading_icon.gif")
  public ImageResource loadingIcon();
  
  @Source("nav-background.gif")
  @ImageOptions(repeatStyle=RepeatStyle.Horizontal)
  public ImageResource navBackground();

  @Source("alert_icon.gif")
  public ImageResource alertIcon();
  
  @Source("pf_endemicity.png")
  public ImageResource endemicityScale();
  
  @Source("country_boundary.png")
  public ImageResource countryBoundary();
  
  @Source("pr_point_key.png")
  public ImageResource prPoint();
  
}
