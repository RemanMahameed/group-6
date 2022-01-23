package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DoctorMainSystem {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Dlabel;

    @FXML
    private ImageView label;

    @FXML
    void LogOut(ActionEvent event) {


    }

    @FXML
    void initialize() {
        assert Dlabel != null : "fx:id=\"Dlabel\" was not injected: check your FXML file 'doctormain.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'doctormain.fxml'.";

    }
}

