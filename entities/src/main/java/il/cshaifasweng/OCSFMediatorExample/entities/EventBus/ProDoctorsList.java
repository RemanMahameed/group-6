package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * this class contains a sorted professional doctor list to show to the patient
 */

public class ProDoctorsList implements Serializable {
    private LinkedList<Doctor> ProDoctors;

    public ProDoctorsList(LinkedList<Doctor> proDoctors) {
        this.ProDoctors = proDoctors;
    }

    public LinkedList<Doctor> getProDoctors() {
        return ProDoctors;
    }

    public void setProDoctors(LinkedList<Doctor> proDoctors) {
        ProDoctors = proDoctors;
    }
}
