package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import java.io.Serializable;
import java.time.LocalTime;

public class WorkingHours implements Serializable {
    LocalTime[][] activityTime;
    LocalTime[][] clinicActivityTime;
    int doctorId;
    String type;
    String clinicName;

    public WorkingHours() {
    }

    public WorkingHours(LocalTime[][] activityTime, LocalTime[][] clinicActivityTime, String type, String clinicName) {
        this.activityTime = activityTime;
        this.clinicActivityTime = clinicActivityTime;
        this.type = type;
        this.clinicName = clinicName;
    }

    public LocalTime[][] getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(LocalTime[][] activityTime) {
        this.activityTime = activityTime;
    }

    public void setActiveTime(LocalTime[][] activeTime) {
        this.activityTime = activeTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public LocalTime[][] getClinicActivityTime() {
        return clinicActivityTime;
    }

    public void setClinicActivityTime(LocalTime[][] clinicActivityTime) {
        this.clinicActivityTime = clinicActivityTime;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
}
