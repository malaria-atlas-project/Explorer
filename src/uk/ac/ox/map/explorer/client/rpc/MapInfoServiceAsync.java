package uk.ac.ox.map.explorer.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface MapInfoServiceAsync {

  void getExtentInfo(Double minX, Double minY, Double maxX, Double maxY, AsyncCallback<String> callback);
  
}
