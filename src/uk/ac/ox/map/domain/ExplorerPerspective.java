package uk.ac.ox.map.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(schema = "explorer", name = "explorer_perspective")
public class ExplorerPerspective implements StringType {

  private String id;

  @Id
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  
  private List<ExplorerLayer> layers;
  
  @OneToMany(mappedBy = "perspective", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @OrderBy("ordinal")
  public List<ExplorerLayer> getLayers() {
    return layers;
  }

  public void setLayers(List<ExplorerLayer> layers) {
    
    this.layers = layers;
    
  }

  @Override
  public String toString() {
    return id;
  }
  
}
