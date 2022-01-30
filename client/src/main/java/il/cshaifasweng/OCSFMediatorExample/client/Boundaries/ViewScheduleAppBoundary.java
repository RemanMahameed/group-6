package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ScheduledApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class ViewScheduleAppBoundary extends PatientMainBoundary {
    int index=params.size()-1;
    private ScheduledApp scheduledApp=(ScheduledApp) params.get(index);
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> ViewList;

    @FXML
    private Label title;

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("scheduledappmain");
    }

    @FXML
    void CancelAppointment(ActionEvent event) throws IOException {
        System.out.println(ViewList.getSelectionModel().getSelectedItems().get(0));
        if(!ViewList.getSelectionModel().getSelectedItems().get(0).equals("There is not Scheduled Appointment!")) {
            int index=ViewList.getSelectionModel().getSelectedIndex();
            message.clear();
            message.add("#CancelAppointment");
            switch (scheduledApp.getTypeFlag()) {
                case ("Doctor"):
                    message.add(scheduledApp.getDoctorAppointments().get(index));
                    break;
                case ("Nurse"):
                    message.add(scheduledApp.getNurseAppointments().get(index));
                    break;
                case ("LaboratoryFacts"):
                    message.add(scheduledApp.getLaboratoryFactsAppointments().get(index));
                    break;
            }
            SimpleClient.getClient().sendToServer(message);
            String AppCon="The  Appointment you chose is Canceled\n"+"( "+scheduledApp.getAppString()+" )";
            System.out.println("AppCon is: "+AppCon);
            params.add(AppCon);
            params.add("Canceld Appointment");
            App.setRoot("Information");
        }else {
            MessageBoundary.displayWarning("There is not Scheduled Appointment!");
        }

    }

    @FXML
    void initialize() {
        assert ViewList != null : "fx:id=\"ViewList\" was not injected: check your FXML file 'viewscheduledApp.fxml'.";
        title.setText("Scheduled " +scheduledApp.getTypeFlag()+" Appointment: ");
        List<String> stringApp=scheduledApp.getAppString();
        for (int i = 0; i < stringApp.size(); i++) {
            ViewList.getItems().add(stringApp.get(i));
        }
        ViewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

}