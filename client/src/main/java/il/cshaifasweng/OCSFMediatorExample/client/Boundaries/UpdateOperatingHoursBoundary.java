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

public class UpdateOperatingHoursBoundary extends MainCmBoundary {
    ObservableList<String> Choices= FXCollections.observableArrayList("Clinic","Clinic's Doctor","Corona Test","Vaccine");
//    int index= params.size()-1;
//    ClinicName clinicName= (ClinicName) params.get(index);
//    List<String> clinicsName= clinicName.getClinicsName();
//    List<Integer> clinicsId= clinicName.getClinicsId();
//    String type=clinicName.getFlag();
    @FXML
    private ChoiceBox<String> ChoiceBox;

    @FXML
    private ListView<String> ListView;

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("MainCm");
    }


    @FXML
    void Next(ActionEvent event) throws IOException {
        message.clear();
        if(ChoiceBox.getValue().equals("Clinic's Doctor")){
            message.add("#geClinicDoctor");

        }else {
            message.add("#getWorkingHours");
        }
        message.add("WorkingHours");
        message.add(clinicManager.getClinic().getClinicType()); // add the clinic name
        message.add(ChoiceBox.getValue()); //add what we want to change.
        SimpleClient.getClient().sendToServer(message);
        MessageBoundary.displayInfo("You working at:\n"+clinicManager.getClinic().getClinicType());
    }
    @FXML
    void initialize() {
        ChoiceBox.setValue("Clinic");
        ChoiceBox.setItems(Choices);
    }
}
