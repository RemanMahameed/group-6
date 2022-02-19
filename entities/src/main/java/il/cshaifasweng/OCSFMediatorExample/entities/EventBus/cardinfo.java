package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;

import java.io.Serializable;

public class cardinfo implements Serializable {
    Patient p ;
    Clinic c ;
    int success ;
    String details;

    public Patient getP() {
        return p;
    }

    public Clinic getC() {
        return c;
    }

    public int getSuccess() {
        return success;
    }

    public String getDetails() {
        return details;
    }

    public cardinfo() {
    }

    public cardinfo(Patient p, Clinic c, int success, String details) {
        this.p = p;
        this.c = c;
        this.success = success;
        this.details = details;
    }
}
