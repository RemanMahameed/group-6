package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.SplittableRandom;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.DoctorNames;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class DoctorWorkingHoursBoundary extends Boundary{
    DoctorNames doctorNames=(DoctorNames) params.get(0);
    List<String> doctorsNameList=doctorNames.getDoctorsFirstName();
    List<Integer> doctorsIdList=doctorNames.getDoctorsId();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> ListView;

    @FXML
    void Back(ActionEvent event) throws IOException {
        message.clear();
        message.add("#GetAllClinicName");
        message.add("UpdateOperatingHours"); // add the flag (Why we need Clinic name)
        SimpleClient.getClient().sendToServer(message);
    }


    @FXML
    void Next(ActionEvent event) throws IOException {
        int index=ListView.getSelectionModel().getSelectedIndex();
        message.add("#getWorkingHours");
        message.add(doctorsIdList.get(index).toString()); // add the doctor Id
        message.add(doctorNames.getClinicName());          //add clinicName
        message.add("Clinic's Doctor"); //add what we want to change.
        SimpleClient.getClient().sendToServer(message);
    }

    @FXML
    void initialize() {
        assert ListView != null : "fx:id=\"ListView\" was not injected: check your FXML file 'DoctorWorkingHours.fxml'.";
        for (int i = 0; i < doctorsNameList.size(); i++) {
            ListView.getItems().add(doctorsNameList.get(i));
        }
        ListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}
