package uk.ac.ox.map.domain;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Factory for creating EntityManager.
 */
public final class EMF {
  private static final EntityManagerFactory emfInstance = Persistence.createEntityManagerFactory("p1");

  public static EntityManagerFactory get() {
    System.out.println( emfInstance.getProperties().get("hibernate.connection.url") );
    return emfInstance;
  }

  private EMF() {
  }
}
