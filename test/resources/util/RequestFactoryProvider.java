package resources.util;

import uk.ac.ox.map.request.client.request.AppRequestFactory;
import uk.ac.ox.map.request.server.AppServiceLayerDecorator;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Injector;
import com.google.web.bindery.requestfactory.server.ServiceLayer;
import com.google.web.bindery.requestfactory.server.SimpleRequestProcessor;
import com.google.web.bindery.requestfactory.server.testing.InProcessRequestTransport;
import com.google.web.bindery.requestfactory.vm.RequestFactorySource;

public class RequestFactoryProvider {

  public static AppRequestFactory get() {

    Injector injector = TestResourceFactory.getInjector();

    ServiceLayer serviceLayer = ServiceLayer.create(injector.getInstance(AppServiceLayerDecorator.class));

    SimpleRequestProcessor processor = new SimpleRequestProcessor(serviceLayer);

    EventBus eventBus = new SimpleEventBus();
    AppRequestFactory requestFactory = RequestFactorySource.create(AppRequestFactory.class);
    requestFactory.initialize(eventBus, new InProcessRequestTransport(processor));
    return requestFactory;
  }

}