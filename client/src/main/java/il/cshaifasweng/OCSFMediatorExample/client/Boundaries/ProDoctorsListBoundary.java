package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ProDoctorsList;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.ImageView;

public class ProDoctorsListBoundary extends Boundary{
    int index =params.size()-1;
    private ProDoctorsList shobdek= (ProDoctorsList) params.get(index);

    @FXML
    private Button LogoutBTR;

    @FXML
    private ImageView label;

    @FXML
    private ListView<String> Proslist;


    @FXML
    void submitAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        System.out.println("I am in the init of ProDoctors");
        LinkedList<Doctor> DoctorsToShow = shobdek.getProDoctors();
        System.out.println(DoctorsToShow.size());
        for (int i = 0; i < DoctorsToShow.size(); i++) {
            Proslist.getItems().add("Dr." +DoctorsToShow.get(i).getLastName() + DoctorsToShow.get(i).getFirstName());
        }
        Proslist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

}
