package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import java.io.Serializable;
import java.time.LocalTime;

public class WorkingHours implements Serializable {
    LocalTime[][] activityTime;
    String type;
    String clinicName;

    public WorkingHours() {
    }

    public WorkingHours(LocalTime[][] activityTime, String type, String clinicName) {
        this.activityTime = activityTime;
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
}
