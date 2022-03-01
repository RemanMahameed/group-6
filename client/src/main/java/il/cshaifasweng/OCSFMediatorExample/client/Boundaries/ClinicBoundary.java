package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ClinicName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
 * in this boundary the patient choose the clinic that he want to book an appointment in it
 */

public class ClinicBoundary extends PatientMainBoundary{
    int index= params.size()-1;
    ClinicName clinicName= (ClinicName) params.get(index);
    List<String> clinicsName= clinicName.getClinicsName();
    List<Integer> clinicsId= clinicName.getClinicsId();
    String type=clinicName.getFlag();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> ListView;


    @FXML
    void ShowFreeAPP(ActionEvent event) throws IOException {
        if(ListView.getSelectionModel().isEmpty()){
            MessageBoundary.displayError("You Don't chose Any Clinic\n"+"Please choose one. ");
        }else {
            int index=ListView.getSelectionModel().getSelectedIndex();
            message.clear();
            message.add("#GetFreeApp");
            message.add(clinicsId.get(index)); // send id of selected clinic
            message.add(type); //add the type of Appointment
            message.add(patient.getId());
            SimpleClient.getClient().sendToServer(message);
        }

    }

    @FXML
    void initialize() {
        assert ListView != null : "fx:id=\"ListView\" was not injected: check your FXML file 'clinic.fxml'.";
        for (int i = 0; i < clinicsName.size(); i++) {
            ListView.getItems().add(clinicsName.get(i));
        }
        ListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}
