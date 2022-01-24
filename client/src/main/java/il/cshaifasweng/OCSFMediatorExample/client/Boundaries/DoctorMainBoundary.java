package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DoctorMainBoundary {
    Doctor doctor;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Dlabel;

    @FXML
    private ImageView label;

    @FXML
    void LogOut(ActionEvent event) throws IOException{
        System.out.println("buttom log out");
        Doctor doctor= (Doctor) SimpleClient.getParams().get(0);
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#Logout");
        message.add(doctor);
        System.out.println("sending Logout from client to server");
        SimpleClient.getClient().sendToServer(message);
    }

    @FXML
    void initialize() {
        Doctor doctor= (Doctor) SimpleClient.getParams().get(0);
        Dlabel.setText(doctor.getUserName());
    }
}

