package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;

import java.io.Serializable;

public class UpdateObject implements Serializable {
    Object object;

    public UpdateObject() {
    }

    public UpdateObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
