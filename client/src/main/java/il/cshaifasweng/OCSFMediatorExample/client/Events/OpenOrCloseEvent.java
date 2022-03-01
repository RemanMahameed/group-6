package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.OpenOrCloseClinic;

public class OpenOrCloseEvent {
    OpenOrCloseClinic openOrCloseClinic;

    public OpenOrCloseEvent(OpenOrCloseClinic openOrCloseClinic) {
        this.openOrCloseClinic = openOrCloseClinic;
    }

    public OpenOrCloseClinic getOpenOrCloseClinic() {
        return openOrCloseClinic;
    }
}
