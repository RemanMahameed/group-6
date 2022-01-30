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

public class CoronaAndVaccineAppBoundary extends Boundary{
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
        System.out.println(AppType+"   aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaas");
        if(AppType.equals("Vaccine"))
            message.add("Vaccine"); // add flag
        else
            message.add("CoronaTest");
        SimpleClient.getClient().sendToServer(message);
    }

    @FXML
    void SaveAppointment(ActionEvent event) {

    }

    @FXML
    void initialize() {

        for (int i = 0; i < AppString.size(); i++) {
            ListView.getItems().add(AppString.get(i));
        }
        ListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}
