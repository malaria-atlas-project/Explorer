package uk.ac.ox.map.explorer.list.presenter;

import java.util.List;

import uk.ac.ox.map.request.client.proxy.SurveyProxy;
import uk.ac.ox.map.request.client.request.AppRequestFactory;

import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Request;

public class SurveyPresenter extends AbstractTablePresenter<SurveyProxy> {

  @Inject
  public SurveyPresenter(PlaceController placeController, SurveyTableView display, AppRequestFactory rf) {

    super(placeController, display);
    this.dataProvider = new AbstractDataProvider<SurveyProxy>(rf, tableView) {

      @Override
      protected Request<List<SurveyProxy>> getSearchRequest(Integer i, Integer j, String jsonString) {
        return marq.surveyRequest().search(i, j, jsonString).with("site").with("surveyReplicates");
      }

      @Override
      protected Request<Long> getSearchCountRequest(String searchString) {
        return marq.surveyRequest().searchCount(searchString);
      }

    };
  }

  @Override
  public void fireObjectSelected(SurveyProxy obj) {
  }

}
