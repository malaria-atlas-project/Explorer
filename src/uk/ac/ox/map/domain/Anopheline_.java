package uk.ac.ox.map.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Anopheline.class)
public class Anopheline_ {
  
  public static volatile SingularAttribute<Anopheline, Long> id;
  
  public static volatile SingularAttribute<Anopheline, String> name;
  
  public static volatile SingularAttribute<Anopheline, String> subGenus;
  
  public static volatile SingularAttribute<Anopheline, String> scientificAbbr;
  
  public static volatile SingularAttribute<Anopheline, String> scientificName;
  
  public static volatile SingularAttribute<Anopheline, Boolean> isDvs;
  
  public static volatile SingularAttribute<Anopheline, String> region;

  public static volatile SingularAttribute<Anopheline, String> species;
  
  public static volatile SingularAttribute<Anopheline, String> abbreviation;
  
  public static volatile SingularAttribute<Anopheline, String> author;
  
  public static volatile SingularAttribute<Anopheline, Anopheline> parent;
  
  public static volatile SingularAttribute<Anopheline, String> taxonomicLevel;
  
  public static volatile SingularAttribute<Anopheline, MapExtent> mapExtent;
  
  
}
