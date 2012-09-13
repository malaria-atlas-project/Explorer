package uk.ac.ox.map.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "explorer", name = "anopheline_dvs")
public class Anopheline {
  
  private Long id;
  
  private String name;
  
  private String subGenus;
  
  private String scientificAbbr;
  
  private String scientificName;
  
  private Boolean isDvs;
  
  private String region;
  
  private String species;
  
  private String abbreviation;
  
  private String author;
  
  private String taxonomicLevel;
  
  private Extent extent;
  
  @Column
  public String getAbbreviation() {
    return abbreviation;
  }
  
  @Column
  public String getAuthor() {
    return author;
  }
  
  @Embedded
  public Extent getExtent() {
    return extent;
  }
  
  @Id
  @Column
  public Long getId() {
    return id;
  }
  
  @Column(name = "is_dvs")
  public Boolean getIsDvs() {
    return isDvs;
  }
  
  @Column
  public String getName() {
    return name;
  }
  
  @Column(name = "region_id")
  public String getRegion() {
    return region;
  }
  
  @Transient
  public String getRepr() {
    return name;
  }
  
  @Column(name = "scientific_abbr")
  public String getScientificAbbr() {
    return scientificAbbr;
  }
  
  @Column(name = "scientific_name")
  public String getScientificName() {
    return scientificName;
  }
  
  @Column
  public String getSpecies() {
    return species;
  }
  
  @Column(name = "sub_genus")
  public String getSubGenus() {
    return subGenus;
  }
  
  @Column(name = "taxonomic_level")
  public String getTaxonomicLevel() {
    return taxonomicLevel;
  }
  
  @Transient
  public Integer getVersion() {
    return 0;
  }
  
  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }
  
  public void setAuthor(String author) {
    this.author = author;
  }
  
  public void setExtent(Extent extent) {
    this.extent = extent;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public void setIsDvs(Boolean isDvs) {
    this.isDvs = isDvs;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setRegion(String region) {
    this.region = region;
  }
  
  public void setScientificAbbr(String scientificAbbr) {
    this.scientificAbbr = scientificAbbr;
  }
  
  public void setScientificName(String scientificName) {
    this.scientificName = scientificName;
  }
  
  public void setSpecies(String species) {
    this.species = species;
  }
  
  public void setSubGenus(String subGenus) {
    this.subGenus = subGenus;
  }
  
  public void setTaxonomicLevel(String taxonomicLevel) {
    this.taxonomicLevel = taxonomicLevel;
  }
  
}
