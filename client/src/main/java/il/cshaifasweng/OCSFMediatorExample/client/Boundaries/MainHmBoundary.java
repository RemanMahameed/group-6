package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
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

    HmoManager hmoManager = (HmoManager) user_Ob.get(0);
    List<Clinic> clinicsList = hmoManager.getHmo().getClinics();

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
        if (Optintion1.isSelected()) {
            Optintion3.setSelected(false);
            Optintion2.setSelected(false);
        }
    }

    @FXML
    void Optintion2(ActionEvent event) {
        if (Optintion2.isSelected()) {
            Optintion3.setSelected(false);
            Optintion1.setSelected(false);
        }
    }

    @FXML
    void Optintion3(ActionEvent event) {
        if (Optintion3.isSelected()) {
            Optintion1.setSelected(false);
            Optintion2.setSelected(false);
        }
    }


    @FXML
    void ShowReport(ActionEvent event) throws IOException {
        if ((!(Optintion1.isSelected()) && !(Optintion2.isSelected()) && !(Optintion3.isSelected())) || (ListView.getSelectionModel().isEmpty()))
            MessageBoundary.displayError("Please choose report to show and clinic!");
        else {

            String clinic_name = ListView.getSelectionModel().getSelectedItem();
//            int end=clinic_name.length()-1;
//            clinic_name= clinic_name.substring(1,end);
            System.out.println(clinic_name);
            LinkedList<String> choice = new LinkedList<String>();
            choice.add("#HMClinicName");
            choice.add(clinic_name);
            System.out.println("the selected clinic is: " + clinic_name);
            if (Optintion1.isSelected()) {
                choice.add("WeaklyDoneApps");
            } else {
                if (Optintion2.isSelected()) {
                    choice.add("WeaklyNOTDoneApps");
                } else {
                    if (Optintion3.isSelected()) {
                        choice.add("Daily");
                    }
                }
            }

            try {
                SimpleClient.getClient().sendToServer(choice);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
//            else
//            {
////                this.params.add(ListView.getSelectionModel().getSelectedItems());
////            if ((Optintion1.isSelected() || Optintion2.isSelected())) {
////                    params.add("HmoManager");
////                    App.setRoot("WeeklyReport");
////                } else
////                    App.setRoot("DailyReport");
//            }

        // (report type,clinic id,Manager type )
    }


    @FXML
    void initialize() {
        assert ListView != null : "fx:id=\"ListView\" was not injected: check your FXML file 'MainHm.fxml'.";
        assert Optintion1 != null : "fx:id=\"Optintion1\" was not injected: check your FXML file 'MainHm.fxml'.";
        assert Optintion2 != null : "fx:id=\"Optintion2\" was not injected: check your FXML file 'MainHm.fxml'.";
        assert Optintion3 != null : "fx:id=\"Optintion3\" was not injected: check your FXML file 'MainHm.fxml'.";

        List<String> clinicsName = new LinkedList<>();
        for (Clinic clinic : clinicsList) {
            clinicsName.add(clinic.getClinicType());
            System.out.println("ClinicName is:" + clinic.getClinicType());
        }

        for (int i = 0; i < clinicsName.size(); i++) {
            ListView.getItems().add(clinicsName.get(i));
        }
        ListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }
}