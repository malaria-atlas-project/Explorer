package uk.ac.ox.map.explorer.client.filter.view;

import uk.ac.ox.map.explorer.client.filter.presenter.Operator;

public class FilterBuilder {
  
  public static String getFilterString(String property, Operator contains, String value) {
    StringBuilder sb = new StringBuilder();
    sb.append(property).append('_').append(contains.toString()).append("=").append(value);
    return sb.toString();
  }

}
