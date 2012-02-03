package uk.ac.ox.map.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema="explorer", name="anopheline_dvs")
public class Anopheline {
  
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
  
  private String taxonomicLevel;

  @Column(name="taxonomic_level")
  public String getTaxonomicLevel() {
    return taxonomicLevel;
  }

  public void setTaxonomicLevel(String taxonomicLevel) {
    this.taxonomicLevel = taxonomicLevel;
  }
  
  private Extent extent;

  @Embedded
  public Extent getExtent() {
    return extent;
  }

  public void setExtent(Extent extent) {
    this.extent = extent;
  }
  
  @Transient
  public Integer getVersion() {
    return 0;
  }
  
  @Transient
  public String getRepr() {
    return name;
  }
  
}
