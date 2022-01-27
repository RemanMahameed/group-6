package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name ="DoctorAppointment")
public class DoctorAppointment extends Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Appointment_ID")
    protected Long Id;
    @ManyToOne
    @JoinColumn(name = "Doctor_ID")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "Patient_ID")
    protected Patient patient;

    @ManyToOne
    @JoinColumn(name = "Clinic_ID")
    protected Clinic clinic;


    public DoctorAppointment(String appointmentType, LocalDateTime date, Doctor doctor, Patient patient, Clinic clinic) {
        super(appointmentType, date);
        this.doctor = doctor;
        this.patient = patient;
        this.clinic = clinic;
    }

    public DoctorAppointment() {
        super();
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
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
