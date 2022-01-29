package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InformationBoundary extends Boundary {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label label;

    @FXML
    private Label title;


    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("patientmain");
    }


    @FXML
    void initialize() {
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'Information.fxml'.";
        title.setText((String) params.get((params.size()-1)));
        label.setText((String) params.get(params.size()-2));
    }
}