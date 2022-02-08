package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WeeklyReportBoundary extends Boundary{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void Back(ActionEvent event) throws IOException {
        if(params.get((params.size()-1)).equals("HmoManager"))
            App.setRoot("MainHm");
        else
            App.setRoot("ReportsMain");
    }


    @FXML
    void initialize() {

    }

}
