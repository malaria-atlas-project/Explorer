package uk.ac.ox.map.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ExplorerLayer.class)
public class ExplorerLayer_ {
  
  public static volatile SingularAttribute<ExplorerLayer, String> id;
  
  public static volatile SingularAttribute<ExplorerLayer, Integer> ordinal;
  
  public static volatile SingularAttribute<ExplorerLayer, String> name;
  
  public static volatile SingularAttribute<ExplorerLayer, String> layers;
  
  public static volatile SingularAttribute<ExplorerLayer, String> image;
  
  public static volatile SingularAttribute<ExplorerLayer, String> infoText;
  
  public static volatile SingularAttribute<ExplorerLayer, ExplorerPerspective> perspective;
  
}
