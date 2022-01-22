package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;


@Entity
@Table(name ="ClinicManager",uniqueConstraints= @UniqueConstraint(columnNames={"PassWord"}))
public class ClinicManager extends Person {

    @OneToOne(mappedBy = "clinicManager")
    private Clinic clinic;

    public ClinicManager(String firstName, String lastName, String phoneNum, String email, String userName, String passWord) {
        super(firstName, lastName, phoneNum, email, userName, passWord);

    }

    public ClinicManager() {
        super();
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Clinic getClinic() {
        return clinic;
    }
}
