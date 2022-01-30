package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.DoctorApp;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class AppointmentDoctorBoundary extends PatientMainBoundary{
    //protected Patient patient= (Patient) user_Ob.get(0);
    private int paramsSize= params.size()-1;
    private DoctorApp doctorApp=(DoctorApp) params.get(paramsSize);
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> ListView;

//    @FXML
//    void BackButton(ActionEvent event) throws IOException {
//        App.setRoot("patientmain");
//    }

    @FXML
    void NextButton(ActionEvent event) throws IOException {
        int index=ListView.getSelectionModel().getSelectedIndex();
        System.out.println(doctorApp.getDoctorAppString().get(index));
        message.add("#SetSelectedAppointement");
        message.add(patient); //add patient
        message.add(doctorApp.getDoctor()); //add the selected doctor
        message.add(doctorApp.getDoctorAppointments().get(index)); // add the selected appointment
        SimpleClient.getClient().sendToServer(message);
        String AppCon="You have select  DoctorAppointment at: "+ doctorApp.getDoctorAppointments().get(index).getDate()+"\n"
                +"with doctor: "+doctorApp.getDoctor().getFirstName()+" "+doctorApp.getDoctor().getLastName()+" ("+doctorApp.getDoctor().getRole() +")"+"\n"
                +"at clinic: "+doctorApp.getPatient().getClinic().getClinicType();
        System.out.println("AppCon is: "+AppCon);
        params.add(AppCon);
        params.add("Appointment Configuration");
        App.setRoot("Information");
    }

    @FXML
    void initialize() {
        assert ListView != null : "fx:id=\"ListView\" was not injected: check your FXML file 'appointmentdoctor.fxml'.";
        List<String> stringApp= doctorApp.getDoctorAppString();
        for (int i = 0; i < stringApp.size(); i++) {
            ListView.getItems().add(stringApp.get(i));
        }
        ListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}

