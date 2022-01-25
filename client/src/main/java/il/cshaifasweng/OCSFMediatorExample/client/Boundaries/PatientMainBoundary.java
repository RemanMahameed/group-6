package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PatientMainBoundary extends Boundary {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Label Plabel;


    @FXML
    void FamilyaOrPediatrician(ActionEvent event) {

    }

    @FXML
    void initialize() {
        System.out.println(" Before !!!");
        Patient patient= (Patient) params.get(0);
        System.out.println(" After Doctor doctor:"+ patient.getFirstName());
        Plabel.setText(patient.getUserName());
    }

}
