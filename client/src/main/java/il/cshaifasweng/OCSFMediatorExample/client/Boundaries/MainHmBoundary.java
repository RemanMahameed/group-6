package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ClinicName;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.ClinicManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.HmoManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class MainHmBoundary extends Boundary {

    HmoManager hmoManager=(HmoManager) user_Ob.get(0);
    List<Clinic> clinicsList=hmoManager.getHmo().getClinics();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> ListView;

    @FXML
    private CheckBox Optintion1;

    @FXML
    private CheckBox Optintion2;

    @FXML
    private CheckBox Optintion3;

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
        if(Optintion1.isSelected() || Optintion2.isSelected())
            App.setRoot("Report2");

    }


    @FXML
    void initialize() {
        assert ListView != null : "fx:id=\"ListView\" was not injected: check your FXML file 'MainHm.fxml'.";
        assert Optintion1 != null : "fx:id=\"Optintion1\" was not injected: check your FXML file 'MainHm.fxml'.";
        assert Optintion2 != null : "fx:id=\"Optintion2\" was not injected: check your FXML file 'MainHm.fxml'.";
        assert Optintion3 != null : "fx:id=\"Optintion3\" was not injected: check your FXML file 'MainHm.fxml'.";

        List<String> clinicsName=new LinkedList<>();
        for(Clinic clinic:clinicsList){
            clinicsName.add(clinic.getClinicType());
            System.out.println("ClinicName is:"+clinic.getClinicType());
        }

        for (int i = 0; i < clinicsName.size(); i++) {
            ListView.getItems().add(clinicsName.get(i));
        }
        ListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}
