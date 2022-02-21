package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.DoneAppBus;

public class DoneAppEvent {
    DoneAppBus doneAppBus;

    public DoneAppEvent(DoneAppBus doneAppBus) {
        this.doneAppBus = doneAppBus;
    }

    public DoneAppBus getDoneAppBus() {
        return doneAppBus;
    }
}
