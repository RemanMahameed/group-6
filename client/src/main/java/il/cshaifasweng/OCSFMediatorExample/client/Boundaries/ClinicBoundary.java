package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ClinicName;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ClinicBoundary extends PatientMainBoundary{
    int index= params.size()-1;
    ClinicName clinicName= (ClinicName) params.get(index);
    List<String> clinicsName= clinicName.getClinicsName();
    List<Integer> clinicsId= clinicName.getClinicsId();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> ListView;


    @FXML
    void ShowFreeApp(ActionEvent event) {
        int index=ListView.getSelectionModel().getSelectedIndex();
        message.clear();
        message.add("GetFeeVaccineApp");
        message.add(clinicsId.get(index)); // send id of selected clinic
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
