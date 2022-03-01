package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.UserBus;

public class UserBusEvent {
    UserBus userBus ;

    public UserBus getUserBus() {
        return userBus;
    }

    public UserBusEvent(UserBus userBus) {
        this.userBus = userBus;
    }
}
