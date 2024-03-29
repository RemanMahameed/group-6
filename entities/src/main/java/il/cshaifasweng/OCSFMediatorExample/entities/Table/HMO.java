package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "HMO")
public class HMO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HMO_ID")
    private Long Id;
    private String name;

    @OneToOne
    @JoinColumn(name = "HMmoManager_ID")
    private HmoManager hmoManager;

    @OneToMany(mappedBy = "hmo")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Clinic> clinics = new ArrayList<>();

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

   public HmoManager getHmoManager() {
       return hmoManager;
   }

   public void setHmoManager(HmoManager hmoManager) {
       this.hmoManager = hmoManager;
   }
}
