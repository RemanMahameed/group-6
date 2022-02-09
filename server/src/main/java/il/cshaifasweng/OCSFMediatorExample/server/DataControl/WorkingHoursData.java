package il.cshaifasweng.OCSFMediatorExample.server.DataControl;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.DoctorNames;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.WorkingHours;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class WorkingHoursData extends DataClass {
    private static Session session;
    public static void getWorkingHours(WorkingHours workingHours, String Name, String type,String doctorId) throws Exception {
        switch (type) {
            case ("Clinic"):
                getClinicWorkingHours(workingHours, Name, type);
                break;
            case ("Clinic's Doctor"):
                getDoctorWorkingHours(workingHours, doctorId, type,Name);
                break;
            case ("Corona Test"):
                getCoronaTestWorkingHours(workingHours, Name, type);
                break;
            case ("Vaccine"):
                getVaccineWorkingHours(workingHours,Name, type);
                break;
        }
    }

    public static void getClinicWorkingHours(WorkingHours workingHours, String clinicName, String type) throws Exception {
        Clinic clinic = getClinicByName(clinicName);
        LocalTime[][] activityTime = clinic.getActivityTime();
        workingHours.setActiveTime(activityTime);
        workingHours.setClinicActivityTime(activityTime);
        workingHours.setType(type);
        workingHours.setClinicName(clinicName);
        workingHours.setDoctorId(0);
    }

    public static void getCoronaTestWorkingHours(WorkingHours workingHours, String clinicName, String type) throws Exception {
        Clinic clinic = getClinicByName(clinicName);
        LocalTime[][] activityTime = clinic.getCoronaTestTime();
        workingHours.setActiveTime(activityTime);
        workingHours.setClinicActivityTime(clinic.getActivityTime());
        workingHours.setType(type);
        workingHours.setClinicName(clinicName);
        workingHours.setDoctorId(0);
    }

    public static void getVaccineWorkingHours(WorkingHours workingHours, String clinicName, String type) throws Exception {
        Clinic clinic = getClinicByName(clinicName);
        LocalTime[][] activityTime = clinic.getVaccineTime();
        workingHours.setActiveTime(activityTime);
        workingHours.setClinicActivityTime(clinic.getActivityTime());
        workingHours.setType(type);
        workingHours.setClinicName(clinicName);
        workingHours.setDoctorId(0);
    }
    public static void getDoctorWorkingHours(WorkingHours workingHours, String StringDoctorID, String type,String clinicName) throws Exception {
        Clinic clinic = getClinicByName(clinicName);
        int doctorId=Integer.parseInt(StringDoctorID);
        Doctor doctor = getDoctorById(doctorId);
        List<ReceptionTime> receptionTime= doctor.getReceptionTime();
        // get the rightWorking hours()
        for (ReceptionTime receptionTime1:receptionTime) {
            if (receptionTime1.getClinicName().equals(clinicName))
                workingHours.setActiveTime(receptionTime1.getActiveTime());
        }
        workingHours.setType(type);
        workingHours.setClinicActivityTime(clinic.getActivityTime());
        workingHours.setClinicName(clinicName);
        workingHours.setDoctorId(doctorId);
    }
    public static void getClinicDoctors(DoctorNames doctorNames,String clinicName,String type) throws Exception {
        Clinic clinic=getClinicByName(clinicName);
        List<Doctor> doctors=clinic.getDoctors();
        List<String> doctorsFirstName=new LinkedList<>();
        List<String> doctorsLastName=new LinkedList<>();
        List<Integer> doctorsId=new LinkedList<>();
        for(Doctor doctor:doctors){
            doctorsFirstName.add(doctor.getFirstName());
            doctorsLastName.add(doctor.getLastName());
            doctorsId.add(doctor.getId());
        }
        doctorNames.setDoctorsFirstName(doctorsFirstName);
        doctorNames.setDoctorsLastName(doctorsLastName);
        doctorNames.setDoctorsId(doctorsId);
        doctorNames.setFlag(type);
        doctorNames.setClinicName(clinicName);
    }
    public static void SetNewWorkingHours(String clinicName, String type, LocalTime[][] newWorkingHours,int doctorId) throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Clinic clinic=getClinicByName(clinicName);
        Doctor doctor = getDoctorById(doctorId);
        List<ReceptionTime> receptionTime= doctor.getReceptionTime();
        LocalTime[][] oldWorkingHours=new LocalTime[2][7];
        switch (type) {
            case ("Clinic"):
                //SetClinicWorkingHours(clinicName, newWorkingHours);
                break;
            case ("Clinic's Doctor"):
                // get the rightWorking hours()
                int temp=0;
                for (ReceptionTime receptionTime1:receptionTime) {
                    if (receptionTime1.getClinicName().equals(clinicName)){
                        oldWorkingHours=receptionTime1.getActiveTime();
                        receptionTime1.setActiveTime(newWorkingHours);
                        doctor.getReceptionTime().set(temp,receptionTime1);
                        SetWorkingHours(clinicName, newWorkingHours,oldWorkingHours,type,doctorId);
                        session.saveOrUpdate(doctor);
                    }
                    temp++;
                }
                break;
            case ("Corona Test"):
                oldWorkingHours=clinic.getCoronaTestTime();
                SetWorkingHours(clinicName, newWorkingHours,oldWorkingHours,type,doctorId);
                break;
            case ("Vaccine"):
                oldWorkingHours=clinic.getVaccineTime();
                SetWorkingHours(clinicName, newWorkingHours,oldWorkingHours,type,doctorId);
                break;
        }
        session.getTransaction().commit(); // Save everything.
        session.close();
    }
    public static void SetWorkingHours(String clinicName, LocalTime[][] newWorkingHours,LocalTime[][] oldWorkingHours,String type,int doctorId) throws Exception {
        Clinic clinic=getClinicByName(clinicName);
        //newStart
        LocalTime newSundayStart= newWorkingHours[0][0];
        LocalTime newMondayStart= newWorkingHours[0][1];
        LocalTime newTuesdayStart= newWorkingHours[0][2];
        LocalTime newWednesdayStart= newWorkingHours[0][3];
        LocalTime newThursdayStart= newWorkingHours[0][4];
        LocalTime newFridayStart= newWorkingHours[0][5];
        LocalTime newSaturdayStart= newWorkingHours[0][6];
        //newFinish
        LocalTime newSundayFinish=newWorkingHours[1][0];
        LocalTime newMondayFinish=newWorkingHours[1][1];
        LocalTime newTuesdayFinish=newWorkingHours[1][2];
        LocalTime newWednesdayFinish=newWorkingHours[1][3];
        LocalTime newThursdayFinish=newWorkingHours[1][4];
        LocalTime newFridayFinish=newWorkingHours[1][5];
        LocalTime newSaturdayFinish=newWorkingHours[1][6];
        //oldStart
        LocalTime oldSundayStart= oldWorkingHours[0][0];
        LocalTime oldMondayStart= oldWorkingHours[0][1];
        LocalTime oldTuesdayStart= oldWorkingHours[0][2];
        LocalTime oldWednesdayStart= oldWorkingHours[0][3];
        LocalTime oldThursdayStart= oldWorkingHours[0][4];
        LocalTime oldFridayStart= oldWorkingHours[0][5];
        LocalTime oldSaturdayStart= oldWorkingHours[0][6];
        //oldFinish
        LocalTime oldSundayFinish=oldWorkingHours[1][0];
        LocalTime oldMondayFinish=oldWorkingHours[1][1];
        LocalTime oldTuesdayFinish=oldWorkingHours[1][2];
        LocalTime oldWednesdayFinish=oldWorkingHours[1][3];
        LocalTime oldThursdayFinish=oldWorkingHours[1][4];
        LocalTime oldFridayFinish=oldWorkingHours[1][5];
        LocalTime oldSaturdayFinish=oldWorkingHours[1][6];

        if(type.equals("Corona Test")){
            List<CoronaTestAppointment> coronaTestAppointments=clinic.getCoronaTestAppointments();
            checkCoronaTestAppointment(coronaTestAppointments,"Sunday",oldSundayStart,newSundayStart,oldSundayFinish,newSundayFinish);
            checkCoronaTestAppointment(coronaTestAppointments,"Monday",oldMondayStart,newMondayStart,oldMondayFinish,newMondayFinish);
            checkCoronaTestAppointment(coronaTestAppointments,"Tuesday",oldTuesdayStart,newTuesdayStart,oldTuesdayFinish,newTuesdayFinish);
            checkCoronaTestAppointment(coronaTestAppointments,"Wednesday",oldWednesdayStart,newWednesdayStart,oldWednesdayFinish,newWednesdayFinish);
            checkCoronaTestAppointment(coronaTestAppointments,"Thursday",oldThursdayStart,newThursdayStart,oldThursdayFinish,newThursdayFinish);
            checkCoronaTestAppointment(coronaTestAppointments,"Friday",oldFridayStart,newFridayStart,oldFridayFinish,newFridayFinish);
            checkCoronaTestAppointment(coronaTestAppointments,"Saturday",oldSaturdayStart,newSaturdayStart,oldSaturdayFinish,newSaturdayFinish);
            clinic.setCoronaTestTime(newWorkingHours);
            session.saveOrUpdate(clinic);
        }else if(type.equals("Vaccine")){
            List<VaccineAppointment> vaccineAppointments=clinic.getVaccineAppointments();
            checkVaccineAppointment(vaccineAppointments,"Sunday",oldSundayStart,newSundayStart,oldSundayFinish,newSundayFinish);
            checkVaccineAppointment(vaccineAppointments,"Monday",oldMondayStart,newMondayStart,oldMondayFinish,newMondayFinish);
            checkVaccineAppointment(vaccineAppointments,"Tuesday",oldTuesdayStart,newTuesdayStart,oldTuesdayFinish,newTuesdayFinish);
            checkVaccineAppointment(vaccineAppointments,"Wednesday",oldWednesdayStart,newWednesdayStart,oldWednesdayFinish,newWednesdayFinish);
            checkVaccineAppointment(vaccineAppointments,"Thursday",oldThursdayStart,newThursdayStart,oldThursdayFinish,newThursdayFinish);
            checkVaccineAppointment(vaccineAppointments,"Friday",oldFridayStart,newFridayStart,oldFridayFinish,newFridayFinish);
            checkVaccineAppointment(vaccineAppointments,"Saturday",oldSaturdayStart,newSaturdayStart,oldSaturdayFinish,newSaturdayFinish);
            clinic.setVaccineTime(newWorkingHours);
            session.saveOrUpdate(clinic);
        }else if(type.equals("Clinic's Doctor")){
            int temp=0;
            Doctor doctor =getDoctorById(doctorId);
            List<DoctorAppointment> doctorAppointments=doctor.getAppointments();
            List<DoctorAppointment> doctorAppointmentsList=new LinkedList<>();
            for (DoctorAppointment doctorAppointment:doctorAppointments) {
                if (doctorAppointment.getClinic().getClinicType().equals(clinicName)){
                    doctorAppointmentsList.add(doctorAppointment);
                }
            }
            checkDoctorAppointment(doctorAppointmentsList,"Sunday",oldSundayStart,newSundayStart,oldSundayFinish,newSundayFinish);
            checkDoctorAppointment(doctorAppointmentsList,"Monday",oldMondayStart,newMondayStart,oldMondayFinish,newMondayFinish);
            checkDoctorAppointment(doctorAppointmentsList,"Tuesday",oldTuesdayStart,newTuesdayStart,oldTuesdayFinish,newTuesdayFinish);
            checkDoctorAppointment(doctorAppointmentsList,"Wednesday",oldWednesdayStart,newWednesdayStart,oldWednesdayFinish,newWednesdayFinish);
            checkDoctorAppointment(doctorAppointmentsList,"Thursday",oldThursdayStart,newThursdayStart,oldThursdayFinish,newThursdayFinish);
            checkDoctorAppointment(doctorAppointmentsList,"Friday",oldFridayStart,newFridayStart,oldFridayFinish,newFridayFinish);
            checkDoctorAppointment(doctorAppointmentsList,"Saturday",oldSaturdayStart,newSaturdayStart,oldSaturdayFinish,newSaturdayFinish);;
        }
    }
    public static void checkCoronaTestAppointment(List<CoronaTestAppointment> coronaTestAppointments,String day,LocalTime oldStart, LocalTime newStart,LocalTime oldFinish,LocalTime newFinish) {
        if ((oldStart.isBefore(newStart)) || oldFinish.isAfter(newFinish)) {
            for (CoronaTestAppointment coronaTestAppointment : coronaTestAppointments) {
                if (coronaTestAppointment.getDate().getDayOfWeek().toString().equalsIgnoreCase(day)) {
                    LocalTime AppTime = coronaTestAppointment.getDate().toLocalTime();
                    if (AppTime.isBefore(newStart) || AppTime.isAfter(newFinish)) {
                        String messageContent = "Sorry! Your Appointment is canceled due to changing working Hours"
                                + "Your Appointment details: " + coronaTestAppointment.getAppointmentType() + " Appointment at: \n"
                                + "Date: " + coronaTestAppointment.getDate().toLocalDate() + " Time: " + coronaTestAppointment.getDate().toLocalTime().withSecond(0) + "\n"
                                + "At clinic: " + coronaTestAppointment.getClinic().getClinicType();
                        AppointmentData.sendEmail("salehSalsabeel99@gmail.com",messageContent,"Canceled Appointment");
                        //AppointmentData.sendEmail(coronaTestAppointment.getPatient().getEmail(),messageContent,"Canceled Appointment");
                        session.delete(coronaTestAppointment);

                    }
                }
            }
        }
    }
    public static void checkVaccineAppointment(List<VaccineAppointment> vaccineAppointments,String day,LocalTime oldStart, LocalTime newStart,LocalTime oldFinish,LocalTime newFinish) {
        if ((oldStart.isBefore(newStart)) || oldFinish.isAfter(newFinish)) {
            for (VaccineAppointment vaccineAppointment : vaccineAppointments) {
                if (vaccineAppointment.getDate().getDayOfWeek().toString().equalsIgnoreCase(day)) {
                    LocalTime AppTime = vaccineAppointment.getDate().toLocalTime();
                    if (AppTime.isBefore(newStart) || AppTime.isAfter(newFinish)) {
                        String messageContent = "Sorry! Your Appointment is canceled due to changing working Hours\n"
                                + "Your Appointment details: " + vaccineAppointment.getAppointmentType() + " Appointment at: \n"
                                + "Date: " + vaccineAppointment.getDate().toLocalDate() + " Time: " + vaccineAppointment.getDate().toLocalTime().withSecond(0) + "\n"
                                + "At clinic: " + vaccineAppointment.getClinic().getClinicType();
                        AppointmentData.sendEmail("salehSalsabeel99@gmail.com",messageContent,"Canceled Appointment");
                        //AppointmentData.sendEmail(coronaTestAppointment.getPatient().getEmail(),messageContent,"Canceled Appointment");
                        session.delete(vaccineAppointment);
                    }
                }
            }
        }
    }
    public static void checkDoctorAppointment(List<DoctorAppointment> doctorAppointments,String day,LocalTime oldStart, LocalTime newStart,LocalTime oldFinish,LocalTime newFinish) {
        if ((oldStart.isBefore(newStart)) || oldFinish.isAfter(newFinish)) {
            for (DoctorAppointment doctorAppointment : doctorAppointments) {
                if (doctorAppointment.getDate().getDayOfWeek().toString().equalsIgnoreCase(day)) {
                    LocalTime AppTime = doctorAppointment.getDate().toLocalTime();
                    if (AppTime.isBefore(newStart) || AppTime.isAfter(newFinish)) {
                        String messageContent = "Sorry! Your Appointment is canceled due to changing working Hours"
                                + "Your Appointment details: " + doctorAppointment.getAppointmentType() + " Appointment at: \n"
                                + "Date: " + doctorAppointment.getDate().toLocalDate() + " Time: " + doctorAppointment.getDate().toLocalTime().withSecond(0) + "\n"
                                + "At clinic: " + doctorAppointment.getClinic().getClinicType();
                        AppointmentData.sendEmail("salehSalsabeel99@gmail.com", messageContent, "Canceled Appointment");
                        //AppointmentData.sendEmail(coronaTestAppointment.getPatient().getEmail(),messageContent,"Canceled Appointment");
                        session.delete(doctorAppointment);
                    }
                }
            }
        }
    }
}

