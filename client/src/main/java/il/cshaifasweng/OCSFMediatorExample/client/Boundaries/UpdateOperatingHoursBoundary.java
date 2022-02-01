package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

import java.io.IOException;

public class UpdateOperatingHoursBoundary extends Boundary {
    ObservableList<String> Choices= FXCollections.observableArrayList("Clinic","Clinic's Doctor","Corona Test","Vaccine");

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
        App.setRoot("WorkingHours");


    }
    @FXML
    void initialize() {
        ChoiceBox.setValue("Clinic");
        ChoiceBox.setItems(Choices);
    }
}
