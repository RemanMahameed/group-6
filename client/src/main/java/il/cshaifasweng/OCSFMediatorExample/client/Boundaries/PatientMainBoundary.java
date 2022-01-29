package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PatientMainBoundary extends Boundary {

    protected Patient patient= (Patient) user_Ob.get(0);
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Label Plabel;

    @FXML
    protected Button BackButton;

    //Salsaaaaaaaabeeel
    @FXML
    void BackButton(ActionEvent event) throws IOException {
        App.setRoot("patientmain");
    }
    @FXML
    void FamilyaOrPediatrician(ActionEvent event) throws IOException {
        System.out.println("FamilyaOrPediatrician");
        message.add("#AppFamilyChild");
        message.add(patient);
        System.out.println(message.get(0));
        SimpleClient.getClient().sendToServer(message);

    }
    @FXML
    void ViewSchelduledAppointment(ActionEvent event) throws IOException {
        App.setRoot("scheduledappmain");
    }
    //Saaaara nameer remaaan
    @FXML
    void to_sp(ActionEvent event) throws IOException {
        App.setRoot("Specialization_list");
    }

    @FXML
    void initialize() {
        Plabel.setText(patient.getUserName());
    }

}
