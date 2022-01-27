package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PatientMainBoundary extends Boundary {

    protected Patient patient= (Patient) params.get(0);
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Label Plabel;


    @FXML
    void FamilyaOrPediatrician(ActionEvent event) throws IOException {
        System.out.println("FamilyaOrPediatrician");
        message.add("#AppFamilyChild");
        message.add(patient);
        System.out.println(message.get(0));
        SimpleClient.getClient().sendToServer(message);

    }

    @FXML
    void initialize() {
        Plabel.setText(patient.getUserName());
    }

}
