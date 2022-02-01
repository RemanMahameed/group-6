package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainHmBoundary extends Boundary{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void CloseSystem(ActionEvent event) {

    }

    @FXML
    void OpenSystem(ActionEvent event) {

    }

    @FXML
    void UpdateOperatingHours(ActionEvent event) throws IOException {
        App.setRoot("UpdateOperatingHours");
    }

    @FXML
    void ViewReports(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
