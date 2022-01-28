package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.LogOut;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.Login;

public class LogOutEvent {
    LogOut logOut;

    public LogOut getLogOut() {
        return logOut;
    }

    public LogOutEvent(LogOut logOut) {
        this.logOut = logOut;
    }
}
