package uk.ac.ox.map.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "explorer", name = "g6pd_data")
public class G6PD {

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
    
    @Column(name = "sexes")
    private String sexes;
    
    @Column(name = "number_males")
    private Integer numberMales;
    
    @Column(name = "number_males_deficient")
    private Integer numberMalesDeficient;
    
    @Column(name = "number_females")
    private Integer numberFemales;
    
    @Column(name = "number_females_deficient")
    private Integer numberFemalesDeficient;
    
    @Column(name = "citation")
    private String citation;
    
    @Column(name = "area_size")
    private Integer areaSize;
   
    @Column(name = "country_id")
    private String countryId;
    
    public String getCountryId() {
		return countryId;
	}


	public Integer getId() {
        return id;
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


    public String getSexes() {
        return sexes;
    }


    public Integer getNumberMales() {
        return numberMales;
    }


    public Integer getNumberMalesDeficient() {
        return numberMalesDeficient;
    }


    public Integer getNumberFemales() {
        return numberFemales;
    }


    public Integer getNumberFemalesDeficient() {
        return numberFemalesDeficient;
    }


    public String getCitation() {
        return citation;
    }


    public Integer getAreaSize() {
        return areaSize;
    }


	
}
