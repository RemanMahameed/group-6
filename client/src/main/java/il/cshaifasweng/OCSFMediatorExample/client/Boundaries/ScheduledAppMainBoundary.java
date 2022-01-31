package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ScheduledAppMainBoundary extends PatientMainBoundary{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void ViewDoctorApp(ActionEvent event) throws IOException {
        System.out.println("ViewDoctorApp");
        message.add("#ViewAppointment");
        message.add("Doctor"); //add type of app
        message.add(patient.getId());
        System.out.println(message.get(0));
        SimpleClient.getClient().sendToServer(message);
    }

    @FXML
    void ViewLaboratoryFactsApp(ActionEvent event) throws IOException{
        System.out.println("ViewDoctorApp");
        message.add("#ViewAppointment");
        message.add("LaboratoryFacts"); //add type of app
        message.add(patient.getId());
        System.out.println(message.get(0));
        SimpleClient.getClient().sendToServer(message);
    }

    @FXML
    void ViewNurseApp(ActionEvent event) throws IOException {
        System.out.println("ViewDoctorApp");
        message.add("#ViewAppointment");
        message.add("Nurse"); //add type of app
        message.add(patient.getId());
        System.out.println(message.get(0));
        SimpleClient.getClient().sendToServer(message);
    }

    @FXML
    void ViewCoronaTestApp(ActionEvent event) throws IOException {
        message.add("#ViewAppointment");
        message.add("CoronaTest"); //add type of app
        message.add(patient.getId());
        System.out.println(message.get(0));
        SimpleClient.getClient().sendToServer(message);
    }

    @FXML
    void ViewVaccineApp(ActionEvent event) throws IOException {
        message.add("#ViewAppointment");
        message.add("Vaccine"); //add type of app
        message.add(patient.getId());
        System.out.println(message.get(0));
        SimpleClient.getClient().sendToServer(message);
    }

    @FXML
    void initialize() {

    }

}
