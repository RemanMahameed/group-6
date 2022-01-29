package il.cshaifasweng.OCSFMediatorExample.client.Events;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.UpdateObject;

public class UpdateObjectEvent {
    UpdateObject updateObject;

    public UpdateObjectEvent(UpdateObject updateObject) {
        this.updateObject = updateObject;
    }

    public UpdateObject getUpdateObject() {
        return updateObject;
    }
}
