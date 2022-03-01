package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.AppointmentTable;

/**
 * this boundary shows the patients that belong to clinic staff user (doctor,nurse,laboratory fact)
 */

public class ViewPatientListBoundary extends Boundary {


    Object user = user_Ob.get(0);
    int index =params.size()-1;
//    Clinic clinic = (Clinic) params.get(index);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label ClinicLabel;



    @FXML
    private ListView<String> PatientList;

    @FXML
    void backAction(ActionEvent event) {
        if (user.getClass().equals(Doctor.class)) {
            try {
                App.setRoot("DoctorActions");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (user.getClass().equals(Nurse.class)) {
            try {
                App.setRoot("nursemain");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (user.getClass().equals(LaboratoryFacts.class)) {
            try {
                App.setRoot("LaboratoryFactMain");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    @FXML
    void initialize() {

        if (user.getClass().equals(Doctor.class))
        {
            Clinic clinic = (Clinic) params.get(index);
            String belong = "These Actions is belong to " + clinic.getClinicType();
            ClinicLabel.setText(belong);
            System.out.println("I am a doctor");
            Doctor doctor = (Doctor) user_Ob.get(0);
            List<Patient> patients = doctor.getPatients();
            for(Patient element : patients)
              PatientList.getItems().add(element.getFirstName() + " " + element.getLastName());
        }
        if (user.getClass().equals(Nurse.class))
        {
            System.out.println("I am a Nurse");
            List<Object> AllApps = SimpleClient.getAppointmentTable();
            List<NurseAppointment> AllNurseApss=new LinkedList<>();
            for (int i=0;i<AllApps.size();i++){
                AllNurseApss.add((NurseAppointment) AllApps.get(i));
            }
            Nurse nurse = (Nurse) user_Ob.get(0);
            int nurseId=nurse.getId();
            List<Patient> patients = new LinkedList<>();
            for (NurseAppointment element : AllNurseApss){
                if(element.isDone() && element.getNurse().getId()==nurseId && !patients.contains(element.getPatient()))
                    patients.add(element.getPatient());
            }
            for(Patient element : patients)
                PatientList.getItems().add(element.getFirstName() + " " + element.getLastName());

        }
        if (user.getClass().equals(LaboratoryFacts.class))
        {
            System.out.println("I am a laboratory factor");
            List<Object> AllApps = SimpleClient.getAppointmentTable();
            List<LaboratoryFactsAppointment> AllLAbApps=new LinkedList<>();
            for (int i=0;i<AllApps.size();i++){
                AllLAbApps.add((LaboratoryFactsAppointment) AllApps.get(i));
            }
            LaboratoryFacts lab = (LaboratoryFacts) user_Ob.get(0);
            int labId=lab.getId();
            List<Patient> patients = new LinkedList<>();
            for (LaboratoryFactsAppointment element : AllLAbApps){
                if(element.isDone() && element.getLaboratoryFacts().getId()==labId && !patients.contains(element.getPatient()))
                    patients.add(element.getPatient());
            }

            for(Patient element : patients)
                PatientList.getItems().add(element.getFirstName() + " " + element.getLastName());
        }

    }

}
