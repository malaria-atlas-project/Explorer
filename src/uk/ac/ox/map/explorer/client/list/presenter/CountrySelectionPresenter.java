package uk.ac.ox.map.explorer.client.list.presenter;

import uk.ac.ox.map.explorer.client.event.CountryCheckedEvent;
import uk.ac.ox.map.explorer.client.list.view.SelectionView;
import uk.ac.ox.map.explorer.client.place.QueryStringBuilder;
import uk.ac.ox.map.explorer.client.proxy.CountryProxy;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class CountrySelectionPresenter extends BaseSelectionPresenter<CountryProxy> {
  
  @Inject
  public CountrySelectionPresenter(SelectionView selectionView, EventBus eventBus) {
    
    this.selectionView = selectionView;
    
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    
    super.start(panel, eventBus);
    
    eventBus.addHandler(CountryCheckedEvent.TYPE, new CountryCheckedEvent.Handler() {
      @Override
      public void onCountryChecked(CountryCheckedEvent requestEvent) {
       
        addObject(requestEvent.getCountry(), requestEvent.isChecked());
      }
    });
    
    
  }

  @Override
  protected void startDownload() {
    QueryStringBuilder qsb = new QueryStringBuilder('&');
    
    String[] cids = new String[selectedObjects.size()];
    for (int i = 0; i < selectedObjects.size(); i++) {
      System.out.println(selectedObjects.get(i));
      cids[i] = selectedObjects.get(i).getId().toString();
    }
    qsb.addParam(RESOURCE_PARAM, COUNTRY_RESOURCE);
    qsb.addParam(ID_PARAM, cids);
    
    String s = qsb.finish();
    
    Window.Location.assign("dataDownload?" + s);
    
  }

}
