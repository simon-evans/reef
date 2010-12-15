package uk.ac.imperial.vazels.reef.client.ui;

import uk.ac.imperial.vazels.reef.client.groups.AllocateGroups;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;

public class SetupPhasePanel extends Composite {
  private final SimplePanel top;
  
  @UiField Button btnStart;
  @UiField DecoratedTabPanel tabPanel;

  private static SetupPhasePanelUiBinder uiBinder = GWT
      .create(SetupPhasePanelUiBinder.class);

  interface SetupPhasePanelUiBinder extends UiBinder<Widget, SetupPhasePanel> {
  }

  @SuppressWarnings("deprecation")
  public SetupPhasePanel(SimplePanel top) {
    initWidget(uiBinder.createAndBindUi(this));

    this.top = top;
    
    tabPanel.selectTab(0);
  }
  
  @UiHandler("btnStart")
  void start(ClickEvent event) {
    top.setWidget(new AllocateGroups());
  }
}