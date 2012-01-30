package uk.ac.ox.map.domain;

import uk.ac.ox.map.request.server.SimpleDao;

public class DaoFactory {
  
  public static SimpleDao get() {
    return new SimpleDao(EMF.get());
  }

}
