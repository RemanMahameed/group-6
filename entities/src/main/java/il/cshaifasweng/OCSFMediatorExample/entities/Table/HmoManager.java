package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import javax.persistence.*;


@Entity
@Table(name ="HmoManager")
public class HmoManager extends Person {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "HmoManager_Id")
    //private int Id;

   @OneToOne(mappedBy = "hmoManager")
   private HMO hmo;

    public HmoManager(String firstName, String lastName, String phoneNum, String email, String userName, String passWord) {
        super(firstName, lastName, phoneNum, email, userName, passWord);
    }

    public HmoManager() {
    }

    public HMO getHmo() {
        return hmo;
    }

    public void setHmo(HMO hmo) {
        this.hmo = hmo;
    }
}
