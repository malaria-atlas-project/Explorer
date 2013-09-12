package uk.ac.ox.map.domain;

import java.math.BigDecimal;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(G6PD.class)
public class G6PD_ {

	public static volatile SingularAttribute<G6PD, Long> id;
	public static volatile SingularAttribute<G6PD, String> country;
	public static volatile SingularAttribute<G6PD, BigDecimal> latitude;
	public static volatile SingularAttribute<G6PD, BigDecimal> longitude;
	public static volatile SingularAttribute<G6PD, String> areaType;
	public static volatile SingularAttribute<G6PD, String> sexes;
	public static volatile SingularAttribute<G6PD, Integer> numberMales;
	public static volatile SingularAttribute<G6PD, Integer> numberMalesDeficient;
	public static volatile SingularAttribute<Anopheline, Integer> numberFemales;
	public static volatile SingularAttribute<Anopheline, Integer> numberFemalesDeficient;
	public static volatile SingularAttribute<G6PD, String> citation;
	public static volatile SingularAttribute<G6PD, Integer> areaSize;
	public static volatile SingularAttribute<G6PD, String> countryId;
}
