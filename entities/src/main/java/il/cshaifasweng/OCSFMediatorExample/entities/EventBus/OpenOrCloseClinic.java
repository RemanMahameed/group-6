package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import java.io.Serializable;

public class OpenOrCloseClinic implements Serializable {
    String clinicName;
    boolean openOrclose;

    public OpenOrCloseClinic() {
    }

    public OpenOrCloseClinic(String clinicName, boolean openOrclose) {
        this.clinicName = clinicName;
        this.openOrclose = openOrclose;
    }

    public boolean isOpenOrclose() {
        return openOrclose;
    }

    public void setOpenOrclose(boolean openOrclose) {
        this.openOrclose = openOrclose;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
}
