package uk.ac.ox.map.request.server;

import java.io.IOException;

import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.server.DefaultExceptionHandler;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;

@Singleton
public class AppRequestFactoryServlet extends RequestFactoryServlet {
  
  private static final long serialVersionUID = 2360351980994046292L;
  
  @Inject
  public AppRequestFactoryServlet(AppServiceLayerDecorator maps) {
    super(new DefaultExceptionHandler(), maps);
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    super.doPost(request, response);
  }
  
}
