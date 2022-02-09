package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.ClinicManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainCmBoundary extends Boundary{
    ClinicManager clinicManager=(ClinicManager) user_Ob.get(0);
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void CloseSystem(ActionEvent event) {

    }

    @FXML
    void OpenSystem(ActionEvent event) throws IOException {
        message.clear();
        message.add("#SendReminderEmail");
        message.add(clinicManager.getClinic().getId()); //send the clinic id
        SimpleClient.getClient().sendToServer(message);
        MessageBoundary.displayInfo("Sent Reminder Email");

    }
    @FXML
    void UpdateClinicWorkingHours(ActionEvent event) throws IOException {
        message.clear();
        message.add("#getWorkingHours");
        message.add("WorkingHours");
        message.add(clinicManager.getClinic().getClinicType()); // add the clinic name
        message.add("Clinic"); //add what we want to change.
        SimpleClient.getClient().sendToServer(message);
        MessageBoundary.displayInfo("You working at:\n"+clinicManager.getClinic().getClinicType());
    }

    @FXML
    void UpdateCoronaTestWorkingHours(ActionEvent event) throws IOException {
        message.clear();
        message.add("#getWorkingHours");
        message.add("WorkingHours");
        message.add(clinicManager.getClinic().getClinicType()); // add the clinic name
        message.add("Corona Test"); //add what we want to change.
        SimpleClient.getClient().sendToServer(message);
        MessageBoundary.displayInfo("You working at:\n"+clinicManager.getClinic().getClinicType());
    }

    @FXML
    void UpdateDoctorWorkingHours(ActionEvent event) throws IOException {
        message.clear();
        message.add("#geClinicDoctor");
        message.add("WorkingHours");
        message.add(clinicManager.getClinic().getClinicType()); // add the clinic name
        message.add("Clinic's Doctor"); //add what we want to change.
        SimpleClient.getClient().sendToServer(message);
        MessageBoundary.displayInfo("You working at:\n"+clinicManager.getClinic().getClinicType());
    }

    @FXML
    void UpdateVaccineWorkingHours(ActionEvent event) throws IOException {
        message.clear();
        message.add("#getWorkingHours");
        message.add("WorkingHours");
        message.add(clinicManager.getClinic().getClinicType()); // add the clinic name
        message.add("Vaccine"); //add what we want to change.
        SimpleClient.getClient().sendToServer(message);
        MessageBoundary.displayInfo("You working at:\n"+clinicManager.getClinic().getClinicType());
    }


    @FXML
    void UpdateOperatingHours(ActionEvent event) throws IOException {
        message.clear();
        message.add("#GetAllClinicName");
        message.add("UpdateOperatingHours"); // add the flag (Why we need Clinic name)
        SimpleClient.getClient().sendToServer(message);
        //App.setRoot("UpdateOperatingHours");
    }

    @FXML
    void ViewReports(ActionEvent event) throws IOException {
        App.setRoot("ReportsMain");
    }

    @FXML
    void initialize() {

    }

}
