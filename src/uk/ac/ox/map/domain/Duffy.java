package uk.ac.ox.map.domain;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "explorer", name = "duffy_data")
public class Duffy {

	@Id
    @Column(name = "gid")
    private Integer gid;
    @Column(name = "country")
    private String country;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "area_type")
    private String areaType;
    @Column(name = "no_examine")
    private String noExamine;
    @Column(name = "diagnostic")
    private String diagnostic;
    @Column(name = "prom_posit")
    private String promPosit;

    @Column(name = "prom_negat")
    private String promNegat;
    @Column(name = "phe_no_a_b")
    private String pheNoAB;
    @Column(name = "phe_no_a_1")
    private String pheNoA1;
    @Column(name = "phe_no_a_2")
    private String pheNoA2;
    @Column(name = "phe_no_a_3")
    private String pheNoA3;
    @Column(name = "aphe_no_a_")
    private String apheNoA;
    @Column(name = "aphe_no_a1")
    private String apheNoA1;
    @Column(name = "bphe_no_b_")
    private String bpheNoB;
    @Column(name = "bphe_no_b1")
    private String bpheNoB1;
    @Column(name = "genfyafya")
    private String genfyafya;
    @Column(name = "fya_fyb")
    private String fyaFyb;
    @Column(name = "fyb_fyb")
    private String fybFyb;
    @Column(name = "fybesfybes")
    private String fybesfybes;
    @Column(name = "fya_fybes")
    private String fyaFybes;
    @Column(name = "fyb_fybes")
    private String fybFybes;
    @Column(name = "fya_fyaes")
    private String fyaFyaes;
    @Column(name = "fyb_fyaes")
    private String fybFyaes;
    @Column(name = "fybesfyaes")
    private String fybesfyaes;
    @Column(name = "fyaesfyaes")
    private String fyaesfyaes;
    @Column(name = "citation")
    private String citation;
    @Column(name = "country_id")
    private String countryId;


    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getNoExamine() {
        return noExamine;
    }

    public void setNoExamine(String noExamine) {
        this.noExamine = noExamine;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getPromPosit() {
        return promPosit;
    }

    public void setPromPosit(String promPosit) {
        this.promPosit = promPosit;
    }

    public String getPromNegat() {
        return promNegat;
    }

    public void setPromNegat(String promNegat) {
        this.promNegat = promNegat;
    }

    public String getPheNoAB() {
        return pheNoAB;
    }

    public void setPheNoAB(String pheNoAB) {
        this.pheNoAB = pheNoAB;
    }

    public String getPheNoA1() {
        return pheNoA1;
    }

    public void setPheNoA1(String pheNoA1) {
        this.pheNoA1 = pheNoA1;
    }

    public String getPheNoA2() {
        return pheNoA2;
    }

    public void setPheNoA2(String pheNoA2) {
        this.pheNoA2 = pheNoA2;
    }

    public String getPheNoA3() {
        return pheNoA3;
    }

    public void setPheNoA3(String pheNoA3) {
        this.pheNoA3 = pheNoA3;
    }

    public String getApheNoA() {
        return apheNoA;
    }

    public void setApheNoA(String apheNoA) {
        this.apheNoA = apheNoA;
    }

    public String getApheNoA1() {
        return apheNoA1;
    }

    public void setApheNoA1(String apheNoA1) {
        this.apheNoA1 = apheNoA1;
    }

    public String getBpheNoB() {
        return bpheNoB;
    }

    public void setBpheNoB(String bpheNoB) {
        this.bpheNoB = bpheNoB;
    }

    public String getBpheNoB1() {
        return bpheNoB1;
    }

    public void setBpheNoB1(String bpheNoB1) {
        this.bpheNoB1 = bpheNoB1;
    }

    public String getGenfyafya() {
        return genfyafya;
    }

    public void setGenfyafya(String genfyafya) {
        this.genfyafya = genfyafya;
    }

    public String getFyaFyb() {
        return fyaFyb;
    }

    public void setFyaFyb(String fyaFyb) {
        this.fyaFyb = fyaFyb;
    }

    public String getFybFyb() {
        return fybFyb;
    }

    public void setFybFyb(String fybFyb) {
        this.fybFyb = fybFyb;
    }

    public String getFybesfybes() {
        return fybesfybes;
    }

    public void setFybesfybes(String fybesfybes) {
        this.fybesfybes = fybesfybes;
    }

    public String getFyaFybes() {
        return fyaFybes;
    }

    public void setFyaFybes(String fyaFybes) {
        this.fyaFybes = fyaFybes;
    }

    public String getFybFybes() {
        return fybFybes;
    }

    public void setFybFybes(String fybFybes) {
        this.fybFybes = fybFybes;
    }

    public String getFyaFyaes() {
        return fyaFyaes;
    }

    public void setFyaFyaes(String fyaFyaes) {
        this.fyaFyaes = fyaFyaes;
    }

    public String getFybFyaes() {
        return fybFyaes;
    }

    public void setFybFyaes(String fybFyaes) {
        this.fybFyaes = fybFyaes;
    }

    public String getFybesfyaes() {
        return fybesfyaes;
    }

    public void setFybesfyaes(String fybesfyaes) {
        this.fybesfyaes = fybesfyaes;
    }

    public String getFyaesfyaes() {
        return fyaesfyaes;
    }

    public void setFyaesfyaes(String fyaesfyaes) {
        this.fyaesfyaes = fyaesfyaes;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }
}
