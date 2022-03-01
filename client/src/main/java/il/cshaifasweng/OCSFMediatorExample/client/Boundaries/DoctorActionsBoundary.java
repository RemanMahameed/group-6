package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * this page is open when the user is doctor
 * by this page the doctor can do his action like viewing the appointments and the patients
 */

public class DoctorActionsBoundary extends Boundary{
    Doctor doctor= (Doctor) user_Ob.get(0);
    int index =params.size()-1;
    Clinic  clinic = (Clinic) params.get(index);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Dlabel;

    @FXML
    private Label clinicLable;



    @FXML
    void AppListAction(ActionEvent event) {
        try {
            App.setRoot("AppView");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void backAction(ActionEvent event) {
        try {
            params.remove(index);
            App.setRoot("DoctorMain");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void patientsListAction(ActionEvent event) {
        try {
            App.setRoot("ViewPatientList");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void initialize() {
        System.out.println("Doctor Actions: "+ doctor.getFirstName());
        String WelcomwD= "Welcome " + doctor.getUserName();
        Dlabel.setText(WelcomwD);
        String belong = "These Actions is belong to " + clinic.getClinicType();
        clinicLable.setText(belong);


    }

}
