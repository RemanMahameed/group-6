package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Nurse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class NurseMainBoundary extends Boundary {
    Nurse nurse = (Nurse) user_Ob.get(0);

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Dlabel;

    @FXML
    private ImageView label;

    @FXML
    void AppListAction(ActionEvent event) {
        try {
            App.setRoot("AppView");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void patientsListAction(ActionEvent event) {
        List<String> orderAllApps = new LinkedList<>();
        orderAllApps.add("#oderAllNurseApps");
        try {
            SimpleClient.getClient().sendToServer(orderAllApps);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
    @FXML
    void initialize() {
        System.out.println(" After Nurse nurse:"+ nurse.getFirstName());
        Dlabel.setText(nurse.getUserName());

    }
}
