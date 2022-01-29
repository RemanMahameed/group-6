package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;

import java.io.Serializable;
import java.util.List;

public class ScheduledApp implements Serializable {

    List<String> AppString;
    List<DoctorAppointment>doctorAppointments;
    List<NurseAppointment> nurseAppointments;
    List<LaboratoryFactsAppointment> laboratoryFactsAppointments;
    String typeFlag;

    public ScheduledApp(List<String> appString, List<DoctorAppointment> doctorAppointments, String typeFlag) {
        AppString = appString;
        this.doctorAppointments = doctorAppointments;
        this.typeFlag = typeFlag;
    }

    public ScheduledApp(List<String> appString, String typeFlag, List<NurseAppointment> nurseAppointments) {
        AppString = appString;
        this.nurseAppointments = nurseAppointments;
        this.typeFlag = typeFlag;
    }

    public ScheduledApp(String typeFlag,List<String> appString, List<LaboratoryFactsAppointment> laboratoryFactsAppointments) {
        AppString = appString;
        this.laboratoryFactsAppointments = laboratoryFactsAppointments;
        this.typeFlag = typeFlag;
    }

    public ScheduledApp() {
    }

    public List<String> getAppString() {
        return AppString;
    }

    public void setAppString(List<String> appString) {
        AppString = appString;
    }

    public List<DoctorAppointment> getDoctorAppointments() {
        return doctorAppointments;
    }

    public void setDoctorAppointments(List<DoctorAppointment> doctorAppointments) {
        this.doctorAppointments = doctorAppointments;
    }

    public String getTypeFlag() {
        return typeFlag;
    }

    public void setTypeFlag(String typeFlag) {
        this.typeFlag = typeFlag;
    }

    public List<NurseAppointment> getNurseAppointments() {
        return nurseAppointments;
    }

    public void setNurseAppointments(List<NurseAppointment> nurseAppointments) {
        this.nurseAppointments = nurseAppointments;
    }

    public List<LaboratoryFactsAppointment> getLaboratoryFactsAppointments() {
        return laboratoryFactsAppointments;
    }

    public void setLaboratoryFactsAppointments(List<LaboratoryFactsAppointment> laboratoryFactsAppointments) {
        this.laboratoryFactsAppointments = laboratoryFactsAppointments;
    }
}
