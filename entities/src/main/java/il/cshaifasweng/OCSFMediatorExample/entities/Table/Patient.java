package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import javax.persistence.*;

import java.util.List;


@Entity
@Table(name ="Patient",uniqueConstraints= @UniqueConstraint(columnNames={"PassWord"}))
public class Patient extends Person {
    private int age;
    private boolean GreenPass;
    private long card;
    private int numOfVaccine;

    @ManyToMany
    @JoinTable(name = "Patient_laboratoryFacts",joinColumns = @JoinColumn(name = "Patient_ID"),inverseJoinColumns = {@JoinColumn(name = "laboratoryFacts_ID")})
    private List<LaboratoryFacts> laboratoryFacts;

    @ManyToMany
    @JoinTable(name = "Patient_Nurse",joinColumns = @JoinColumn(name = "Patient_ID"),inverseJoinColumns = {@JoinColumn(name = "Nurse_ID")})
    private List<Nurse> nurses;

    @ManyToMany
    @JoinTable(name = "Patient_Doctor",joinColumns = @JoinColumn(name = "Patient_ID"),inverseJoinColumns = {@JoinColumn(name = "Doctor_ID")})
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "patient")
    private List<LaboratoryFactsAppointment> laboratoryFactsAppointments;

    @OneToMany(mappedBy = "patient")
    private List<NurseAppointment> nurseAppointments;

    @OneToMany(mappedBy = "patient")
    private List<DoctorAppointment> doctorAppointments;

    @ManyToOne
    @JoinColumn(name = "Clinic_ID")
    protected Clinic clinic;

    public Patient(String firstName, String lastName, String phoneNum, String email, String userName, String passWord, int age, long card) {
        super(firstName, lastName, phoneNum, email, userName, passWord);
        this.age = age;
        GreenPass = false;
        this.card = card;
        numOfVaccine = 0;
    }

    public Patient() {
        super();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGreenPass() {
        return GreenPass;
    }

    public void setGreenPass(boolean greenPass) {
        GreenPass = greenPass;
    }

    public List<LaboratoryFacts> getLaboratoryFacts() {
        return laboratoryFacts;
    }

    public void setLaboratoryFacts(List<LaboratoryFacts> laboratoryFacts) {
        this.laboratoryFacts = laboratoryFacts;
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<LaboratoryFactsAppointment> getLaboratoryFactsAppointments() {
        return laboratoryFactsAppointments;
    }

    public void setLaboratoryFactsAppointments(List<LaboratoryFactsAppointment> laboratoryFactsAppointments) {
        this.laboratoryFactsAppointments = laboratoryFactsAppointments;
    }

    public List<NurseAppointment> getNurseAppointments() {
        return nurseAppointments;
    }

    public void setNurseAppointments(List<NurseAppointment> nurseAppointments) {
        this.nurseAppointments = nurseAppointments;
    }

    public List<DoctorAppointment> getDoctorAppointments() {
        return doctorAppointments;
    }

    public void setDoctorAppointments(List<DoctorAppointment> doctorAppointments) {
        this.doctorAppointments = doctorAppointments;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public long getCard() {
        return card;
    }

    public void setCard(long card) {
        this.card = card;
    }

    public int getNumOfVaccine() {
        return numOfVaccine;
    }

    public void setNumOfVaccine(int numOfVaccine) {
        this.numOfVaccine = numOfVaccine;
    }
}

