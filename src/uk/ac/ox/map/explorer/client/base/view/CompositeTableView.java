package uk.ac.ox.map.explorer.client.base.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Singleton;

@Singleton
public class CompositeTableView extends Composite {

  private static CompositeTableViewUiBinder uiBinder = GWT.create(CompositeTableViewUiBinder.class);

  interface CompositeTableViewUiBinder extends UiBinder<Widget, CompositeTableView> {
  }

  public CompositeTableView() {
    initWidget(uiBinder.createAndBindUi(this));
  }

}
