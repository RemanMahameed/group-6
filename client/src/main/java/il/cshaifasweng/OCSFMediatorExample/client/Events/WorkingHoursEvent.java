package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.WorkingHours;

public class WorkingHoursEvent {
    WorkingHours workingHours;

    public WorkingHoursEvent(WorkingHours workingHours) {
        this.workingHours = workingHours;
    }

    public WorkingHours getWorkingHours() {
        return workingHours;
    }
}
