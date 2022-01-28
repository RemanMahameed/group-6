package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.util.LinkedList;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;

public class SpecializationBoundary extends Boundary{


    @FXML
    private Button LogoutBTR;

    @FXML
    private ImageView label;

    @FXML
    private ListView<String> splist;


    @FXML
    void submitAction(ActionEvent event) {
        if(splist.getSelectionModel().getSelectedItems().isEmpty()){
            MessageBoundary.displayError("please choose a specialization");
        }
        else{
            if(!splist.getSelectionModel().getSelectedItems().equals(null))
            {
                LinkedList<String> choice = new LinkedList<String>();
                choice.add("#specilizationChoice " );
                choice.add( splist.getSelectionModel().getSelectedItem());
                String id = String.valueOf(((Patient) params.get(0)).getId());
                //System.out.println(id);
                //String id = "2";
                //System.out.println(id);
                choice.add(id); // adding the id of the patient
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
        ObservableList<String> items = FXCollections.observableArrayList (
                "Otolaryngologies","Gynecologist");

        splist.setItems(items);
        splist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

}