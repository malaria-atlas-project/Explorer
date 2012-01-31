package uk.ac.ox.map.explorer.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MapInfoServiceAsync {

  void getPrExtentInfo(Double minX, Double minY, Double maxX, Double maxY, AsyncCallback<String> callback);
  void getAnophelineExtentInfo(Long anoId, Double minX, Double minY, Double maxX, Double maxY, AsyncCallback<String> callback);
  
}
