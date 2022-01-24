package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "Clinic")
public class Clinic {
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
    @JoinTable(name = "Clinic_Doctors",joinColumns = @JoinColumn(name = "Clinic_ID"),inverseJoinColumns = {@JoinColumn(name = "Doctor_ID")})
    private List<Doctor> doctors;

    @ManyToMany
    @JoinTable(name = "Clinic_Nurse",joinColumns = @JoinColumn(name = "Clinic_ID"),inverseJoinColumns = {@JoinColumn(name = "Nurse_ID")})
    private List<Nurse> nurses;

    @ManyToMany
    @JoinTable(name = "Clinic_LaboratoryFacts",joinColumns = @JoinColumn(name = "Clinic_ID"),inverseJoinColumns = {@JoinColumn(name = "LaboratoryFacts_ID")})
    private List<LaboratoryFacts> laboratoryFacts;

    @OneToMany(mappedBy = "clinic")
    private List<LaboratoryFactsAppointment> laboratoryFactsAppointments;

    @OneToMany(mappedBy = "clinic")
    private List<NurseAppointment> nurseAppointments;

    @OneToMany(mappedBy = "clinic")
    private List<DoctorAppointment> doctorAppointments;

    @OneToMany(mappedBy = "clinic")
    private List<Patient> patients;

    public Clinic(String clinicType, LocalTime[][] activityTime, LocalTime[][] coronaTestTime, LocalTime[][] vaccineTime, ClinicManager clinicManager, HMO hmo) {
        ClinicType = clinicType;
        ActivityTime = activityTime;
        CoronaTestTime = coronaTestTime;
        VaccineTime = vaccineTime;
        this.clinicManager = clinicManager;
        this.hmo = hmo;
    }

    public Clinic() {
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
}

