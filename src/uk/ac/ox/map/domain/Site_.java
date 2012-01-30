package uk.ac.ox.map.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.vividsolutions.jts.geom.Point;

@StaticMetamodel(Site.class)
public abstract class Site_ {

	public static volatile SingularAttribute<Site, Point> geom;
	public static volatile SingularAttribute<Site, BigDecimal> latitudeMbg;
	public static volatile SingularAttribute<Site, Double> pointDensity;
	public static volatile SingularAttribute<Site, Double> km2;
	public static volatile SingularAttribute<Site, String> latLongSource;
	public static volatile SingularAttribute<Site, Integer> version;
	public static volatile SingularAttribute<Site, Timestamp> timeUpdated;
	public static volatile SingularAttribute<Site, String> colour;
	public static volatile SingularAttribute<Site, String> vectorSiteNotes;
	public static volatile SingularAttribute<Site, String> areaType;
	public static volatile SingularAttribute<Site, BigDecimal> longitude;
	public static volatile SingularAttribute<Site, String> countryId;
	public static volatile SingularAttribute<Site, Double> snapDistance;
	public static volatile SingularAttribute<Site, String> generatedCountryId;
	public static volatile SingularAttribute<Site, Boolean> rice;
	public static volatile SingularAttribute<Site, Boolean> forest;
	public static volatile SingularAttribute<Site, String> ruralUrban;
	public static volatile SingularAttribute<Site, String> country;
	public static volatile SingularAttribute<Site, Integer> adminId;
	public static volatile SingularAttribute<Site, Long> id;
	public static volatile SingularAttribute<Site, String> siteNotes;
	public static volatile SingularAttribute<Site, Boolean> bestguessRough;
	public static volatile SingularAttribute<Site, Integer> admin1Id;
	public static volatile SingularAttribute<Site, String> name;
	public static volatile SingularAttribute<Site, BigDecimal> latitude;
	public static volatile SingularAttribute<Site, Boolean> bestguessGood;
	public static volatile SingularAttribute<Site, BigDecimal> longitudeMbg;
	public static volatile SingularAttribute<Site, Long> generatedAdminId;
	public static volatile SingularAttribute<Site, String> notes;
}

