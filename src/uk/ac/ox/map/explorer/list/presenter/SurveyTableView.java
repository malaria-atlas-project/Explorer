package uk.ac.ox.map.explorer.list.presenter;

import uk.ac.ox.map.request.client.place.EditEntityPlace;
import uk.ac.ox.map.request.client.proxy.SiteProxy;
import uk.ac.ox.map.request.client.proxy.SurveyProxy;
import uk.ac.ox.map.request.client.widget.HyperlinkCell;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;

@Singleton
public class SurveyTableView extends TableView<SurveyProxy> {

  @Inject
  public SurveyTableView(@Named("TABLE_SIZE") Integer pageSize, final PlaceHistoryMapper mapper) {

    super(pageSize);
    
    HyperlinkCell hyperlinkCell = new HyperlinkCell();
    addSortableColumn(new Column<SurveyProxy, Hyperlink>(hyperlinkCell) {
      @Override
      public Hyperlink getValue(SurveyProxy object) {
        return new Hyperlink(object.getId().toString(), mapper.getToken(new EditEntityPlace("Survey", object.getId())));
      }
    }, "Id", "id");

    addColumn(new TextColumn<SurveyProxy>() {
      public String getValue(SurveyProxy obj) {
        return obj.getRdtType();
      }
    }, "RDT type");

    addColumn(new TextColumn<SurveyProxy>() {
      public String getValue(SurveyProxy obj) {
        return obj.getMethod();
      }
    }, "Method");

    addColumn(new TextColumn<SurveyProxy>() {
      public String getValue(SurveyProxy obj) {
        SiteProxy site = obj.getSite();
        return (site != null) ? site.getName() : null;
      }
    }, "Site name");
  }

}
