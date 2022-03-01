package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;


import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Doctor_AVG;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.DoctorAppointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.HmoManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

/**
 * this boundary is for daily report
 * it shows th average of waiting time for each doctor for the relevant clinic
 */

public class DailyReportBoundary extends Boundary{
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    Label Date;

    @FXML
    private TableColumn<Doctor_AVG, String> Doctor_name;

    @FXML
    private TableView<Doctor_AVG> TableView;

    @FXML
    private TableColumn<Doctor_AVG, Double> waiting_time;

    @FXML
    void Back(ActionEvent event) throws IOException {

        if(SimpleClient.getUser_Ob().get(0).getClass().equals(HmoManager.class))
            App.setRoot("MainHm");
        else
        {

            App.setRoot("ReportsMain");
        }
    }
    @FXML
    void initialize()
    {

        Doctor_name.setCellValueFactory(new PropertyValueFactory<Doctor_AVG,String>("doctor_name"));
        //Doctor_name.setCellValueFactory(new PropertyValueFactory<Doctor_AVG,String>("doctor_name"));
        waiting_time.setCellValueFactory(new PropertyValueFactory<Doctor_AVG,Double>("AVG"));

        Date.setText(LocalDate.now().toString());
        Clinic C=(Clinic) SimpleClient.getParams().get(params.size()-1);
        List<Doctor> All_docs=C.getDoctors();
        List<DoctorAppointment> Doctor_App=C.getDoctorAppointments();
        Double AVG=0.0;
        int counter=0;
        long avg_sec=0;
        Duration difference;
        for(int i=0;i<All_docs.size();i++) {
            avg_sec=0;
            counter=0;
            for (int j=0;j<Doctor_App.size();j++) {
                if(LocalDate.from(Doctor_App.get(j).getDate()).isAfter(LocalDate.now().minusDays(1)) && LocalDate.from(Doctor_App.get(j).getDate()).isBefore(LocalDate.now().plusDays(1)))
                {if(Doctor_App.get(j).getDoctor().getId()==All_docs.get(i).getId() && Doctor_App.get(j).isDone())
                {
                    counter++;
                    difference=Duration.between(Doctor_App.get(j).getRealTime().toLocalTime(),Doctor_App.get(j).getDate());
                    avg_sec+=difference.getSeconds();
                }
                }}
            AVG= Double.valueOf(avg_sec)/60;
            if(counter!=0)
                AVG/=counter;
            TableView.getItems().add(new Doctor_AVG(All_docs.get(i).getFirstName() + " " + All_docs.get(i).getLastName(), AVG));

        }
    }
}
