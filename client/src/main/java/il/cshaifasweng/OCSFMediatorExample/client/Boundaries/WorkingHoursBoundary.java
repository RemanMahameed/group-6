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
    protected ObservableList<String> Hours= FXCollections.observableArrayList();
    protected ObservableList<String> Minute= FXCollections.observableArrayList();
    protected WorkingHours workingHours=(WorkingHours) params.get(0);
    protected LocalTime[][] activityWorking=workingHours.getActivityTime();
    protected LocalTime[][] clinicActivityWorking=workingHours.getClinicActivityTime();
    protected LocalTime[][] newActivityWorkingTime=new LocalTime[2][7];
    protected String type=workingHours.getType();
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
         App.setRoot("MainCm");
    }

    @FXML
    void Submit(ActionEvent event) throws IOException {
        //Set start working Hours
        newActivityWorkingTime[0][0]=LocalTime.of(Integer.parseInt(StarHours1.getValue()),Integer.parseInt(StartMinute1.getValue()));
        newActivityWorkingTime[0][1]=LocalTime.of(Integer.parseInt(StarHours2.getValue()),Integer.parseInt(StartMinute2.getValue()));
        newActivityWorkingTime[0][2]=LocalTime.of(Integer.parseInt(StarHours3.getValue()),Integer.parseInt(StartMinute3.getValue()));
        newActivityWorkingTime[0][3]=LocalTime.of(Integer.parseInt(StarHours4.getValue()),Integer.parseInt(StartMinute4.getValue()));
        newActivityWorkingTime[0][4]=LocalTime.of(Integer.parseInt(StarHours5.getValue()),Integer.parseInt(StartMinute5.getValue()));
        newActivityWorkingTime[0][5]=LocalTime.of(Integer.parseInt(StarHours6.getValue()),Integer.parseInt(StartMinute6.getValue()));
        newActivityWorkingTime[0][6]=LocalTime.of(Integer.parseInt(StarHours7.getValue()),Integer.parseInt(StartMinute7.getValue()));
        //Set finish working hours
        newActivityWorkingTime[1][0]=LocalTime.of(Integer.parseInt(FinishHours1.getValue()),Integer.parseInt(FinishMinute1.getValue()));
        newActivityWorkingTime[1][1]=LocalTime.of(Integer.parseInt(FinishHours2.getValue()),Integer.parseInt(FinishMinute2.getValue()));
        newActivityWorkingTime[1][2]=LocalTime.of(Integer.parseInt(FinishHours3.getValue()),Integer.parseInt(FinishMinute3.getValue()));
        newActivityWorkingTime[1][3]=LocalTime.of(Integer.parseInt(FinishHours4.getValue()),Integer.parseInt(FinishMinute4.getValue()));
        newActivityWorkingTime[1][4]=LocalTime.of(Integer.parseInt(FinishHours5.getValue()),Integer.parseInt(FinishMinute5.getValue()));
        newActivityWorkingTime[1][5]=LocalTime.of(Integer.parseInt(FinishHours6.getValue()),Integer.parseInt(FinishMinute6.getValue()));
        newActivityWorkingTime[1][6]=LocalTime.of(Integer.parseInt(FinishHours7.getValue()),Integer.parseInt(FinishMinute7.getValue()));
        //what we want to change
        message.clear();
        message.add("#SetNewWorkingHours");
        switch (type) {
            case ("Clinic"):
                message.add("Clinic");
                break;
            case ("Clinic's Doctor"):
                message.add("Clinic's Doctor");
                break;
            case ("Corona Test"):
                message.add("Corona Test");
                break;
            case ("Vaccine"):
                message.add("Vaccine");
                break;
        }

        if(CheckCorrectness(clinicActivityWorking,newActivityWorkingTime)){
            message.add(workingHours.getClinicName());
            message.add(newActivityWorkingTime);
            message.add(workingHours.getDoctorId());
            SimpleClient.getClient().sendToServer(message);
            MessageBoundary.displayInfo("Change successfully");
            App.setRoot("MainCm");
        }else{
            MessageBoundary.displayInfo("The new working hours wrong!!");
        }
        //message (#SetNewWorkingHours,type,clinic name,doctorId)
    }

    @FXML
    void initialize() {
        for(int i=0;i<=23;i++){
            Hours.add(String.valueOf(i));
        }
        for (int i=0;i<=59;i++){
            Minute.add(String.valueOf(i));
        }
        //StartWorking (Hours)
        StarHours1.setValue(String.valueOf(activityWorking[0][0].getHour()));
        StarHours2.setValue(String.valueOf(activityWorking[0][1].getHour()));
        StarHours3.setValue(String.valueOf(activityWorking[0][2].getHour()));
        StarHours4.setValue(String.valueOf(activityWorking[0][3].getHour()));
        StarHours5.setValue(String.valueOf(activityWorking[0][4].getHour()));
        StarHours6.setValue(String.valueOf(activityWorking[0][5].getHour()));
        StarHours7.setValue(String.valueOf(activityWorking[0][6].getHour()));
        //FinishWorking (Hours)
        FinishHours1.setValue(String.valueOf(activityWorking[1][0].getHour()));
        FinishHours2.setValue(String.valueOf(activityWorking[1][1].getHour()));
        FinishHours3.setValue(String.valueOf(activityWorking[1][2].getHour()));
        FinishHours4.setValue(String.valueOf(activityWorking[1][3].getHour()));
        FinishHours5.setValue(String.valueOf(activityWorking[1][4].getHour()));
        FinishHours6.setValue(String.valueOf(activityWorking[1][5].getHour()));
        FinishHours7.setValue(String.valueOf(activityWorking[1][6].getHour()));
        //StartWorking (Minute))
        StartMinute1.setValue(String.valueOf(activityWorking[0][0].getMinute()));
        StartMinute2.setValue(String.valueOf(activityWorking[0][1].getMinute()));
        StartMinute3.setValue(String.valueOf(activityWorking[0][2].getMinute()));
        StartMinute4.setValue(String.valueOf(activityWorking[0][3].getMinute()));
        StartMinute5.setValue(String.valueOf(activityWorking[0][4].getMinute()));
        StartMinute6.setValue(String.valueOf(activityWorking[0][5].getMinute()));
        StartMinute7.setValue(String.valueOf(activityWorking[0][6].getMinute()));
        //FinishWorking (Minute)
        FinishMinute1.setValue(String.valueOf(activityWorking[1][0].getMinute()));
        FinishMinute2.setValue(String.valueOf(activityWorking[1][1].getMinute()));
        FinishMinute3.setValue(String.valueOf(activityWorking[1][2].getMinute()));
        FinishMinute4.setValue(String.valueOf(activityWorking[1][3].getMinute()));
        FinishMinute5.setValue(String.valueOf(activityWorking[1][4].getMinute()));
        FinishMinute6.setValue(String.valueOf(activityWorking[1][5].getMinute()));
        FinishMinute7.setValue(String.valueOf(activityWorking[1][6].getMinute()));
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
    //check is service time is in clinic time
    public boolean CheckCorrectness(LocalTime[][] clinicActivityTime,LocalTime[][] serviceActivityTime){
        if(  (clinicActivityTime[0][0].minusSeconds(1).isBefore(serviceActivityTime[0][0]) && clinicActivityTime[1][0].plusSeconds(1).isAfter(serviceActivityTime[1][0]))
              &&(clinicActivityTime[0][1].minusSeconds(1).isBefore(serviceActivityTime[0][1]) && clinicActivityTime[1][1].plusSeconds(1).isAfter(serviceActivityTime[1][1]))
              &&(clinicActivityTime[0][2].minusSeconds(1).isBefore(serviceActivityTime[0][2]) && clinicActivityTime[1][2].plusSeconds(1).isAfter(serviceActivityTime[1][2]))
              &&(clinicActivityTime[0][3].minusSeconds(1).isBefore(serviceActivityTime[0][3]) && clinicActivityTime[1][3].plusSeconds(1).isAfter(serviceActivityTime[1][3]))
              &&(clinicActivityTime[0][4].minusSeconds(1).isBefore(serviceActivityTime[0][4]) && clinicActivityTime[1][4].plusSeconds(1).isAfter(serviceActivityTime[1][4]))
              &&(clinicActivityTime[0][5].minusSeconds(1).isBefore(serviceActivityTime[0][5]) && clinicActivityTime[1][5].plusSeconds(1).isAfter(serviceActivityTime[1][5]))
              &&(clinicActivityTime[0][6].minusSeconds(1).isBefore(serviceActivityTime[0][6]) && clinicActivityTime[1][6].plusSeconds(1).isAfter(serviceActivityTime[1][6]))
        )
              return true;
        return false;
    }
}
