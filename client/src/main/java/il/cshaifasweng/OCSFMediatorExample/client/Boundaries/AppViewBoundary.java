package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.DoctorApp;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.DoctorAppointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AppViewBoundary extends Boundary{
    Object user =  user_Ob.get(0);
    int index =params.size()-1;
    Clinic clinic = (Clinic) params.get(index);
    @FXML
    private ListView<String> AppList;

    @FXML
    private Label ClinicLabel;

    @FXML
    void backAction(ActionEvent event) {
        if (user.getClass().equals(Doctor.class)) {
            try {
                App.setRoot("DoctorActions");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void callAction(ActionEvent event) {

    }


    @FXML
    void initialize() {
        String belong = "These Actions is belong to " + clinic.getClinicType();
        ClinicLabel.setText(belong);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        if (user.getClass().equals(Doctor.class)) {
            System.out.println("I am a doctor");
            Doctor doctor = (Doctor) user_Ob.get(0);
            List<DoctorAppointment> appointments = doctor.getAppointments();
            for (DoctorAppointment element : appointments)
                if(!element.isDone()) {
                    AppList.getItems().add("Patient name is : " + element.getPatient().getFirstName() + " " + element.getPatient().getLastName() + "\n"
                            + "Appointment Date is : " + dtf.format(element.getDate()) + "\n"
                    );
                }
        }
    }
}
