package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.cardinfo;

public class CardInfoEvent {
    cardinfo inf ;

    public cardinfo getInf() {
        return inf;
    }

    public CardInfoEvent(cardinfo inf) {
        this.inf = inf;
    }
}
