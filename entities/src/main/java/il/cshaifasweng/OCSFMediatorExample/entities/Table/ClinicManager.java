package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import javax.persistence.*;


@Entity
@Table(name ="ClinicManager",uniqueConstraints= @UniqueConstraint(columnNames={"PassWord"}))
public class ClinicManager extends Person {

    private boolean openORclose;

    @OneToOne(mappedBy = "clinicManager")
    private Clinic clinic;


    public ClinicManager(String firstName, String lastName, String phoneNum, String email, String userName, String passWord, boolean openORclose) {
        super(firstName, lastName, phoneNum, email, userName, passWord);
        this.openORclose = openORclose;
    }

    public boolean isOpenORclose() {
        return openORclose;
    }

    public void setOpenORclose(boolean openORclose) {
        this.openORclose = openORclose;
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
