package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.WorkingHours;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import org.hibernate.type.LocalDateTimeType;

import javax.persistence.criteria.CriteriaBuilder;

public class WorkingHoursBoundary extends Boundary{
    ObservableList<Integer> Hours= FXCollections.observableArrayList();
    ObservableList<Integer> Minute= FXCollections.observableArrayList();
    WorkingHours workingHours=(WorkingHours) params.get(0);
    LocalTime[][] activityWorking=workingHours.getActivityTime();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<Integer> FinishHours1;

    @FXML
    private ChoiceBox<Integer> FinishHours2;

    @FXML
    private ChoiceBox<Integer> FinishHours3;

    @FXML
    private ChoiceBox<Integer> FinishHours4;

    @FXML
    private ChoiceBox<Integer> FinishHours5;

    @FXML
    private ChoiceBox<Integer> FinishHours6;

    @FXML
    private ChoiceBox<Integer> FinishHours7;

    @FXML
    private ChoiceBox<Integer> FinishMinute1;

    @FXML
    private ChoiceBox<Integer> FinishMinute2;

    @FXML
    private ChoiceBox<Integer> FinishMinute3;

    @FXML
    private ChoiceBox<Integer> FinishMinute4;

    @FXML
    private ChoiceBox<Integer> FinishMinute5;

    @FXML
    private ChoiceBox<Integer> FinishMinute6;

    @FXML
    private ChoiceBox<Integer> FinishMinute7;

    @FXML
    private ChoiceBox<Integer> StarHours1;

    @FXML
    private ChoiceBox<Integer> StarHours2;

    @FXML
    private ChoiceBox<Integer> StarHours3;

    @FXML
    private ChoiceBox<Integer> StarHours4;

    @FXML
    private ChoiceBox<Integer> StarHours5;

    @FXML
    private ChoiceBox<Integer> StarHours6;

    @FXML
    private ChoiceBox<Integer> StarHours7;

    @FXML
    private ChoiceBox<Integer> StartMinute1;

    @FXML
    private ChoiceBox<Integer> StartMinute2;

    @FXML
    private ChoiceBox<Integer> StartMinute3;

    @FXML
    private ChoiceBox<Integer> StartMinute4;

    @FXML
    private ChoiceBox<Integer> StartMinute5;

    @FXML
    private ChoiceBox<Integer> StartMinute6;

    @FXML
    private ChoiceBox<Integer> StartMinute7;

    @FXML
    void Back(ActionEvent event) throws IOException {
        message.clear();
        message.add("#GetAllClinicName");
        message.add("UpdateOperatingHours"); // add the flag (Why we need Clinic name)
        SimpleClient.getClient().sendToServer(message);
    }

    @FXML
    void Submit(ActionEvent event) {

    }

    @FXML
    void initialize() {
        for(int i=0;i<=23;i++){
            Hours.add(i);
        }
        for (int i=0;i<=59;i++){
            Minute.add(i);
        }
        //StartWorking (Hours)
        StarHours1.setValue(activityWorking[0][0].getHour());
        StarHours2.setValue(activityWorking[0][1].getHour());
        StarHours3.setValue(activityWorking[0][2].getHour());
        StarHours4.setValue(activityWorking[0][3].getHour());
        StarHours5.setValue(activityWorking[0][4].getHour());
        StarHours6.setValue(activityWorking[0][5].getHour());
        StarHours7.setValue(activityWorking[0][6].getHour());
        //FinishWorking (Hours)
        FinishHours1.setValue(activityWorking[1][0].getHour());
        FinishHours2.setValue(activityWorking[1][1].getHour());
        FinishHours3.setValue(activityWorking[1][2].getHour());
        FinishHours4.setValue(activityWorking[1][3].getHour());
        FinishHours5.setValue(activityWorking[1][4].getHour());
        FinishHours6.setValue(activityWorking[1][5].getHour());
        FinishHours7.setValue(activityWorking[1][6].getHour());
        //StartWorking (Minute)
        StartMinute1.setValue(activityWorking[0][0].getMinute());
        StartMinute2.setValue(activityWorking[0][1].getMinute());
        StartMinute3.setValue(activityWorking[0][2].getMinute());
        StartMinute4.setValue(activityWorking[0][3].getMinute());
        StartMinute5.setValue(activityWorking[0][4].getMinute());
        StartMinute6.setValue(activityWorking[0][5].getMinute());
        StartMinute7.setValue(activityWorking[0][6].getMinute());
        //FinishWorking (Minute)
        FinishMinute1.setValue(activityWorking[1][0].getMinute());
        FinishMinute2.setValue(activityWorking[1][1].getMinute());
        FinishMinute3.setValue(activityWorking[1][2].getMinute());
        FinishMinute4.setValue(activityWorking[1][3].getMinute());
        FinishMinute5.setValue(activityWorking[1][4].getMinute());
        FinishMinute6.setValue(activityWorking[1][5].getMinute());
        FinishMinute7.setValue(activityWorking[1][6].getMinute());
        StarHours1.setItems(Hours);
        StarHours2.setItems(Hours);
        StarHours3.setItems(Hours);
        StarHours4.setItems(Hours);
        StarHours5.setItems(Hours);
        StarHours6.setItems(Hours);
        StarHours7.setItems(Hours);
        StartMinute1.setItems(Minute);
        StartMinute2.setItems(Minute);
        StartMinute3.setItems(Minute);
        StartMinute4.setItems(Minute);
        StartMinute5.setItems(Minute);
        StartMinute6.setItems(Minute);
        StartMinute7.setItems(Minute);
        FinishHours1.setItems(Hours);
        FinishHours2.setItems(Hours);
        FinishHours3.setItems(Hours);
        FinishHours4.setItems(Hours);
        FinishHours5.setItems(Hours);
        FinishHours6.setItems(Hours);
        FinishHours7.setItems(Hours);
        FinishMinute1.setItems(Minute);
        FinishMinute2.setItems(Minute);
        FinishMinute3.setItems(Minute);
        FinishMinute4.setItems(Minute);
        FinishMinute5.setItems(Minute);
        FinishMinute6.setItems(Minute);
        FinishMinute7.setItems(Minute);
    }

}
