package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ScheduledApp;

public class ScheduledAppEvent {

    ScheduledApp scheduledApp;

    public ScheduledAppEvent(ScheduledApp scheduledApp) {
        this.scheduledApp = scheduledApp;
    }

    public ScheduledApp getScheduledApp() {
        return scheduledApp;
    }
}
