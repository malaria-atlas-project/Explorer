package resources.util;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.vm.RequestFactorySource;

public class RequestFactoryProvider {

  public static RequestFactory get() {

    EventBus eventBus = new SimpleEventBus();
    RequestFactory requestFactory = RequestFactorySource.create(RequestFactory.class);
    requestFactory.initialize(eventBus);
    return requestFactory;
  }

}