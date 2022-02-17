package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.io.IOException;

public class QueueManagementBoundary extends Boundary {
    @FXML
    private ListView<String> ClinicList;

    @FXML
    void BackAction(ActionEvent event) {
        try {
            App.setRoot("primary");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void SubmitAction(ActionEvent event) {
        params.clear();
        if (ClinicList.getSelectionModel().getSelectedItems().isEmpty()) {
            MessageBoundary.displayError("please choose a clinic");
        } else {
            if (!ClinicList.getSelectionModel().getSelectedItems().equals(null)) {
                String choice = ClinicList.getSelectionModel().getSelectedItem();
                System.out.println(choice);
                params.add(choice);
                try {
                    App.setRoot("CardVer");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    }
    @FXML
    void initialize(){
        ObservableList<String> items = FXCollections.observableArrayList (
                "General clinic","Professional clinic" , "General && Professional clinic");

        ClinicList.setItems(items);
        ClinicList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}
