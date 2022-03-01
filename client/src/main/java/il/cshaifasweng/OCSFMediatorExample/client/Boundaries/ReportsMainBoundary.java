package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

/**
 * this page is belong to the clinic manager
 * by this page the clinic manager can choose the report that he want to see
 */

public class ReportsMainBoundary extends MainCmBoundary {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox Optintion1;

    @FXML
    private CheckBox Optintion2;

    @FXML
    private CheckBox Optintion3;

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("maincm");
    }

    @FXML
    void Optintion1(ActionEvent event) {
        if(Optintion1.isSelected()){
            Optintion3.setSelected(false);
            Optintion2.setSelected(false);
        }
    }

    @FXML
    void Optintion2(ActionEvent event) {
        if(Optintion2.isSelected()){
            Optintion3.setSelected(false);
            Optintion1.setSelected(false);
        }
    }

    @FXML
    void Optintion3(ActionEvent event) {
        if(Optintion3.isSelected()){
            Optintion1.setSelected(false);
            Optintion2.setSelected(false);
        }
    }

    @FXML
    void ShowReport(ActionEvent event) throws IOException {
        if(!(Optintion1.isSelected()) &&!(Optintion2.isSelected()) &&!(Optintion3.isSelected() ))
            MessageBoundary.displayError("Please chose a report to show!");
        else
        {
            if (Optintion1.isSelected()) {
                params.add("WeaklyDoneApps");
                App.setRoot("WeeklyReport");
            } else if(Optintion2.isSelected())
            {
                params.add("WeaklyNotDoneApps");
                App.setRoot("WeeklyReport");
            }
            else
                App.setRoot("DailyReport");
        }
        // (report type,clinic id,Manager type )
    }

    @FXML
    void initialize() {
        assert Optintion1 != null : "fx:id=\"Optintion1\" was not injected: check your FXML file 'ReportsMain.fxml'.";
        assert Optintion2 != null : "fx:id=\"Optintion2\" was not injected: check your FXML file 'ReportsMain.fxml'.";
        assert Optintion3 != null : "fx:id=\"Optintion3\" was not injected: check your FXML file 'ReportsMain.fxml'.";

    }

}
