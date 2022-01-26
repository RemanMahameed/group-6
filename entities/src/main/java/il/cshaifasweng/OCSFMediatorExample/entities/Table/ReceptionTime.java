package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name ="ReceptionTime")
public class ReceptionTime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReceptionTime_id")
    private int Id;
    String ClinicName;
    LocalTime[][] ActiveTime=new LocalTime[2][7];

    @ManyToMany
    @JoinTable(name = "DoctorReceptionTime")
    private List<Doctor> doctors;

    public ReceptionTime(String clinicName, LocalTime[][] activeTime) {
        ClinicName = clinicName;
        ActiveTime = activeTime;
    }

    public ReceptionTime() {
    }

    public int getId() {
        return Id;
    }

    public String getClinicName() {
        return ClinicName;
    }

    public void setClinicName(String clinicName) {
        ClinicName = clinicName;
    }

    public LocalTime[][] getActiveTime() {
        return ActiveTime;
    }

    public void setActiveTime(LocalTime[][] activeTime) {
        ActiveTime = activeTime;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }
}
