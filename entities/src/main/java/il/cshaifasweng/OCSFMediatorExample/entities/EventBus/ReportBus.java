package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.Clinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.ClinicManager;

import java.io.Serializable;

public class ReportBus implements Serializable{
    Clinic clinic ;
    ClinicManager manager;
    String report_type;
    String User_type;

    public ReportBus() {
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }

    public String getUser_type() {
        return User_type;
    }

    public void setUser_type(String user_type) {
        User_type = user_type;
    }

    public ClinicManager getManager() {
        return manager;
    }

    public void setManager(ClinicManager manager) {
        this.manager = manager;
    }
}
