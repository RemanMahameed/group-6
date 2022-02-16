package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name ="LaboratoryFacts",uniqueConstraints= @UniqueConstraint(columnNames={"PassWord"}))
public class LaboratoryFacts extends Person {

    @ManyToMany(mappedBy = "laboratoryFacts", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Clinic> clinicList;

    @ManyToMany(mappedBy = "laboratoryFacts", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Patient> patients;

    @OneToMany(mappedBy = "laboratoryFacts")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<LaboratoryFactsAppointment> appointments;

    public LaboratoryFacts(String firstName, String lastName, String phoneNum, String email, String userName, String passWord) {
        super(firstName, lastName, phoneNum, email, userName, passWord);
    }

    public LaboratoryFacts() {
        super();
    }

    public List<Clinic> getClinicList() {
        return clinicList;
    }

    public void setClinicList(List<Clinic> clinicList) {
        this.clinicList = clinicList;
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

    public List<LaboratoryFactsAppointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<LaboratoryFactsAppointment> appointments) {
        this.appointments = appointments;
    }
}
