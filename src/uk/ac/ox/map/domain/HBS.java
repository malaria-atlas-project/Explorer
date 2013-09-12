package uk.ac.ox.map.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "explorer", name = "hbs_data")
public class HBS {

	@Id
	@Column(name = "id")
    private Integer id;
	    
    @Column(name = "country")
    private String country;
       
    @Column(name = "latitude")
    private BigDecimal latitude;
    
    @Column(name = "longitude")
    private BigDecimal longitude;
    
    @Column(name = "area_type")
    private String areaType;
    
    @Column(name = "sample_size")
    private Integer sampleSize;
    
    @Column(name = "hbaa")
    private Integer hbaa;
    
    @Column(name = "hbas")
    private Integer hbas;
    
    @Column(name = "hbss")
    private Integer hbss;
    
    @Column(name = "malaria_hypothesis")
    private String malariaHypothesis;
    
    @Column(name = "population_estimates")
    private String populationEstimates;
    
    @Column(name = "source")
    private String source;
    
    @Column(name = "citation")
    private String citation;
    
    @Column(name = "country_id")
    private String countryId;
    
   	public Integer getId() {
		return id;
	}
   	
   	public String getCountryId() {
		return countryId;
	}

	public String getCountry() {
		return country;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public String getAreaType() {
		return areaType;
	}

	public Integer getSampleSize() {
		return sampleSize;
	}

	public Integer getHbaa() {
		return hbaa;
	}

	public Integer getHbas() {
		return hbas;
	}

	public Integer getHbss() {
		return hbss;
	}

	public String getMalariaHypothesis() {
		return malariaHypothesis;
	}

	public String getPopulationEstimates() {
		return populationEstimates;
	}

	public String getSource() {
		return source;
	}

	public String getCitation() {
		return citation;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public void setSampleSize(Integer sampleSize) {
		this.sampleSize = sampleSize;
	}

	public void setHbaa(Integer hbaa) {
		this.hbaa = hbaa;
	}

	public void setHbas(Integer hbas) {
		this.hbas = hbas;
	}

	public void setHbss(Integer hbss) {
		this.hbss = hbss;
	}

	public void setMalariaHypothesis(String malariaHypothesis) {
		this.malariaHypothesis = malariaHypothesis;
	}

	public void setPopulationEstimates(String populationEstimates) {
		this.populationEstimates = populationEstimates;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setCitation(String citation) {
		this.citation = citation;
	}

	
}
    