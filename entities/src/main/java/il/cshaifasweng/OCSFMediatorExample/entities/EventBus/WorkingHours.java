package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import java.io.Serializable;
import java.time.LocalTime;

public class WorkingHours implements Serializable {
    LocalTime[][] activityTime;
    String type;

    public WorkingHours() {
    }

    public WorkingHours(LocalTime[][] activeTime, String type) {
        this.activityTime = activityTime;
        this.type = type;
    }

    public LocalTime[][] getActivityTime() {
        return activityTime;
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
}
