package uk.ac.ox.map.domain;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ExplorerPerspective.class)
public class ExplorerPerspective_ {
  
  public static volatile SingularAttribute<ExplorerPerspective, String> id;
  
  public static volatile ListAttribute<ExplorerPerspective, ExplorerLayer> layers;
  
}
