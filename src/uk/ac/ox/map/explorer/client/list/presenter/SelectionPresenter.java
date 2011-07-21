package uk.ac.ox.map.explorer.client.list.presenter;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ox.map.explorer.client.base.view.SelectionWidget;
import uk.ac.ox.map.explorer.client.event.CountryCheckedEvent;
import uk.ac.ox.map.explorer.client.list.view.SelectionView;
import uk.ac.ox.map.request.client.place.QueryStringBuilder;
import uk.ac.ox.map.request.client.proxy.CountryProxy;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class SelectionPresenter {
  
  private SelectionView selectionView;
  
  private List<CountryProxy> countries = new ArrayList<CountryProxy>();

  @Inject
  public SelectionPresenter(SelectionWidget selectionWidget, final SelectionView selectionView, EventBus eventBus) {
    
    selectionWidget.add(selectionView);
    this.selectionView = selectionView;
    
    eventBus.addHandler(CountryCheckedEvent.TYPE, new CountryCheckedEvent.Handler() {
      @Override
      public void onCountryChecked(CountryCheckedEvent requestEvent) {
       
        CountryProxy country = requestEvent.getCountry();
        if (requestEvent.isChecked()) {
	        countries.add(country);
        } else {
          countries.remove(countries.indexOf(country));
        }
        
		    selectionView.setRowData(countries);
      }
    });
    
    selectionView.getDownloadButton().addClickHandler(new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        QueryStringBuilder qsb = new QueryStringBuilder('&');
        
        String[] cids = new String[countries.size()];
        for (int i = 0; i < countries.size(); i++) {
          cids[i] = countries.get(i).getId();
        }
        qsb.addParam("country", cids);
        
        String s = qsb.finish();
        
		    Window.Location.assign("dataDownload?" + s);
      }
    });
    
  }


}
