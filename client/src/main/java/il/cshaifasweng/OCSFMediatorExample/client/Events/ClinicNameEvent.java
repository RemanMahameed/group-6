package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ClinicName;

public class ClinicNameEvent {
    ClinicName clinicName;

    public ClinicNameEvent(ClinicName clinicName) {
        this.clinicName = clinicName;
    }

    public ClinicName getClinicName() {
        return clinicName;
    }
}
