package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.orderAllAppsBus;

public class OrderAllAppsBusEvent {
    orderAllAppsBus orderAllAppsBus;

    public OrderAllAppsBusEvent(il.cshaifasweng.OCSFMediatorExample.entities.EventBus.orderAllAppsBus orderAllAppsBus) {
        this.orderAllAppsBus = orderAllAppsBus;
    }

    public OrderAllAppsBusEvent() {
    }

    public orderAllAppsBus getOrderAllAppsBus() {
        return orderAllAppsBus;
    }
}
