package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.DoctorApp;

public class DoctorAppEvent {
    DoctorApp doctorApp;

    public DoctorAppEvent(DoctorApp doctorApp) {
        this.doctorApp = doctorApp;
    }

    public DoctorApp getDoctorApp() {
        return doctorApp;
    }
}
