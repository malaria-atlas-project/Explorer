package uk.ac.ox.map.explorer.client.proxy;

import java.math.BigDecimal;
import java.util.Date;

import uk.ac.ox.map.domain.Site;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Site.class)
public interface SiteProxy extends EntityProxy {
	Long getId();
	void setId(Long id);
	
	String getName();
	void setName(String name);
	
	BigDecimal getLatitude();
	void setLatitude(BigDecimal lat);
	
	BigDecimal getLongitude();
	void setLongitude(BigDecimal lon);
	
  String getLatLongSource();
  void setLatLongSource(String latlongSource);

  CountryProxy getCountry();
  void setCountry(CountryProxy country);
	
  String getAreaType();
  void setAreaType(String areaType);
  
  String getRuralUrban();
  void setRuralUrban(String ruralUrban);
  
  String getNotes();
  void setNotes(String notes);

  Date getTimeUpdated();
  void setTimeUpdated(Date dateUpdated);
	
}
