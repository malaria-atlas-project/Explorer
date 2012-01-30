package uk.ac.ox.map.explorer.client.filter.presenter;

public interface Filter {

  String getFilterString();

  String getParameterName();

  void setValue(String value);

  void setListener(FilterListener filterListener);

}
