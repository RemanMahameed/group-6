package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.LinkedList;
import java.util.List;


import javax.persistence.*;


@Entity
@Table(name ="Nurse",uniqueConstraints= @UniqueConstraint(columnNames={"PassWord"}))
public class Nurse extends Person {

    @ManyToMany(mappedBy = "nurses", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Clinic> clinicList;

    @ManyToMany(mappedBy = "nurses", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Patient> patients;

    @OneToMany(mappedBy = "nurse")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<NurseAppointment> appointments;

    public Nurse(String firstName, String lastName, String phoneNum, String email, String userName, String passWord) {
        super(firstName, lastName, phoneNum, email, userName, passWord);

    }

    public Nurse() {
        super();
    }

    public void setClinicList(List<Clinic> clinicList) {
        this.clinicList = clinicList;
    }

    public List<Clinic> getClinicList() {
        return clinicList;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void AddPatient(Patient patient){
        if(this.patients.size()==0){
            List<Patient> firstPatient=new LinkedList<>();
            firstPatient.add(patient);
            setPatients(firstPatient);
        }
        else
            this.patients.add(patient);
    }

    public List<NurseAppointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<NurseAppointment> appointments) {
        this.appointments = appointments;
    }
}


