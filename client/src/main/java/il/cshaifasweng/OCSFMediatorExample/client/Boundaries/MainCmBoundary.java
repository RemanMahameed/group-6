package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.ClinicManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainCmBoundary extends Boundary{
    ClinicManager clinicManager=(ClinicManager) user_Ob.get(0);
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
        message.clear();
        message.add("#GetAllClinicName");
        message.add("UpdateOperatingHours"); // add the flag (Why we need Clinic name)
        SimpleClient.getClient().sendToServer(message);
        //App.setRoot("UpdateOperatingHours");
    }

    @FXML
    void ViewReports(ActionEvent event) {

    }

    @FXML
    void initialize() {

    }

}
