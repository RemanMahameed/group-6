package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ClinicName;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;

public class DoctorMainBoundary extends Boundary {
//    Doctor doctor;
//
//    public Doctor getDoctor() {
//        return doctor;
//    }
//
//    public void setDoctor(Doctor doctor) {
//        this.doctor = doctor;
//    }
    Doctor doctor= (Doctor) user_Ob.get(0);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Dlabel;

    @FXML
    private ImageView label;

    @FXML
    private ListView<String> ClinicName;

    @FXML
    void submit(ActionEvent event) {
        if(ClinicName.getSelectionModel().getSelectedItems().isEmpty()){
            MessageBoundary.displayError("please choose a clinic");
        }
        else {
            if (!ClinicName.getSelectionModel().getSelectedItems().equals(null)) {
                LinkedList<String> choice = new LinkedList<String>();
                choice.add("#DocClinicName" );
                //choice.add(String.valueOf(doctor.getId()));
                choice.add( ClinicName.getSelectionModel().getSelectedItem());
                System.out.println("the selected clinic is: " + ClinicName.getSelectionModel().getSelectedItem());
                try {
                    SimpleClient.getClient().sendToServer(choice);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }





    @FXML
    void initialize() {

        System.out.println(" After Doctor doctor:"+ doctor.getFirstName());
        String WelcomwD= "Welcome " + doctor.getUserName();
        Dlabel.setText(WelcomwD);
        List<Clinic> clinicList = doctor.getClinicList();
        for(Clinic element : clinicList)
        {
            ClinicName.getItems().add(element.getClinicType());
        }
        ClinicName.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }
}

