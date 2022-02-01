package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.DoctorNames;

public class DoctorNameEvent {
    DoctorNames doctorNames;

    public DoctorNameEvent(DoctorNames doctorNames) {
        this.doctorNames = doctorNames;
    }

    public DoctorNames getDoctorNames() {
        return doctorNames;
    }
}
