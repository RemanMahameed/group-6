package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import net.bytebuddy.agent.builder.AgentBuilder;

/**
 * this page chose the weekly report for a specific clinic and specific type of report (done or not done appointment)
 */

public class WeeklyReportBoundary extends Boundary{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Fri_CD;

    @FXML
    private Label Fri_CT;

    @FXML
    private Label Fri_FD;

    @FXML
    private Label Fri_LT;

    @FXML
    private Label Fri_NC;

    @FXML
    private Label Fri_PD;

    @FXML
    private Label Fri_V;

    @FXML
    private Label Mon_CD;

    @FXML
    private Label Mon_CT;

    @FXML
    private Label Mon_FD;

    @FXML
    private Label Mon_LT;

    @FXML
    private Label Mon_NC;

    @FXML
    private Label Mon_PD;

    @FXML
    private Label Mon_V;

    @FXML
    private Label Sat_CD;

    @FXML
    private Label Sat_CT;

    @FXML
    private Label Sat_FD;

    @FXML
    private Label Sat_LT;

    @FXML
    private Label Sat_NC;

    @FXML
    private Label Sat_PD;

    @FXML
    private Label Sat_V;

    @FXML
    private Label Sun_CD;

    @FXML
    private Label Sun_CT;

    @FXML
    private Label Sun_FD;

    @FXML
    private Label Sun_LT;

    @FXML
    private Label Sun_NC;

    @FXML
    private Label Sun_PD;

    @FXML
    private Label Sun_V;

    @FXML
    private Label Thue_PD;

    @FXML
    private Label Thur_CD;

    @FXML
    private Label Thur_CT;

    @FXML
    private Label Thur_FD;

    @FXML
    private Label Thur_LT;

    @FXML
    private Label Thur_NC;

    @FXML
    private Label Thur_V;

    @FXML
    private Label Tue_CD;

    @FXML
    private Label Tue_CT;

    @FXML
    private Label Tue_FD;

    @FXML
    private Label Tue_LT;

    @FXML
    private Label Tue_NC;

    @FXML
    private Label Tue_PD;

    @FXML
    private Label Tue_V;

    @FXML
    private Label Wed_CD;

    @FXML
    private Label Wed_CT;

    @FXML
    private Label Wed_FD;

    @FXML
    private Label Wed_LT;

    @FXML
    private Label Wed_NC;

    @FXML
    private Label Wed_PD;

    @FXML
    private Label Wed_V;

    @FXML
    void Back(ActionEvent event) throws IOException {

        if(SimpleClient.getUser_Ob().get(0).getClass().equals(HmoManager.class))
            App.setRoot("MainHm");
        else
        {
            SimpleClient.getParams().remove(params.size()-1);
            App.setRoot("ReportsMain");
        }
    }


    @FXML
    void initialize() {

        Clinic my_clinic= (Clinic) SimpleClient.getParams().get(params.size()-2);
        List<DoctorAppointment> DocApps=my_clinic.getDoctorAppointments();
        List<NurseAppointment> NurseApps= my_clinic.getNurseAppointments();
        List<LaboratoryFactsAppointment> LabApps = my_clinic.getLaboratoryFactsAppointments();
        List<VaccineAppointment> vaccineApps = my_clinic.getVaccineAppointments();
        List<CoronaTestAppointment> coronaApps = my_clinic.getCoronaTestAppointments();
        int SunC=0,MonC=0,TueC=0,WedC=0,ThuC=0,FriC=0,SatC=0;
        LocalDateTime now= LocalDateTime.now();
        LocalDateTime Sunday=now.minusWeeks(1);
        Sunday= Sunday.minusDays((now.getDayOfWeek().getValue()));
        System.out.println(Sunday.getDayOfWeek().toString());
        System.out.println(Sunday.getDayOfWeek().getValue());
        LocalDate SunDate = LocalDate.from(Sunday);
        LocalDate runDate;
        if(SimpleClient.getParams().get(params.size()-1).toString().equals("WeaklyDoneApps"))
        {
            for (NurseAppointment element:NurseApps)
            {

                runDate=LocalDate.from(element.getDate());
                if(element.isDone() && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                {
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                        SunC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                        MonC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                        TueC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                        WedC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                        ThuC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                        FriC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                        SatC++;
                }
            }
            Sun_NC.setText(String.valueOf(SunC));
            Mon_NC.setText(String.valueOf(MonC));
            Tue_NC.setText(String.valueOf(TueC));
            Wed_NC.setText(String.valueOf(WedC));
            Thur_NC.setText(String.valueOf(ThuC));
            Fri_NC.setText(String.valueOf(FriC));
            Sat_NC.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;

            for (VaccineAppointment element:vaccineApps)
            {

                runDate=LocalDate.from(element.getDate());
                if(element.isDone() && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                {
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                        SunC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                        MonC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                        TueC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                        WedC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                        ThuC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                        FriC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                        SatC++;
                }
            }
            Sun_V.setText(String.valueOf(SunC));
            Mon_V.setText(String.valueOf(MonC));
            Tue_V.setText(String.valueOf(TueC));
            Wed_V.setText(String.valueOf(WedC));
            Thur_V.setText(String.valueOf(ThuC));
            Fri_V.setText(String.valueOf(FriC));
            Sat_V.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;


            for (CoronaTestAppointment element:coronaApps)
            {

                runDate=LocalDate.from(element.getDate());
                if(element.isDone() && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                {
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                        SunC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                        MonC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                        TueC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                        WedC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                        ThuC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                        FriC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                        SatC++;
                }
            }
            Sun_CT.setText(String.valueOf(SunC));
            Mon_CT.setText(String.valueOf(MonC));
            Tue_CT.setText(String.valueOf(TueC));
            Wed_CT.setText(String.valueOf(WedC));
            Thur_CT.setText(String.valueOf(ThuC));
            Fri_CT.setText(String.valueOf(FriC));
            Sat_CT.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;

            for (LaboratoryFactsAppointment element:LabApps)
            {

                runDate=LocalDate.from(element.getDate());
                if(element.isDone() && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                {
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                        SunC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                        MonC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                        TueC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                        WedC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                        ThuC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                        FriC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                        SatC++;
                }
            }
            Sun_LT.setText(String.valueOf(SunC));
            Mon_LT.setText(String.valueOf(MonC));
            Tue_LT.setText(String.valueOf(TueC));
            Wed_LT.setText(String.valueOf(WedC));
            Thur_LT.setText(String.valueOf(ThuC));
            Fri_LT.setText(String.valueOf(FriC));
            Sat_LT.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;

            for (DoctorAppointment element:DocApps)
            {

                runDate=LocalDate.from(element.getDate());
                if(element.isDone() && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                    if(element.getAppointmentType().startsWith("ProfessionalDoctor-"))
                     {
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                        SunC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                        MonC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                        TueC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                        WedC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                        ThuC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                        FriC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                        SatC++;
                }
            }
            Sun_PD.setText(String.valueOf(SunC));
            Mon_PD.setText(String.valueOf(MonC));
            Tue_PD.setText(String.valueOf(TueC));
            Wed_PD.setText(String.valueOf(WedC));
            Thue_PD.setText(String.valueOf(ThuC));
            Fri_PD.setText(String.valueOf(FriC));
            Sat_PD.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;



            for (DoctorAppointment element:DocApps)
            {

                runDate=LocalDate.from(element.getDate());
                if(element.isDone() && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                    if(element.getAppointmentType().equals("FamilyApp"))
                    {
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                            SunC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                            MonC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                            TueC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                            WedC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                            ThuC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                            FriC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                            SatC++;
                    }
            }
            Sun_FD.setText(String.valueOf(SunC));
            Mon_FD.setText(String.valueOf(MonC));
            Tue_FD.setText(String.valueOf(TueC));
            Wed_FD.setText(String.valueOf(WedC));
            Thur_FD.setText(String.valueOf(ThuC));
            Fri_FD.setText(String.valueOf(FriC));
            Sat_FD.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;

                for (DoctorAppointment element:DocApps)
                {

                    runDate=LocalDate.from(element.getDate());
                    if(element.isDone() && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                        if(element.getAppointmentType().equals("PediatricianApp"))
                        {
                            if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                                SunC++;
                            if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                                MonC++;
                            if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                                TueC++;
                            if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                                WedC++;
                            if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                                ThuC++;
                            if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                                FriC++;
                            if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                                SatC++;
                        }
                }
            Sun_CD.setText(String.valueOf(SunC));
            Mon_CD.setText(String.valueOf(MonC));
            Tue_CD.setText(String.valueOf(TueC));
            Wed_CD.setText(String.valueOf(WedC));
            Thur_CD.setText(String.valueOf(ThuC));
            Fri_CD.setText(String.valueOf(FriC));
            Sat_CD.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;






        }
        else
        {


            for (NurseAppointment element:NurseApps)
            {

                runDate=LocalDate.from(element.getDate());
                if(!(element.isDone()) && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                {
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                        SunC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                        MonC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                        TueC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                        WedC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                        ThuC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                        FriC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                        SatC++;
                }
            }
            Sun_NC.setText(String.valueOf(SunC));
            Mon_NC.setText(String.valueOf(MonC));
            Tue_NC.setText(String.valueOf(TueC));
            Wed_NC.setText(String.valueOf(WedC));
            Thur_NC.setText(String.valueOf(ThuC));
            Fri_NC.setText(String.valueOf(FriC));
            Sat_NC.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;

            for (VaccineAppointment element:vaccineApps)
            {

                runDate=LocalDate.from(element.getDate());
                if(!(element.isDone())  && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                {
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                        SunC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                        MonC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                        TueC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                        WedC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                        ThuC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                        FriC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                        SatC++;
                }
            }
            Sun_V.setText(String.valueOf(SunC));
            Mon_V.setText(String.valueOf(MonC));
            Tue_V.setText(String.valueOf(TueC));
            Wed_V.setText(String.valueOf(WedC));
            Thur_V.setText(String.valueOf(ThuC));
            Fri_V.setText(String.valueOf(FriC));
            Sat_V.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;


            for (CoronaTestAppointment element:coronaApps)
            {

                runDate=LocalDate.from(element.getDate());
                if(!(element.isDone())  && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                {
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                        SunC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                        MonC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                        TueC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                        WedC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                        ThuC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                        FriC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                        SatC++;
                }
            }
            Sun_CT.setText(String.valueOf(SunC));
            Mon_CT.setText(String.valueOf(MonC));
            Tue_CT.setText(String.valueOf(TueC));
            Wed_CT.setText(String.valueOf(WedC));
            Thur_CT.setText(String.valueOf(ThuC));
            Fri_CT.setText(String.valueOf(FriC));
            Sat_CT.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;

            for (LaboratoryFactsAppointment element:LabApps)
            {

                runDate=LocalDate.from(element.getDate());
                if(!(element.isDone())  && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                {
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                        SunC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                        MonC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                        TueC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                        WedC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                        ThuC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                        FriC++;
                    if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                        SatC++;
                }
            }
            Sun_LT.setText(String.valueOf(SunC));
            Mon_LT.setText(String.valueOf(MonC));
            Tue_LT.setText(String.valueOf(TueC));
            Wed_LT.setText(String.valueOf(WedC));
            Thur_LT.setText(String.valueOf(ThuC));
            Fri_LT.setText(String.valueOf(FriC));
            Sat_LT.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;

            for (DoctorAppointment element:DocApps)
            {

                runDate=LocalDate.from(element.getDate());
                if(!(element.isDone())  && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                    if(element.getAppointmentType().startsWith("ProfessionalDoctor-"))
                    {
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                            SunC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                            MonC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                            TueC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                            WedC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                            ThuC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                            FriC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                            SatC++;
                    }
            }
            Sun_PD.setText(String.valueOf(SunC));
            Mon_PD.setText(String.valueOf(MonC));
            Tue_PD.setText(String.valueOf(TueC));
            Wed_PD.setText(String.valueOf(WedC));
            Thue_PD.setText(String.valueOf(ThuC));
            Fri_PD.setText(String.valueOf(FriC));
            Sat_PD.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;



            for (DoctorAppointment element:DocApps)
            {

                runDate=LocalDate.from(element.getDate());
                if(!(element.isDone())  && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                    if(element.getAppointmentType().equals("FamilyApp"))
                    {
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                            SunC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                            MonC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                            TueC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                            WedC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                            ThuC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                            FriC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                            SatC++;
                    }
            }
            Sun_FD.setText(String.valueOf(SunC));
            Mon_FD.setText(String.valueOf(MonC));
            Tue_FD.setText(String.valueOf(TueC));
            Wed_FD.setText(String.valueOf(WedC));
            Thur_FD.setText(String.valueOf(ThuC));
            Fri_FD.setText(String.valueOf(FriC));
            Sat_FD.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;

            for (DoctorAppointment element:DocApps)
            {

                runDate=LocalDate.from(element.getDate());
                if(!(element.isDone())  && (runDate.isEqual(SunDate) ||runDate.isAfter(SunDate)) && (runDate.isEqual(SunDate.plusDays(6)) || runDate.isBefore(SunDate.plusDays(6))))
                    if(element.getAppointmentType().equals("PediatricianApp"))
                    {
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek()))
                            SunC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(1)))
                            MonC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(2)))
                            TueC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(3)))
                            WedC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(4)))
                            ThuC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(5)))
                            FriC++;
                        if (element.getDate().getDayOfWeek().equals(Sunday.getDayOfWeek().plus(6)))
                            SatC++;
                    }
            }
            Sun_CD.setText(String.valueOf(SunC));
            Mon_CD.setText(String.valueOf(MonC));
            Tue_CD.setText(String.valueOf(TueC));
            Wed_CD.setText(String.valueOf(WedC));
            Thur_CD.setText(String.valueOf(ThuC));
            Fri_CD.setText(String.valueOf(FriC));
            Sat_CD.setText(String.valueOf(SatC));
            SunC=0;
            MonC=0;
            TueC=0;
            WedC=0;
            ThuC=0;
            FriC=0;
            SatC=0;





        }



//        String clinic_name=(this.params.get(1)).toString();
//        int end=clinic_name.length()-1;
//        clinic_name= clinic_name.substring(1,end);
//        System.out.println(clinic_name+);
//
//        LinkedList<String> choice = new LinkedList<String>();
//        choice.add("#HMClinicName" );
//        //choice.add(String.valueOf(doctor.getId()));
//        choice.add( clinic_name);
//        System.out.println("the selected clinic is: " + clinic_name);
//        try {
//            SimpleClient.getClient().sendToServer(choice);
//            Clinic my_clinic=(Clinic) params.get(3) ;
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

    }

}
