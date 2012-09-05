package uk.ac.ox.map.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.Point;

@Entity

@NamedNativeQueries ({
		@NamedNativeQuery(
		    name="findSitesByCoordinate", 
		    query="select * from explorer.site s where st_distance(s.geom, ST_SetSRID(st_makepoint(?,?), 4326)) < ? order by st_distance(s.geom, ST_SetSRID(st_makepoint(?,?), 4326))",
		    resultClass = Site.class
		)
})

public class Site {

  public static List<Site> getSitesByCoordinate(Double lon, Double lat, Double dist, Integer limit) {
    
    EntityManager em = EMF.get().createEntityManager();
    TypedQuery<Site> q = em.createNamedQuery("findSitesByCoordinate", Site.class);
    q.setParameter(1, lon);
    q.setParameter(2, lat);
    q.setParameter(3, dist);
    q.setParameter(4, lon);
    q.setParameter(5, lat);
    q.setMaxResults(limit);
    
    return q.getResultList();
  }
  
  private Long id;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO, generator="siteIdGenerator")
  @SequenceGenerator(allocationSize = 1, name = "siteIdGenerator", sequenceName = "site_id_seq")
  public Long getId() {
    return id;
  }

  public void setId(Long siteId) {
    this.id = siteId;
  }

  private Integer adminId;

  @Column(name = "admin_id")
  public Integer getAdminId() {
    return adminId;
  }

  public void setAdminId(Integer adminId) {
    this.adminId = adminId;
  }

  private BigDecimal latitude;

  @Column(name = "latitude")
  public BigDecimal getLatitude() {
    return latitude;
  }

  public void setLatitude(BigDecimal latitude) {
    this.latitude = latitude;
  }
  
  private Point geom;
  
  @Column
  @Type(type="org.hibernatespatial.GeometryUserType")
  public Point getGeom() {
    return geom;
  }
  public void setGeom(Point geom) {
    this.geom = geom;
  }
  

  private BigDecimal longitude;

  @Column(name = "longitude")
  public BigDecimal getLongitude() {
    return longitude;
  }

  public void setLongitude(BigDecimal longitude) {
    this.longitude = longitude;
  }

  private BigDecimal latitudeMbg;

  @Column(name = "latitude_mbg")
  public BigDecimal getLatitudeMbg() {
    return latitudeMbg;
  }

  public void setLatitudeMbg(BigDecimal latitudeMbg) {
    this.latitudeMbg = latitudeMbg;
  }

  private BigDecimal longitudeMbg;

  @Column(name = "longitude_mbg")
  public BigDecimal getLongitudeMbg() {
    return longitudeMbg;
  }

  public void setLongitudeMbg(BigDecimal longitudeMbg) {
    this.longitudeMbg = longitudeMbg;
  }

  private String name;

  @Column
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  private String latLongSource;

  @Column(name = "latlong_source_id")
  public String getLatLongSource() {
    return latLongSource;
  }

  public void setLatLongSource(String latlongSource) {
    this.latLongSource = latlongSource;
  }

  private Boolean bestguessGood;

  @Column(name = "bestguess_good")
  @NotNull
  public Boolean isBestguessGood() {
    return bestguessGood;
  }

  public void setBestguessGood(Boolean bestguessGood) {
    this.bestguessGood = bestguessGood;
  }

  private Boolean bestguessRough;

  @Column(name = "bestguess_rough")
  @NotNull
  public Boolean isBestguessRough() {
    return bestguessRough;
  }

  public void setBestguessRough(Boolean bestguessRough) {
    this.bestguessRough = bestguessRough;
  }

  private String vectorSiteNotes;

  @Column(name = "vector_site_notes")
  public String getVectorSiteNotes() {
    return vectorSiteNotes;
  }

  public void setVectorSiteNotes(String vectorSiteNotes) {
    this.vectorSiteNotes = vectorSiteNotes;
  }

  private String notes;

  @Column(name = "notes")
  public String getNotes() {
    return notes;
  }

  public void setNotes(String siteNotes) {
    this.notes = siteNotes;
  }

  private Country country;

  @ManyToOne(fetch=FetchType.EAGER)
  @JoinColumn(name="country_id")
  @NotNull
  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  private String areaType;

  @Column(name = "area_type_id")
  public String getAreaType() {
    return areaType;
  }

  public void setAreaType(String areaType) {
    this.areaType = areaType;
  }

  private String ruralUrban;

  @Column(name = "rural_urban")
  public String getRuralUrban() {
    return ruralUrban;
  }

  public void setRuralUrban(String ruralUrban) {
    this.ruralUrban = ruralUrban;
  }

  private Date dateUpdated;

  @Column(name = "date_updated")
  public Date getTimeUpdated() {
    return dateUpdated;
  }

  //TODO: rename col in db
  public void setTimeUpdated(Date timeUpdated) {
    this.dateUpdated = timeUpdated;
  }

  private String generatedCountryId;

  @Column(name = "generated_country_id")
  @Basic
  public String getGeneratedCountryId() {
    return generatedCountryId;
  }

  public void setGeneratedCountryId(String generatedCountryId) {
    this.generatedCountryId = generatedCountryId;
  }

  private Integer generatedAdminId;

  @Column(name = "generated_admin_id")
  public Integer getGeneratedAdminId() {
    return generatedAdminId;
  }

  public void setGeneratedAdminId(Integer generatedAdminId) {
    this.generatedAdminId = generatedAdminId;
  }
  
  @Transient
  public Integer getVersion() {
    return 0;
  }

}
