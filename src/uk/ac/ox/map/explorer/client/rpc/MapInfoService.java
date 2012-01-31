package uk.ac.ox.map.explorer.client.rpc;

import javax.inject.Singleton;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@Singleton
@RemoteServiceRelativePath("mapInfo")
public interface MapInfoService extends RemoteService {
  String getPrExtentInfo(Double minX, Double minY, Double maxX, Double maxY);
  String getAnophelineExtentInfo(Long anoId, Double minX, Double minY, Double maxX, Double maxY);
}
