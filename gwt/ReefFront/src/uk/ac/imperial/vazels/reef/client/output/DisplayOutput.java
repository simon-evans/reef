package uk.ac.imperial.vazels.reef.client.output;

import uk.ac.imperial.vazels.reef.client.MultipleRequester;
import uk.ac.imperial.vazels.reef.client.RequestHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DisplayOutput extends Composite {
  
  private final Label testLabel = new Label("Hello World");
  private final Button refreshButton = new Button("Refresh");
  private HorizontalPanel temp = new HorizontalPanel();
  private HTML html = new HTML();
  private VerticalPanel mainPanel;
  private OutputData outputData = new OutputData("{}");
  
  public DisplayOutput() {
    
    mainPanel = new VerticalPanel();
    
    initWidget(mainPanel);
    mainPanel.setSize("521px", "100px");
    temp.setSize("521px", "22px");
    
    temp.add(testLabel);
    
    temp.add(refreshButton);
    
    refreshButton.addClickHandler(new ClickHandler() {
        @Override
        public void onClick(ClickEvent event) {
          
          refresh();
          
        }
    });
    
    mainPanel.add(temp);
  }

  public void loadData() {
    System.out.println(outputData.getData());
    
     mainPanel.add(outputData.getPanel());
  }
  
  private void refresh() {
    new OutputDataRequest().go(new RequestHandler<OutputData>(){

      @Override
      public void handle(OutputData reply, boolean success, String message) {
        if (success) {
          System.out.println(reply.getData());
          outputData.setData(reply.getData());
          loadData();
        }
      }
    });
  }
  
  private class OutputDataRequest extends MultipleRequester<OutputData>{
    OutputDataRequest() {
      super(RequestBuilder.GET, "/output/", 
          new Converter<OutputData>() {

        @Override
        public OutputData convert(String original) {
            return new OutputData(original);
        }

      });
    }
  }

}