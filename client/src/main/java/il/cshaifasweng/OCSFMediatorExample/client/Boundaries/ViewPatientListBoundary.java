package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ViewPatientListBoundary extends Boundary {


    Object user = user_Ob.get(0);
    int index =params.size()-1;
    Clinic clinic = (Clinic) params.get(index);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label ClinicLabel;


    @FXML
    private ListView<String> PatientList;


    @FXML
    void initialize() {
        String belong = "These Actions is belong to " + clinic.getClinicType();
        ClinicLabel.setText(belong);
        if (user.getClass().equals(Doctor.class))
        {
            System.out.println("I am a doctor");
            Doctor doctor = (Doctor) user_Ob.get(0);
            List<Patient> patients = doctor.getPatients();
            for(Patient element : patients)
              PatientList.getItems().add(element.getFirstName() + " " + element.getLastName());
        }

    }

}
