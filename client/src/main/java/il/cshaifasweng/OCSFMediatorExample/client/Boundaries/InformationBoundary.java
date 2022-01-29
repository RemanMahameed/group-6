package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InformationBoundary extends PatientMainBoundary {
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
        message.clear();
        message.add("#BackToMainPatientboundary");
        message.add(patient.getId());
        message.add("Patient");
        System.out.println("sending BackToMainPatientboundary from client to server");
        SimpleClient.getClient().sendToServer(message);
    }


    @FXML
    void initialize() {
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'Information.fxml'.";
        title.setText((String) params.get((params.size()-1)));
        label.setText((String) params.get(params.size()-2));
    }
}