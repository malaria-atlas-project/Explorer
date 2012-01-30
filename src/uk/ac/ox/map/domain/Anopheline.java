package uk.ac.ox.map.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema="explorer", name="anopheline_dvs")
public class Anopheline {
  
  public static List<Anopheline> search(Integer firstResult, Integer maxResults, String searchParams) {
    return DaoFactory.get().search(firstResult, maxResults, searchParams, Anopheline.class);
  }

  public static Long searchCount(String searchParams) {
    return DaoFactory.get().searchCount(searchParams, Anopheline.class);
  }

  public static Anopheline findAnopheline(String id) {
    return DaoFactory.get().find(Anopheline.class, id);
  }
  
  public static List<Anopheline> all() {
    return DaoFactory.get().all(Anopheline.class);
  }
  
  private Long id;
  
  @Id
  @Column
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  

  private String name;
  
  @Column
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  private String subGenus;
  
  @Column(name="sub_genus")
  public String getSubGenus() {
    return subGenus;
  }

  public void setSubGenus(String subGenus) {
    this.subGenus = subGenus;
  }
  
  private String scientificAbbr;
  
  @Column(name="scientific_abbr")
  public String getScientificAbbr() {
    return scientificAbbr;
  }

  public void setScientificAbbr(String scientificAbbr) {
    this.scientificAbbr = scientificAbbr;
  }

  private String scientificName;
  
  @Column(name="scientific_name")
  public String getScientificName() {
    return scientificName;
  }

  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }
  
  private Boolean isDvs;

  @Column(name = "is_dvs")
  public Boolean getIsDvs() {
    return isDvs;
  }

  public void setIsDvs(Boolean isDvs) {
    this.isDvs = isDvs;
  }

  private String region;

  @Column(name="region_id")
  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  private String species;
  
  @Column
  public String getSpecies() {
    return species;
  }

  public void setSpecies(String species) {
    this.species = species;
  }

  private String abbreviation;

  @Column
  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }
  
  private String author;

  @Column
  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
  
  private Anopheline parent;

  @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinColumn(name = "parent_id")
  public Anopheline getParent() {
    return parent;
  }

  public void setParent(Anopheline parent) {
    this.parent = parent;
  }
  
  private String taxonomicLevel;

  @Column(name="taxonomic_level")
  public String getTaxonomicLevel() {
    return taxonomicLevel;
  }

  public void setTaxonomicLevel(String taxonomicLevel) {
    this.taxonomicLevel = taxonomicLevel;
  }
  
  private MapExtent mapExtent;

  @OneToOne(mappedBy = "anopheline")
  public MapExtent getMapExtent() {
    return mapExtent;
  }

  public void setMapExtent(MapExtent mapExtent) {
    this.mapExtent = mapExtent;
  }
  
}
