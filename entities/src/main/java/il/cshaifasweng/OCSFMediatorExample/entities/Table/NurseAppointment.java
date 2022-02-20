package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name ="NurseAppointment")
public class NurseAppointment extends Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Appointment_ID")
    protected Long Id;

    @ManyToOne
    @JoinColumn(name = "Nurse_ID")
    private Nurse nurse;

    @ManyToOne
    @JoinColumn(name = "Patient_ID")
    protected Patient patient;

    @ManyToOne
    @JoinColumn(name = "Clinic_ID")
    protected Clinic clinic;

    public NurseAppointment(String appointmentType, LocalDateTime date, Nurse nurse, Patient patient, Clinic clinic) {
        super(appointmentType, date);
        this.nurse = nurse;
        this.patient = patient;
        this.clinic = clinic;
    }

    public NurseAppointment() {
        super();
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
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

    public Long getId() {
        return Id;
    }
}
