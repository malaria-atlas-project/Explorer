package uk.ac.ox.map.request.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;



import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.web.bindery.requestfactory.server.ServiceLayer;
import com.google.web.bindery.requestfactory.server.ServiceLayerDecorator;


/**
 * Implements all methods that interact with domain objects.
 */
@Singleton
public class AppServiceLayerDecorator extends ServiceLayerDecorator {

  private static final Validator jsr303Validator;
  private static final Logger log = Logger.getLogger(ServiceLayer.class.getName());
  private Provider<EntityManager> provider;
  private Provider<SimpleDao> daoProvider;
	
	@Inject
	public AppServiceLayerDecorator(Provider<EntityManager> p, Provider<SimpleDao> sd) {
	  provider = p;
	  daoProvider = sd;
  }
	
	private SimpleDao getDao() {
	  SimpleDao x = daoProvider.get();
	  return x;
	}
	
	private EntityManager getEntityManager() {
	  return provider.get();
	}
	

  static {
    Validator found;
    try {
      ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
      found = validatorFactory.getValidator();
    } catch (ValidationException e) {
      log.log(Level.INFO, "Unable to initialize a JSR 303 Bean Validator", e);
      found = null;
    }
    jsr303Validator = found;
  }

  @Override
  public Object getVersion(Object domainObject) {
    return getTop().getProperty(domainObject, "version");
  }

  @Override
  public Object invoke(Method domainMethod, Object... args) {
    Throwable ex;
    try {
      domainMethod.setAccessible(true);
      if (domainMethod.getName().equals("persist")) {
        return getDao().persist(args[0]);
      } else if (domainMethod.getName().startsWith("find")) {
        return getDao().find(domainMethod.getDeclaringClass(), args[0]);
      } else if (domainMethod.getName().equals("remove")) {
        getDao().remove(domainMethod.getDeclaringClass(), args[0]);
        return null;
      } else if (domainMethod.getName().equals("search")) {
        return getDao().search((Integer)args[0], (Integer)args[1], (String)args[2], domainMethod.getDeclaringClass());
      } else if (domainMethod.getName().equals("searchCount")) {
        return getDao().searchCount((String)args[0], domainMethod.getDeclaringClass());
      } else if (domainMethod.getName().equals("all")) {
        return getDao().all(domainMethod.getDeclaringClass());
      } else if (Modifier.isStatic(domainMethod.getModifiers())) {
        return domainMethod.invoke(null, args);
      } else {
        Object[] realArgs = new Object[args.length - 1];
        System.arraycopy(args, 1, realArgs, 0, realArgs.length);
        return domainMethod.invoke(args[0], realArgs);
      }
    } catch (IllegalArgumentException e) {
      ex = e;
    } catch (IllegalAccessException e) {
      ex = e;
    } catch (InvocationTargetException e) {
      return report(e);
    }
    return die(ex, "Could not invoke method %s", domainMethod.getName());
  }

  /**
   * This implementation attempts to re-load the object from the backing store.
   */
  @Override
  public boolean isLive(Object domainObject) {
    Object id = getId(domainObject);
    return getDao().find(domainObject.getClass(), id) != null;
  }

  @Override
  public <T> T loadDomainObject(Class<T> clazz, Object id) {
    if (id == null) {
      die(null, "Cannot invoke find method with a null id");
    }
    return getEntityManager().find(clazz, id);
  }

  @Override
  public <T> Set<ConstraintViolation<T>> validate(T domainObject) {
    if (jsr303Validator != null) {
      return jsr303Validator.validate(domainObject);
    }
    return Collections.emptySet();
  }
}
