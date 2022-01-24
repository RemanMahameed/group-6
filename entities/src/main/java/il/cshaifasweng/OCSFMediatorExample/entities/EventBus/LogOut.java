package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import java.io.Serializable;

public class LogOut implements Serializable {
    private String message;

    public LogOut(String message) {
        this.message = message;
    }

    public LogOut() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
