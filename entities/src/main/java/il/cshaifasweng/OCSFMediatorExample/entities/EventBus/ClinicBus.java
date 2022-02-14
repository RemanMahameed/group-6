package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.Clinic;

import java.io.Serializable;

public class ClinicBus implements Serializable {
    Clinic clinic ;

   public ClinicBus(){

   }

    public ClinicBus(Clinic clinic) {
        this.clinic = clinic;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
