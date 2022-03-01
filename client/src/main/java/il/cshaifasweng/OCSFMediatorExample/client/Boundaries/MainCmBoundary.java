package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.ClinicManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * this is the page that will open for a clinic manager user
 * by this pase the clinic manager can do her action like view report update working hour...
 */

public class MainCmBoundary extends Boundary{
    ClinicManager clinicManager=(ClinicManager) user_Ob.get(0);
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    void CloseSystem(ActionEvent event) throws IOException {
        message.clear();
        message.add("#CloseSystem");
        message.add(clinicManager.getClinic().getId()); //send the clinic id
        SimpleClient.getClient().sendToServer(message);
        MessageBoundary.displayInfo("Clinic is Closed");

    }

    @FXML
    void OpenSystem(ActionEvent event) throws IOException {
        message.clear();
        message.add("#SendReminderEmail");
        message.add(clinicManager.getClinic().getId()); //send the clinic id
        SimpleClient.getClient().sendToServer(message);
        MessageBoundary.displayInfo("Open clinic and sent Reminder Email");

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
        SimpleClient.getParams().add(clinicManager.getClinic());
        App.setRoot("ReportsMain");
    }

    @FXML
    void initialize() {

    }

}
