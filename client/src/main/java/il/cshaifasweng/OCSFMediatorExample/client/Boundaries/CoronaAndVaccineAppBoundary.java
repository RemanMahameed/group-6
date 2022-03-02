package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.FreeAppointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

/**
 * this boundary shows the free appointments for Corona or vaccine
 * the patient can choose the wanted appointment
 */

public class CoronaAndVaccineAppBoundary extends PatientMainBoundary{
    FreeAppointment freeAppointment=(FreeAppointment) params.get(0);
    List<String> AppString=freeAppointment.getDetailsApp();
    String AppType=freeAppointment.getType();
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
        if(AppType.equals("Vaccine"))
            message.add("Vaccine"); // add flag
        else
            message.add("CoronaTest");
        SimpleClient.getClient().sendToServer(message);
    }

    @FXML
    void SaveAppointment(ActionEvent event) throws IOException {
        int index = ListView.getSelectionModel().getSelectedIndex();
        message.clear();
        if(ListView.getSelectionModel().getSelectedItems().isEmpty()){
            MessageBoundary.displayError("You have to choose a date");
        }
        else{
        if (AppType.equals("CoronaTest")) {
            message.add("#setSelectedCoronaTestApp");
            message.add(freeAppointment.getCoronaTestAppointments().get(index)); //add the selected App
        } else {
            message.add("#SetSelectedVaccineApp");
            message.add(freeAppointment.getVaccineAppointments().get(index)); //add the selected App
        }
        message.add(freeAppointment.getClinicId());
        message.add(patient.getId());
        SimpleClient.getClient().sendToServer(message);
        MessageBoundary.displayInfo("You chose: " + "\n" +
                ListView.getSelectionModel().getSelectedItems());
        App.setRoot("patientmain");
        }
    }

    @FXML
    void initialize() {

        for (int i = 0; i < AppString.size(); i++) {
            ListView.getItems().add(AppString.get(i));
        }
        ListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}
