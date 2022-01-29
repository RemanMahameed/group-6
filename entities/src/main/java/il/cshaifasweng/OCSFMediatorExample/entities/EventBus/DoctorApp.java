package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.DoctorAppointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;

import java.io.Serializable;
import java.util.List;

public class DoctorApp implements Serializable {

    List<String> doctorAppString;
    List<DoctorAppointment>doctorAppointments;
    Patient patient;
    Doctor doctor;

    public DoctorApp() {
    }

    public DoctorApp(List<String> doctorAppString, List<DoctorAppointment> doctorAppointments, Patient patient, Doctor doctor) {
        this.doctorAppString = doctorAppString;
        this.doctorAppointments = doctorAppointments;
        this.patient = patient;
        this.doctor = doctor;
    }

    public List<String> getDoctorAppString() {
        return doctorAppString;
    }

    public void setDoctorAppString(List<String> doctorAppString) {
        this.doctorAppString = doctorAppString;
    }

    public List<DoctorAppointment> getDoctorAppointments() {
        return doctorAppointments;
    }

    public void setDoctorAppointments(List<DoctorAppointment> doctorAppointments) {
        this.doctorAppointments = doctorAppointments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
