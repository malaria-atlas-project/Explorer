package uk.ac.ox.map.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(schema = "explorer", name = "explorer_layer")
public class ExplorerLayer implements HasOrdinal {
  
  private Long id;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO, generator="seq")
  @SequenceGenerator(allocationSize = 1, name = "seq", sequenceName = "carto.job_id_seq")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  private ExplorerPerspective perspective;
  
  @ManyToOne
  public ExplorerPerspective getPerspective() {
    return perspective;
  }

  public void setPerspective(ExplorerPerspective perspective) {
    this.perspective = perspective;
  }
  
  private Integer ordinal;

  @Column
  public Integer getOrdinal() {
    return ordinal;
  }

  public void setOrdinal(Integer ordinal) {
    this.ordinal = ordinal;
  }

  private String name;
  
  @Column
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  
  private String layers;

  @Column
  public String getLayers() {
    return layers;
  }

  public void setLayers(String layers) {
    this.layers = layers;
  }
  
  private String image;
  
  @Column
  public String getImage() {
    return image;
  }
  
  public void setImage(String image) {
    this.image = image;
  }
  
  private String infoText;
  
  @Column(name = "info_text")
  public String getInfoText() {
    return infoText;
  }
  
  public void setInfoText(String infoText) {
    this.infoText = infoText;
  }

}
