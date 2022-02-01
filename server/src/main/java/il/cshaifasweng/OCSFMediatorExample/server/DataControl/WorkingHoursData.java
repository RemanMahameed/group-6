package il.cshaifasweng.OCSFMediatorExample.server.DataControl;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.DoctorNames;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.WorkingHours;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.ReceptionTime;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class WorkingHoursData extends DataClass {

    public static void getWorkingHours(WorkingHours workingHours, String Name, String type) throws Exception {
        switch (type) {
            case ("Clinic"):
                getClinicWorkingHours(workingHours, Name, type);
                break;
            case ("Clinic's Doctor"):
                getDoctorWorkingHours(workingHours, Name, type);
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
        workingHours.setType(type);
    }

    public static void getCoronaTestWorkingHours(WorkingHours workingHours, String clinicName, String type) throws Exception {
        Clinic clinic = getClinicByName(clinicName);
        LocalTime[][] activityTime = clinic.getCoronaTestTime();
        workingHours.setActiveTime(activityTime);
        workingHours.setType(type);
    }

    public static void getVaccineWorkingHours(WorkingHours workingHours, String clinicName, String type) throws Exception {
        Clinic clinic = getClinicByName(clinicName);
        LocalTime[][] activityTime = clinic.getCoronaTestTime();
        workingHours.setActiveTime(activityTime);
        workingHours.setType(type);
    }
    public static void getDoctorWorkingHours(WorkingHours workingHours, String StringDoctorID, String type) throws Exception {
        int doctorId=Integer.parseInt(StringDoctorID);
        Doctor doctor = getDoctorById(doctorId);
        List<ReceptionTime> receptionTime= doctor.getReceptionTime();
        workingHours.setActiveTime(receptionTime.get(0).getActiveTime());
        workingHours.setType(type);
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
    }
}

