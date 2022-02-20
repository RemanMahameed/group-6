package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.AppNum;

public class AppNumEvent {
    AppNum appNum;

    public AppNumEvent(AppNum appNum) {
        this.appNum = appNum;
    }

    public AppNum getAppNum() {
        return appNum;
    }
}
