package uk.ac.ox.map.explorer.client.place;

import java.util.Map;

/**
 * 
 * @author will
 * Helps construct and serialize an order by expression.
 * Used client and server side.
 */
public class Order {
  
  private static String COL = "col";
  private static String ASC = "asc";
  
  private String colName;
  private Boolean isAsc;

  public Order(String serialization) {
    Map<String, String> params = PlaceUtils.getMapFromParams(serialization);
    colName = params.get(COL);
    isAsc = Boolean.parseBoolean(params.get(ASC));
  }
  
  public Order(String colName, Boolean isAsc) {
    this.colName = colName;
    this.isAsc = isAsc;
  }
  
  public String serialize() {
    StringBuilder sb = new StringBuilder();
    sb.append(COL).append("=").append(colName).append("&").append(ASC).append("=").append(isAsc);
    return sb.toString();
  }
  
  public String getColName() {
    return colName;
  }
  
  public Boolean isAscending() {
    return isAsc;
  }

}
