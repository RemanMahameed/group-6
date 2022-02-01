package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ClinicName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.IOException;
import java.util.List;

public class UpdateOperatingHoursBoundary extends Boundary {
    ObservableList<String> Choices= FXCollections.observableArrayList("Clinic","Clinic's Doctor","Corona Test","Vaccine");
    int index= params.size()-1;
    ClinicName clinicName= (ClinicName) params.get(index);
    List<String> clinicsName= clinicName.getClinicsName();
    List<Integer> clinicsId= clinicName.getClinicsId();
    String type=clinicName.getFlag();
    @FXML
    private ChoiceBox<String> ChoiceBox;

    @FXML
    private ListView<String> ListView;

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("mainhm");
    }


    @FXML
    void Next(ActionEvent event) throws IOException {
        message.clear();
        if(ChoiceBox.getValue().equals("Clinic's Doctor")){
            message.add("#geClinicDoctor");
            message.add("WorkingHours");
        }else {
            message.add("#getWorkingHours");
        }
        index=ListView.getSelectionModel().getSelectedIndex();
        message.add(clinicsName.get(index)); // add the clinic name
        message.add(ChoiceBox.getValue()); //add what we want to change.
        SimpleClient.getClient().sendToServer(message);
    }
    @FXML
    void initialize() {
        ChoiceBox.setValue("Clinic");
        ChoiceBox.setItems(Choices);
        for (int i = 0; i < clinicsName.size(); i++) {
            ListView.getItems().add(clinicsName.get(i));
        }
        ListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
