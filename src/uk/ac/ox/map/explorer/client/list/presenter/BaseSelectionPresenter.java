package uk.ac.ox.map.explorer.client.list.presenter;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.list.view.SelectionView;
import uk.ac.ox.map.explorer.client.proxy.NamedProxy;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public abstract class BaseSelectionPresenter<T extends NamedProxy> extends
    AbstractActivity {
  
  public static String ID_PARAM = "ids";
  public static String RESOURCE_PARAM = "resource";
  public static String ANO_RESOURCE = "ano";
  public static String COUNTRY_RESOURCE = "country";
  
  protected List<T> selectedObjects = new ArrayList<T>();
  
  @Inject
  protected SelectionView selectionView;
  
  protected void addObject(T obj, boolean add) {
    
    if (add) {
      selectedObjects.add(obj);
    } else {
      selectedObjects.remove(selectedObjects.indexOf(obj));
    }
    
    selectionView.setRowData(selectedObjects);
  }
  
  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(selectionView);
    selectionView.setRowData(selectedObjects);
    
    selectionView.getDownloadButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        startDownload();
      }
    });
  }
  
  protected abstract void startDownload();
  
}
