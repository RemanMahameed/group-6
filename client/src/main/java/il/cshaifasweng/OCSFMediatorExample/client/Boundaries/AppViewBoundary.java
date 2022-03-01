package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.DoctorApp;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * this boundary shows the appointment that belong to clinic staff user (doctor,nurse,laboratory fact)
 * the user can call the patient to get in (just when the patient come to the clinic) other wise the user can't call
 */

public class AppViewBoundary extends Boundary{
    Object user =  user_Ob.get(0);
    int index =params.size()-1;
//    Clinic clinic = (Clinic) params.get(index);
    @FXML
    private ListView<String> AppList;

    @FXML
    private Label ClinicLabel;
    @FXML
    void RefreshAction(ActionEvent event){
        LinkedList<String> ReMsg = new LinkedList<String>();
        ReMsg.add("#Refresh" );
        if (user.getClass().equals(Doctor.class)) {
            Doctor doctor = (Doctor) user_Ob.get(0);
            ReMsg.add("doctor");
            ReMsg.add(String.valueOf(doctor.getId()));
        }else if (user.getClass().equals(Nurse.class)) {
            Nurse nurse = (Nurse) user_Ob.get(0);
            ReMsg.add("nurse");
            ReMsg.add(String.valueOf(nurse.getId()));
        } else if (user.getClass().equals(LaboratoryFacts.class)) {
            LaboratoryFacts labFact = (LaboratoryFacts) user_Ob.get(0);
            ReMsg.add("lab");
            ReMsg.add(String.valueOf(labFact.getId()));
        }
        try {
            SimpleClient.getClient().sendToServer(ReMsg);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

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
    void callAction(ActionEvent event) {
        if(AppList.getSelectionModel().getSelectedItems().isEmpty()){
            MessageBoundary.displayError("please choose an App");
        }
        else {
            if (user.getClass().equals(Doctor.class)) {
                Doctor doctor = (Doctor) user_Ob.get(0);
                LinkedList<String> choice = new LinkedList<String>();
                choice.add("#SelectedDoctoreApp");
                choice.add( AppList.getSelectionModel().getSelectedItem());
                choice.add(String.valueOf(doctor.getId()));
                choice.add(String.valueOf(doctor.getClinicList().get(0).getId()));
                try {
                    SimpleClient.getClient().sendToServer(choice);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (user.getClass().equals(Nurse.class)) {
                Nurse nurse = (Nurse) user_Ob.get(0);
                LinkedList<String> choice = new LinkedList<String>();
                choice.add("#SelectedNurseApp");
                choice.add( AppList.getSelectionModel().getSelectedItem());
                choice.add(String.valueOf(nurse.getId()));
                choice.add(String.valueOf(nurse.getClinicList().get(0).getId()));
                try {
                    SimpleClient.getClient().sendToServer(choice);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (user.getClass().equals(LaboratoryFacts.class)) {
                LaboratoryFacts labFact = (LaboratoryFacts) user_Ob.get(0);
                LinkedList<String> choice = new LinkedList<String>();
                choice.add("#SelectedLabFactApp");
                choice.add( AppList.getSelectionModel().getSelectedItem());
                choice.add(String.valueOf(labFact.getId()));
                choice.add(String.valueOf(labFact.getClinicList().get(0).getId()));
                try {
                    SimpleClient.getClient().sendToServer(choice);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @FXML
    void initialize() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        if (user.getClass().equals(Doctor.class)) {
            Clinic clinic = (Clinic) params.get(index);
            String belong = "These Actions is belong to " + clinic.getClinicType();
            ClinicLabel.setText(belong);
            System.out.println("I am a doctor");
            Doctor doctor = (Doctor) user_Ob.get(0);
            List<DoctorAppointment> appointments = doctor.getAppointments();
            List<String> App_nums = new LinkedList<>();
            List<DoctorAppointment> SortedAppointments = new LinkedList<>();
            for(DoctorAppointment element : appointments){
                App_nums.add(element.getAppNum());
            }

            Collections.sort(App_nums);
            for(String num : App_nums){
                for(DoctorAppointment element : appointments){
                    if(element.getAppNum().equals(num)){
                        SortedAppointments.add(element);
                    }
                }
            }
            String IsHere ;
            for (DoctorAppointment element : SortedAppointments)
                if(!element.isDone()) {
                    IsHere= element.getAppNum().equals("initial val") ? "the patient isn't here" : element.getAppNum();
                    AppList.getItems().add("App ID: " + element.getId() + " Patient ID: " + element.getPatient().getId()+"\nPatient name is : " + element.getPatient().getFirstName() + " " + element.getPatient().getLastName() + "\n"
                            + "Appointment Date is : " + dtf.format(element.getDate()) + "\n" +"Number of App is: " + IsHere
                    );
                }
        }
        if(user.getClass().equals(Nurse.class)) {
            System.out.println("I am a nurse");
            Nurse nurse = (Nurse) user_Ob.get(0);
            Clinic Nclinic = nurse.getClinicList().get(0);
            List<NurseAppointment> appointments = Nclinic.getNurseAppointments();
            for (NurseAppointment element : appointments)
                if(!element.isDone()) {
                    AppList.getItems().add("App ID: " + element.getId() + " Patient ID: " + element.getPatient().getId() + "\nPatient name is : " + element.getPatient().getFirstName() + " " + element.getPatient().getLastName() + "\n"
                            + "Appointment Date is : " + dtf.format(element.getDate()) + "\n" + "Number of App is: " + element.getAppNum()
                    );

        }
        }
        if(user.getClass().equals(LaboratoryFacts.class)) {
            System.out.println("I am a laboratory factor");
            LaboratoryFacts laboratoryFact = (LaboratoryFacts) user_Ob.get(0);
            Clinic Lclinic = laboratoryFact.getClinicList().get(0);
            List<LaboratoryFactsAppointment> appointments = Lclinic.getLaboratoryFactsAppointments();
            for (LaboratoryFactsAppointment element : appointments)
                if(!element.isDone()) {
                    AppList.getItems().add("App ID: " + element.getId() + " Patient ID: " + element.getPatient().getId() + "\nPatient name is : " + element.getPatient().getFirstName() + " " + element.getPatient().getLastName() + "\n"
                            + "Appointment Date is : " + dtf.format(element.getDate()) + "\n" + "Number of App is: " + element.getAppNum()
                    );

                }
        }

    }
}
