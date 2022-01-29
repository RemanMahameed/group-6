package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AppConfigurationBoundary extends Boundary {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label;


    @FXML
    void initialize() {
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'appConfiguration.fxml'.";
        int index =params.size()-1;
        label.setText((String) params.get(index));
    }
}