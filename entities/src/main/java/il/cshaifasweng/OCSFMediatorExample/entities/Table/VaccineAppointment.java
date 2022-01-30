package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import javax.persistence.*;
import java.time.LocalDateTime;

public class VaccineAppointment extends Appointment {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Appointment_ID")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "Patient_ID")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "Clinic_ID")
    private Clinic clinic;

    public VaccineAppointment() {
        super();
    }

    public VaccineAppointment(String appointmentType, LocalDateTime date, Patient patient, Clinic clinic) {
        super(appointmentType, date);
        this.patient = patient;
        this.clinic = clinic;
    }

    public Long getId() {
        return Id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }
}
