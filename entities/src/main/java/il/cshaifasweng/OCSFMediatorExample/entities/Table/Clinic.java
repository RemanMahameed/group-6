package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "Clinic")

public class Clinic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Clinic_id")
    private int Id;
    private String ClinicType;
    private LocalTime[][] ActivityTime=new LocalTime[2][7];
    private LocalTime[][] CoronaTestTime=new LocalTime[2][7];
    private LocalTime[][] VaccineTime=new LocalTime[2][7];
    //private LinkedList<String> Department;
    //private LinkedList<Report> Report;

    @OneToOne
    @JoinColumn(name = "ClinicManager_ID")
    private ClinicManager clinicManager;

    @ManyToOne
    @JoinColumn(name = "HMO_ID")
    private HMO hmo;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Clinic_Doctors",joinColumns = @JoinColumn(name = "Clinic_ID"),inverseJoinColumns = {@JoinColumn(name = "Doctor_ID")})
    private List<Doctor> doctors;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Clinic_Nurse",joinColumns = @JoinColumn(name = "Clinic_ID"),inverseJoinColumns = {@JoinColumn(name = "Nurse_ID")})
    private List<Nurse> nurses;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Clinic_LaboratoryFacts",joinColumns = @JoinColumn(name = "Clinic_ID"),inverseJoinColumns = {@JoinColumn(name = "LaboratoryFacts_ID")})
    private List<LaboratoryFacts> laboratoryFacts;

    @OneToMany(mappedBy = "clinic")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<LaboratoryFactsAppointment> laboratoryFactsAppointments;

    @OneToMany(mappedBy = "clinic")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<NurseAppointment> nurseAppointments;

    @OneToMany(mappedBy = "clinic")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DoctorAppointment> doctorAppointments;

    @OneToMany(mappedBy = "clinic")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<VaccineAppointment> vaccineAppointments;

    @OneToMany(mappedBy = "clinic")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CoronaTestAppointment> coronaTestAppointments;

    @OneToMany(mappedBy = "clinic")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Patient> patients;


    public Clinic(String clinicType, LocalTime[][] activityTime, LocalTime[][] coronaTestTime, LocalTime[][] vaccineTime, ClinicManager clinicManager, HMO hmo) {
        ClinicType = clinicType;
        ActivityTime = activityTime;
        CoronaTestTime = coronaTestTime;
        VaccineTime = vaccineTime;
        this.clinicManager = clinicManager;
        this.hmo = hmo;
        laboratoryFacts=new LinkedList<>();
        nurses=new LinkedList<>();
        doctors=new LinkedList<>();
        patients=new LinkedList<>();
        laboratoryFactsAppointments=new LinkedList<>();
        nurseAppointments=new LinkedList<>();
        doctorAppointments=new LinkedList<>();
        vaccineAppointments=new LinkedList<>();
        coronaTestAppointments=new LinkedList<>();
    }

    public Clinic() {
        super();
        laboratoryFacts=new LinkedList<>();
        nurses=new LinkedList<>();
        doctors=new LinkedList<>();
        patients=new LinkedList<>();
        laboratoryFactsAppointments=new LinkedList<>();
        nurseAppointments=new LinkedList<>();
        doctorAppointments=new LinkedList<>();
        vaccineAppointments=new LinkedList<>();
        coronaTestAppointments=new LinkedList<>();
    }

    public int getId() {
        return Id;
    }

    public String getClinicType() {
        return ClinicType;
    }

    public void setClinicType(String clinicType) {
        ClinicType = clinicType;
    }

    public LocalTime[][] getActivityTime() {
        return ActivityTime;
    }

    public void setActivityTime(LocalTime[][] activityTime) {
        ActivityTime = activityTime;
    }

    public LocalTime[][] getCoronaTestTime() {
        return CoronaTestTime;
    }

    public void setCoronaTestTime(LocalTime[][] coronaTestTime) {
        CoronaTestTime = coronaTestTime;
    }

    public LocalTime[][] getVaccineTime() {
        return VaccineTime;
    }

    public void setVaccineTime(LocalTime[][] vaccineTime) {
        VaccineTime = vaccineTime;
    }

    public ClinicManager getClinicManager() {
        return clinicManager;
    }

    public void setClinicManager(ClinicManager clinicManager) {
        this.clinicManager = clinicManager;
    }

    public HMO getHmo() {
        return hmo;
    }

    public void setHmo(HMO hmo) {
        this.hmo = hmo;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public List<LaboratoryFacts> getLaboratoryFacts() {
        return laboratoryFacts;
    }

    public void setLaboratoryFacts(List<LaboratoryFacts> laboratoryFacts) {
        this.laboratoryFacts = laboratoryFacts;
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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
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

