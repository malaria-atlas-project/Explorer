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
  
  private ExplorerPerspective perspective;
  
  private Integer ordinal;
  
  private String name;
  
  private String layers;
  
  private String image;
  
  private String infoText;
  
  private boolean useResizeTransition;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq")
  @SequenceGenerator(allocationSize = 1, name = "seq", sequenceName = "carto.job_id_seq")
  public Long getId() {
    return id;
  }
  
  @Column
  public String getImage() {
    return image;
  }
  
  @Column(name = "info_text")
  public String getInfoText() {
    return infoText;
  }
  
  @Column
  public String getLayers() {
    return layers;
  }
  
  @Column
  public String getName() {
    return name;
  }
  
  @Override
  @Column
  public Integer getOrdinal() {
    return ordinal;
  }
  
  @ManyToOne
  public ExplorerPerspective getPerspective() {
    return perspective;
  }
  
  @Column(name = "use_resize_transition")
  public boolean getUseResizeTransition() {
    return useResizeTransition;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  public void setImage(String image) {
    this.image = image;
  }
  
  public void setInfoText(String infoText) {
    this.infoText = infoText;
  }
  
  public void setLayers(String layers) {
    this.layers = layers;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  @Override
  public void setOrdinal(Integer ordinal) {
    this.ordinal = ordinal;
  }
  
  public void setPerspective(ExplorerPerspective perspective) {
    this.perspective = perspective;
  }
  
  public void setUseResizeTransition(boolean useResizeTransition) {
    this.useResizeTransition = useResizeTransition;
  }
}
