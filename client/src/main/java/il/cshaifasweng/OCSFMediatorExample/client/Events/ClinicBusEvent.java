package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ClinicBus;

public class ClinicBusEvent {
    ClinicBus clinicBus ;

    public ClinicBusEvent(ClinicBus clinicBus) {
        this.clinicBus = clinicBus;
    }

    public ClinicBus getClinicBus() {
        return clinicBus;
    }
}
