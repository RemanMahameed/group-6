package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import java.io.Serializable;
import java.util.List;

public class DoctorNames implements Serializable {
    private List<String> DoctorsFirstName;
    private List<String> DoctorsLastName;
    private List<Integer> DoctorsId;
    private String flag; //to Know why we need  name of Doctors clinic
    private String clinicName;

    public DoctorNames() {
    }


    public DoctorNames(List<String> doctorsFirstName, List<String> doctorsLastName, List<Integer> doctorsId, String flag, String clinicName) {
        DoctorsFirstName = doctorsFirstName;
        DoctorsLastName = doctorsLastName;
        DoctorsId = doctorsId;
        this.flag = flag;
        this.clinicName = clinicName;
    }

    public List<String> getDoctorsFirstName() {
        return DoctorsFirstName;
    }

    public void setDoctorsFirstName(List<String> doctorsFirstName) {
        DoctorsFirstName = doctorsFirstName;
    }

    public List<String> getDoctorsLastName() {
        return DoctorsLastName;
    }

    public void setDoctorsLastName(List<String> doctorsLastName) {
        DoctorsLastName = doctorsLastName;
    }

    public List<Integer> getDoctorsId() {
        return DoctorsId;
    }

    public void setDoctorsId(List<Integer> doctorsId) {
        DoctorsId = doctorsId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
}
