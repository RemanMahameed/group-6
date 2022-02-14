package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name ="Patient",uniqueConstraints= @UniqueConstraint(columnNames={"PassWord"}))
public class Patient extends Person {
    private int age;
    private boolean IsRecovery;
    private long card;
    private int numOfVaccine;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Patient_laboratoryFacts",joinColumns = @JoinColumn(name = "Patient_ID"),inverseJoinColumns = {@JoinColumn(name = "laboratoryFacts_ID")})
    private List<LaboratoryFacts> laboratoryFacts;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Patient_Nurse",joinColumns = @JoinColumn(name = "Patient_ID"),inverseJoinColumns = {@JoinColumn(name = "Nurse_ID")})
    private List<Nurse> nurses;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Patient_Doctor",joinColumns = @JoinColumn(name = "Patient_ID"),inverseJoinColumns = {@JoinColumn(name = "Doctor_ID")})
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "patient")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<LaboratoryFactsAppointment> laboratoryFactsAppointments;

    @OneToMany(mappedBy = "patient")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<NurseAppointment> nurseAppointments;

    @OneToMany(mappedBy = "patient")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DoctorAppointment> doctorAppointments;

    @OneToMany(mappedBy = "clinic")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<VaccineAppointment> vaccineAppointments;

    @OneToMany(mappedBy = "clinic")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CoronaTestAppointment> coronaTestAppointments;

    @ManyToOne
    @JoinColumn(name = "Clinic_ID")
    protected Clinic clinic;

    public Patient(String firstName, String lastName, String phoneNum, String email, String userName, String passWord, int age, boolean isRecovery, long card) {
        super(firstName, lastName, phoneNum, email, userName, passWord);
        this.age = age;
        IsRecovery = isRecovery;
        this.card = card;
        numOfVaccine=0;
        laboratoryFacts=new LinkedList<>();
        nurses=new LinkedList<>();
        doctors=new LinkedList<>();
        laboratoryFactsAppointments=new LinkedList<>();
        nurseAppointments=new LinkedList<>();
        doctorAppointments=new LinkedList<>();
        vaccineAppointments=new LinkedList<>();
        coronaTestAppointments=new LinkedList<>();
    }

    public Patient() {
        super();
        laboratoryFacts=new LinkedList<>();
        nurses=new LinkedList<>();
        doctors=new LinkedList<>();
        laboratoryFactsAppointments=new LinkedList<>();
        nurseAppointments=new LinkedList<>();
        doctorAppointments=new LinkedList<>();
        vaccineAppointments=new LinkedList<>();
        coronaTestAppointments=new LinkedList<>();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isRecovery() {
        return IsRecovery;
    }

    public void setRecovery(boolean recovery) {
        IsRecovery = recovery;
    }

    public List<LaboratoryFacts> getLaboratoryFacts() {
        return laboratoryFacts;
    }

    public void setLaboratoryFacts(List<LaboratoryFacts> laboratoryFacts) {
        this.laboratoryFacts = laboratoryFacts;
    }

    public void AddLaboratoryFacts(LaboratoryFacts laboratoryFact){
        if(this.laboratoryFacts.size()==0){
            List<LaboratoryFacts> firstLaboratoryfact=new LinkedList<>();
            firstLaboratoryfact.add(laboratoryFact);
            setLaboratoryFacts(firstLaboratoryfact);
        }
        else
            this.laboratoryFacts.add(laboratoryFact);
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public void AddNurse(Nurse nurse){
        if(this.nurses.size()==0){
            List<Nurse> firstNurse=new LinkedList<>();
            firstNurse.add(nurse);
            setNurses(firstNurse);
        }
        else
            this.nurses.add(nurse);
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void AddDoctor(Doctor doctor){
        if(this.doctors.size()==0){
            List<Doctor> firstDoctor=new LinkedList<>();
            firstDoctor.add(doctor);
            setDoctors(firstDoctor);
        }
        else
            this.doctors.add(doctor);
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

    public List<VaccineAppointment> getVaccineAppointments() {
        return vaccineAppointments;
    }

    public void setVaccineAppointments(List<VaccineAppointment> vaccineAppointments) {
        this.vaccineAppointments = vaccineAppointments;
    }

    public List<CoronaTestAppointment> getCoronaTestAppointments() {
        return coronaTestAppointments;
    }

    public void setCoronaTestAppointments(List<CoronaTestAppointment> coronaTestAppointments) {
        this.coronaTestAppointments = coronaTestAppointments;
    }
}

