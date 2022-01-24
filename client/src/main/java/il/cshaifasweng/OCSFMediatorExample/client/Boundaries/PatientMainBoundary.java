package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PatientMainBoundary {

    @FXML
    private Label Plabel;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void FamilyaOrPediatrician(ActionEvent event) {

    }

    @FXML
    void LogOut(ActionEvent event) throws IOException {
        Patient patient=(Patient) SimpleClient.getParams().get(0);
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#Logout");
        message.add(patient);
        System.out.println("sending Logout from client to server");
        SimpleClient.getClient().sendToServer(message);
    }

    @FXML
    void initialize() {
        Patient patient=(Patient) SimpleClient.getParams().get(0);
        Plabel.setText(patient.getUserName());
    }

}
