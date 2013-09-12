package uk.ac.ox.map.domain;

import java.math.BigDecimal;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(HBS.class)
public class HBS_ {

	public static volatile SingularAttribute<G6PD, Long> id;
	public static volatile SingularAttribute<G6PD, String> country;
	public static volatile SingularAttribute<G6PD, BigDecimal> latitude;
	public static volatile SingularAttribute<G6PD, BigDecimal> longitude;
	public static volatile SingularAttribute<G6PD, String> areaType;
	public static volatile SingularAttribute<G6PD, Integer> sampleSize;
	public static volatile SingularAttribute<G6PD, Integer> hbaa;
	public static volatile SingularAttribute<G6PD, Integer> hbas;
	public static volatile SingularAttribute<Anopheline, Integer> hbss;
	public static volatile SingularAttribute<Anopheline, String> malariaHypothesis;
	public static volatile SingularAttribute<G6PD, String> citation;
	public static volatile SingularAttribute<G6PD, String> populationEstimates;
	public static volatile SingularAttribute<G6PD, String> source;
	public static volatile SingularAttribute<G6PD, String> countryId;
	    
}
