package uk.ac.ox.map.domain;

import java.math.BigDecimal;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(HBS.class)
public class HBS_ {

	public static volatile SingularAttribute<HBS, Long> id;
	public static volatile SingularAttribute<HBS, String> country;
	public static volatile SingularAttribute<HBS, BigDecimal> latitude;
	public static volatile SingularAttribute<HBS, BigDecimal> longitude;
	public static volatile SingularAttribute<HBS, String> areaType;
	public static volatile SingularAttribute<HBS, Integer> sampleSize;
	public static volatile SingularAttribute<HBS, Integer> hbaa;
	public static volatile SingularAttribute<HBS, Integer> hbas;
	public static volatile SingularAttribute<Anopheline, Integer> hbss;
	public static volatile SingularAttribute<Anopheline, String> malariaHypothesis;
	public static volatile SingularAttribute<HBS, String> citation;
	public static volatile SingularAttribute<HBS, String> populationEstimates;
	public static volatile SingularAttribute<HBS, String> source;
	public static volatile SingularAttribute<HBS, String> countryId;
	    
}
