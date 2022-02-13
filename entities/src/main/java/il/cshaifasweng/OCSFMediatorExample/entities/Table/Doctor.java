package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.mapping.Set;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name ="Doctor" ,uniqueConstraints= @UniqueConstraint(columnNames={"PassWord"}))
public class Doctor extends Person {

    private String Role;

//    @ManyToMany(mappedBy = "doctors", cascade = CascadeType.ALL)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<ReceptionTime> receptionTime;
    @OneToMany(mappedBy = "doctor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ReceptionTime> receptionTime;

    @ManyToMany(mappedBy = "doctors", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Clinic> clinicList;

    @ManyToMany(mappedBy = "doctors", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Patient> patients;

    @OneToMany(mappedBy = "doctor")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<DoctorAppointment> appointments;


    public Doctor(String firstName, String lastName, String phoneNum, String email, String userName, String passWord, String role) {
        super(firstName, lastName, phoneNum, email, userName, passWord);
        Role = role;
    }

    public Doctor() {
        super();
    }

    public void setClinicList(List<Clinic> clinicList) {
        this.clinicList = clinicList;
    }

    public List<Clinic> getClinicList() {
        return clinicList;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getRole() {
        return Role;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<DoctorAppointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<DoctorAppointment> appointments) {
        this.appointments = appointments;
    }

    public List<ReceptionTime> getReceptionTime() {
        return receptionTime;
    }

    public void setReceptionTime(List<ReceptionTime> receptionTime) {
        this.receptionTime = receptionTime;
    }
    //saraa remaan nameer
    public ReceptionTime getRecepByClinic(String Clinic){
        ReceptionTime recep =null ;
        for (ReceptionTime element : this.receptionTime) {
            if(element.getClinicName().equals(Clinic))
                return element;
        }
        return recep;
    }
}
