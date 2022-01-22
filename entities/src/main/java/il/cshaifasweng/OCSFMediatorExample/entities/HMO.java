package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "HMO")
public class HMO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HMO_ID")
    private Long Id;
    private String name;
    @OneToMany(mappedBy = "hmo")
    private List<Clinic> clinics = new ArrayList<>();

    //@OneToOne
    ///@JoinColumns({@JoinColumn(name = "HMmoManager_ID")})
    //private HmoManager hmoManager;

    public HMO(String name) {
        this.name = name;
    }

    public HMO() {
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public List<Clinic> getClinics() {
        return clinics;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClinics(List<Clinic> clinics) {
        this.clinics = clinics;
    }

   //public HmoManager getHmoManager() {
   //    return hmoManager;
   //}

   //public void setHmoManager(HmoManager hmoManager) {
   //    this.hmoManager = hmoManager;
   //}
}
