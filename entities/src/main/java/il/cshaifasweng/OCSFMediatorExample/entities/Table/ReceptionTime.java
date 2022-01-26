package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;


public class ReceptionTime  {

    String ClinicName;
    LocalTime[][] ActiveTime=new LocalTime[2][7];


    public ReceptionTime(String clinicName, LocalTime[][] activeTime) {
        ClinicName = clinicName;
        ActiveTime = activeTime;
    }

    public ReceptionTime() {
    }

    public String getClinicName() {
        return ClinicName;
    }

    public void setClinicName(String clinicName) {
        ClinicName = clinicName;
    }

    public LocalTime[][] getActiveTime() {
        return ActiveTime;
    }

    public void setActiveTime(LocalTime[][] activeTime) {
        ActiveTime = activeTime;
    }
}
