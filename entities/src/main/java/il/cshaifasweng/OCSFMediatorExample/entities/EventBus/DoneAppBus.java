package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.LaboratoryFacts;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Nurse;

import java.io.Serializable;

public class DoneAppBus implements Serializable {
    Doctor doctor;
    Nurse nurse;
    LaboratoryFacts labFact;
    String patientISHere="";

    public DoneAppBus() {
    }

    public DoneAppBus(Doctor doctor, Nurse nurse, LaboratoryFacts labFact, String patientISHere) {
        this.doctor = doctor;
        this.nurse = nurse;
        this.labFact = labFact;
        this.patientISHere = patientISHere;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public LaboratoryFacts getLabFact() {
        return labFact;
    }

    public void setLabFact(LaboratoryFacts labFact) {
        this.labFact = labFact;
    }

    public String getPatientISHere() {
        return patientISHere;
    }

    public void setPatientISHere(String patientISHere) {
        this.patientISHere = patientISHere;
    }
}
