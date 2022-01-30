package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.FreeAppointment;

public class FreeAppointmentEvent {
    FreeAppointment freeAppointment;

    public FreeAppointmentEvent(FreeAppointment freeAppointment) {
        this.freeAppointment = freeAppointment;
    }

    public FreeAppointment getFreeAppointment() {
        return freeAppointment;
    }
}
