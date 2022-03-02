package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.CoronaTestAppointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.VaccineAppointment;

import java.io.Serializable;
import java.util.List;

/**
 * this class contains free appointments
 */

public class FreeAppointment implements Serializable {
    String type;
    int clinicId;
    List<String> DetailsApp;
    List<CoronaTestAppointment> coronaTestAppointments;
    List<VaccineAppointment> vaccineAppointments;

    public FreeAppointment() {
    }

    public FreeAppointment(String type, int clinicId, List<String> detailsApp, List<CoronaTestAppointment> coronaTestAppointments) {
        this.type = type;
        this.clinicId = clinicId;
        DetailsApp = detailsApp;
        this.coronaTestAppointments = coronaTestAppointments;
    }

    public FreeAppointment(List<VaccineAppointment> vaccineAppointments,String type, int clinicId, List<String> detailsApp) {
        this.type = type;
        this.clinicId = clinicId;
        DetailsApp = detailsApp;
        this.vaccineAppointments = vaccineAppointments;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getClinicId() {
        return clinicId;
    }

    public void setClinicId(int clinicId) {
        this.clinicId = clinicId;
    }

    public List<CoronaTestAppointment> getCoronaTestAppointments() {
        return coronaTestAppointments;
    }

    public void setCoronaTestAppointments(List<CoronaTestAppointment> coronaTestAppointments) {
        this.coronaTestAppointments = coronaTestAppointments;
    }

    public List<VaccineAppointment> getVaccineAppointments() {
        return vaccineAppointments;
    }

    public void setVaccineAppointments(List<VaccineAppointment> vaccineAppointments) {
        this.vaccineAppointments = vaccineAppointments;
    }

    public List<String> getDetailsApp() {
        return DetailsApp;
    }

    public void setDetailsApp(List<String> detailsApp) {
        DetailsApp = detailsApp;
    }
}
