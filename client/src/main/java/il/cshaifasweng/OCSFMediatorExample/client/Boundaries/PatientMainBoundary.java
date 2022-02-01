package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PatientMainBoundary extends Boundary {

    protected Patient patient= (Patient) user_Ob.get(0);
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Label Plabel;

    @FXML
    protected Button BackButton;

    //Salsaaaaaaaabeeel
    @FXML
    void BackButton(ActionEvent event) throws IOException {
        App.setRoot("patientmain");
    }
    @FXML
    void FamilyaOrPediatrician(ActionEvent event) throws IOException {
        System.out.println("FamilyaOrPediatrician");
        message.add("#AppFamilyChild");
        message.add(patient.getId());
        System.out.println(message.get(0));
        SimpleClient.getClient().sendToServer(message);

    }
    @FXML
    void ViewSchelduledAppointment(ActionEvent event) throws IOException {
        App.setRoot("scheduledappmain");
    }

    @FXML
    void CoronaVaccineAppointment(ActionEvent event) throws IOException {
        message.clear();
        message.add("#GetAllClinicName");
        message.add("CoronaVaccine"); // add flag
        SimpleClient.getClient().sendToServer(message);
    }

    @FXML
    void FluVaccineAppointment(ActionEvent event) throws IOException {
        message.clear();
        message.add("#GetAllClinicName");
        message.add("FluVaccine"); // add flag
        SimpleClient.getClient().sendToServer(message);

    }
    @FXML
    void CoronaTestAppointment(ActionEvent event) throws IOException{
            App.setRoot("questionnaire");
    }
    @FXML
    void IssuingGreenNote(ActionEvent event) throws IOException {
         if(patient.getNumOfVaccine()>= 2 || patient.isRecovery()){
             String Info=
                     "First Name: "+patient.getFirstName()+
                     "\n"+
                     "Last Name: "+patient.getLastName();
             MessageBoundary.displayGeenPass(Info);
         }else {
             String Info="You Don't have Green Pass\n"+
                     "First Name: "+patient.getFirstName()+
                     "\n"+
                     "Last Name: "+patient.getLastName();
             MessageBoundary.displayError(Info);
         }
    }
    //*************************************************************
    //*************************************************************
    //Saaaara nameer remaaan
    @FXML
    void to_sp(ActionEvent event) throws IOException {
        App.setRoot("Specialization_list");
    }

    @FXML
    void initialize() {
        Plabel.setText(patient.getFirstName()+" "+patient.getLastName());
    }

}
