package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.DoctorAppointment;

import java.io.Serializable;
import java.util.List;

public class orderAllAppsBus implements Serializable {
    String type;
    List<DoctorAppointment> DApp;
    List<Object> NApp;
    List<Object> LabApp;

    public orderAllAppsBus() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DoctorAppointment> getDApp() {
        return DApp;
    }

    public void setDApp(List<DoctorAppointment> DApp) {
        this.DApp = DApp;
    }

    public List<Object> getNApp() {
        return NApp;
    }

    public void setNApp(List<Object> NApp) {
        this.NApp = NApp;
    }

    public List<Object> getLabApp() {
        return LabApp;
    }

    public void setLabApp(List<Object> labApp) {
        LabApp = labApp;
    }
}
