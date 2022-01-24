package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name ="LaboratoryFactsAppointment")
public class LaboratoryFactsAppointment extends Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Appointment_ID")
    protected Long Id;
    @ManyToOne
    @JoinColumn(name = "Doctor_ID")
    private LaboratoryFacts laboratoryFacts;

    @ManyToOne
    @JoinColumn(name = "Patient_ID")
    protected Patient patient;

    @ManyToOne
    @JoinColumn(name = "Clinic_ID")
    protected Clinic clinic;

    public LaboratoryFactsAppointment(int appNum, String appointmentType, LocalDateTime date, LocalTime realTime, boolean available, boolean done, LaboratoryFacts laboratoryFacts, Patient patient, Clinic clinic) {
        super(appNum, appointmentType, date, realTime, available, done);
        this.laboratoryFacts = laboratoryFacts;
        this.patient = patient;
        this.clinic = clinic;
    }

    public LaboratoryFactsAppointment() {
        super();
    }

    public LaboratoryFacts getLaboratoryFacts() {
        return laboratoryFacts;
    }

    public void setLaboratoryFacts(LaboratoryFacts laboratoryFacts) {
        this.laboratoryFacts = laboratoryFacts;
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
