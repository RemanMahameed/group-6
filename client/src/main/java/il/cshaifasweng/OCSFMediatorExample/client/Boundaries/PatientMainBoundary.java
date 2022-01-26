package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.net.URL;
import java.util.ResourceBundle;

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
    void FamilyaOrPediatrician(ActionEvent event) {
        message.add("#AppFamilyChild");
        message.add(params);

    }

    @FXML
    void initialize() {
        Plabel.setText(patient.getUserName());
    }

}
