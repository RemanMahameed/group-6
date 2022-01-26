package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name ="Doctor" ,uniqueConstraints= @UniqueConstraint(columnNames={"PassWord"}))
public class Doctor extends Person {

    private String Role;
    private  LocalTime[][] ActivityTime=new LocalTime[2][7];

    @ManyToMany(mappedBy = "doctors", cascade = CascadeType.ALL)
    private List<Clinic> clinicList;

    @ManyToMany(mappedBy = "doctors", cascade = CascadeType.ALL)
    private List<Patient> patients;

    @OneToMany(mappedBy = "doctor")
    private List<DoctorAppointment> appointments;

    public Doctor(String firstName, String lastName, String phoneNum, String email, String userName, String passWord, String role, LocalTime[][] activityTime) {
        super(firstName, lastName, phoneNum, email, userName, passWord);
        Role = role;
        ActivityTime = activityTime;
    }

    public Doctor() {
        super();
        clinicList=new LinkedList<>();
        patients=new LinkedList<>();
        appointments=new LinkedList<>();
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

    public LocalTime[][] getActivityTime() {
        return ActivityTime;
    }

    public void setActivityTime(LocalTime[][] activityTime) {
        ActivityTime = activityTime;
    }
}
