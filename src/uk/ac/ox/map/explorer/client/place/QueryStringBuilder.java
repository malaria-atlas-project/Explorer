package uk.ac.ox.map.explorer.client.place;

/**
 * 
 * @author will Encapsulation of a string builder, allowing method chaining when
 *         building query strings.
 * 
 */
public class QueryStringBuilder {
  
  private StringBuilder sb = new StringBuilder();
  private final char delimiter;
  
  public QueryStringBuilder(char delimiter) {
    this.delimiter = delimiter;
  }
  
  /**
   * Add a parameter to the querystring, returning self to allow chaining.
   * 
   * @param param
   * @return
   */
  public QueryStringBuilder addParam(String param) {
    if (param.isEmpty()) {
      return this;
    }
    if (sb.length() > 1) {
      sb.append(delimiter);
    }
    sb.append(param);
    return this;
  }
  
  public QueryStringBuilder addParam(String paramIdentifier, String... param) {
    
    if (sb.length() > 1) {
      sb.append(delimiter);
    }
    sb.append(paramIdentifier);
    sb.append('=');
    for (int i = 0; i < param.length; i++) {
      if (i != 0) {
        sb.append(',');
      }
      sb.append(param[i]);
    }
    return this;
  }
  
  public QueryStringBuilder addParam(String paramIdentifier, String param,
      char separator) {
    if (param == null) {
      return this;
    }
    if (param.isEmpty()) {
      return this;
    }
    if (sb.length() > 1) {
      sb.append(delimiter);
    }
    sb.append(paramIdentifier).append(separator).append(param);
    return this;
  }
  
  public String finish() {
    return sb.toString();
  }
  
}
