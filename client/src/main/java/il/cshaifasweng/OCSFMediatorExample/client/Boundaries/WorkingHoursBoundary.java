package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import org.hibernate.type.LocalDateTimeType;

import javax.persistence.criteria.CriteriaBuilder;

public class WorkingHoursBoundary extends Boundary{
    ObservableList<String> Hours= FXCollections.observableArrayList("Hours");
    ObservableList<String> Minute= FXCollections.observableArrayList("Minute");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> FinishHours1;

    @FXML
    private ChoiceBox<String> FinishHours2;

    @FXML
    private ChoiceBox<String> FinishHours3;

    @FXML
    private ChoiceBox<String> FinishHours4;

    @FXML
    private ChoiceBox<String> FinishHours5;

    @FXML
    private ChoiceBox<String> FinishHours6;

    @FXML
    private ChoiceBox<String> FinishHours7;

    @FXML
    private ChoiceBox<String> FinishMinute1;

    @FXML
    private ChoiceBox<String> FinishMinute2;

    @FXML
    private ChoiceBox<String> FinishMinute3;

    @FXML
    private ChoiceBox<String> FinishMinute4;

    @FXML
    private ChoiceBox<String> FinishMinute5;

    @FXML
    private ChoiceBox<String> FinishMinute6;

    @FXML
    private ChoiceBox<String> FinishMinute7;

    @FXML
    private ChoiceBox<String> StarHours1;

    @FXML
    private ChoiceBox<String> StarHours2;

    @FXML
    private ChoiceBox<String> StarHours3;

    @FXML
    private ChoiceBox<String> StarHours4;

    @FXML
    private ChoiceBox<String> StarHours5;

    @FXML
    private ChoiceBox<String> StarHours6;

    @FXML
    private ChoiceBox<String> StarHours7;

    @FXML
    private ChoiceBox<String> StartMinute1;

    @FXML
    private ChoiceBox<String> StartMinute2;

    @FXML
    private ChoiceBox<String> StartMinute3;

    @FXML
    private ChoiceBox<String> StartMinute4;

    @FXML
    private ChoiceBox<String> StartMinute5;

    @FXML
    private ChoiceBox<String> StartMinute6;

    @FXML
    private ChoiceBox<String> StartMinute7;

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("UpdateOperatingHours");
    }

    @FXML
    void Submit(ActionEvent event) {

    }

    @FXML
    void initialize() {
        for(int i=0;i<=23;i++){
            Hours.add(String.valueOf(i));
        }
        for (int i=0;i<=59;i++){
            Minute.add(String.valueOf(i));
        }
        StarHours1.setValue("Hours");
        StarHours2.setValue("Hours");
        StarHours3.setValue("Hours");
        StarHours4.setValue("Hours");
        StarHours5.setValue("Hours");
        StarHours6.setValue("Hours");
        StarHours7.setValue("Hours");
        FinishHours1.setValue("Hours");
        FinishHours2.setValue("Hours");
        FinishHours3.setValue("Hours");
        FinishHours4.setValue("Hours");
        FinishHours5.setValue("Hours");
        FinishHours6.setValue("Hours");
        FinishHours7.setValue("Hours");
        StartMinute1.setValue("Minute");
        StartMinute2.setValue("Minute");
        StartMinute3.setValue("Minute");
        StartMinute4.setValue("Minute");
        StartMinute5.setValue("Minute");
        StartMinute6.setValue("Minute");
        StartMinute7.setValue("Minute");
        FinishMinute1.setValue("Minute");
        FinishMinute2.setValue("Minute");
        FinishMinute3.setValue("Minute");
        FinishMinute4.setValue("Minute");
        FinishMinute5.setValue("Minute");
        FinishMinute6.setValue("Minute");
        FinishMinute7.setValue("Minute");
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
