package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class otherActionsBoundary extends Boundary {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void LabApp(ActionEvent event) {
        LinkedList<String> choice = new LinkedList<String>();
        System.out.println("book Lab App");
        if (LocalTime.now().isAfter(LocalTime.of(7, 59, 59)) && LocalTime.now().isBefore(LocalTime.of(10, 0, 0)))
        {choice.add("#LabApp");
            Patient patient = (Patient) user_Ob.get(0);
            String Pid = String.valueOf(patient.getId());
            choice.add(Pid);  // adding patient Id
            Clinic clinic = (Clinic) params.get(0);
            String Cid = String.valueOf(clinic.getId());
            choice.add(Cid); // adding clinic Id
            try {
                SimpleClient.getClient().sendToServer(choice);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }}
        else
        {
            MessageBoundary.displayError("reception time for Laboratory is from 8:00 to 10:00");
        }

    }
    
    @FXML
    void NurseApp(ActionEvent event) {
        LinkedList<String> choice = new LinkedList<String>();
        System.out.println("book Nurse App");
        choice.add("#NurseApp");
        Patient patient = (Patient) user_Ob.get(0);
        String Pid = String.valueOf(patient.getId());
        choice.add(Pid);  // adding patient Id
        Clinic clinic = (Clinic) params.get(0);
        String Cid = String.valueOf(clinic.getId());
        choice.add(Cid); // adding clinic Id
        try {
            SimpleClient.getClient().sendToServer(choice);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {

    }

}
