package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ProDoctorsList;

public class ProEvent {
    private ProDoctorsList ProDoctors;

    public ProDoctorsList getProDoctors() {
        return ProDoctors;
    }

    public ProEvent(ProDoctorsList proDoctors) {
        this.ProDoctors = proDoctors;
    }
}
