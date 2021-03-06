package uk.ac.ox.map.explorer.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>MapInfoService</code>.
 */
public interface MapInfoServiceAsync {
  
  void getAnophelineExtentInfo(Boolean presencePointsEnabled,
      Boolean absencePointsEnabled, Long anoId, Double minX, Double minY,
      Double maxX, Double maxY, AsyncCallback<String> callback);
  
  void getPrExtentInfo(Boolean availablePointsEnabled,
      Boolean unavailablePointsEnabled, Double minX, Double minY, Double maxX,
      Double maxY, AsyncCallback<String> callback);
  
}
