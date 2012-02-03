package uk.ac.ox.map.explorer.client.proxy.seed;

import java.io.Serializable;

import uk.ac.ox.map.explorer.client.proxy.NamedProxy;


public class AnophelineProxy implements NamedProxy, Serializable{

  private Long id;
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  

}
