package uk.ac.ox.map.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Country.class)
public abstract class Country_ {
  
  public static volatile SingularAttribute<Country, String> id;
  public static volatile SingularAttribute<Country, Region> region;
  public static volatile SingularAttribute<Country, String> name;
  public static volatile SingularAttribute<Country, Boolean> pvEndemic;
  public static volatile SingularAttribute<Country, Boolean> pfEndemic;
  public static volatile SingularAttribute<Country, String> continent;
  public static volatile SingularAttribute<Country, Integer> rasterId;
  public static volatile SingularAttribute<Country, Extent> extent;
  
}
