package uk.ac.ox.map.explorer.client.filter.presenter;

/**
 * All filters implement this.
 * 
 * @author will
 */
public interface Filter {
  
  /**
   * The query string which is normally sent to the server in a URL.
   * 
   * @return
   */
  String getFilterString();
  
  /**
   * The name of the object property being filtered.
   * 
   * @return
   */
  String getParameterName();
  
  /**
   * Sets the filter presenter listening for filter changes.
   * 
   * @param filterListener
   */
  void setListener(FilterListener filterListener);
  
  /**
   * Sets the value held by the form component used in the filter.
   * 
   * @param value
   */
  void setValue(String value);
  
}
