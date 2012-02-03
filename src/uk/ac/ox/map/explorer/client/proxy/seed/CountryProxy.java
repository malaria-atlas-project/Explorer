package uk.ac.ox.map.explorer.client.proxy.seed;

import uk.ac.ox.map.explorer.client.proxy.NamedProxy;


public class CountryProxy implements NamedProxy {

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  private String id;
  private String name;
  

}
