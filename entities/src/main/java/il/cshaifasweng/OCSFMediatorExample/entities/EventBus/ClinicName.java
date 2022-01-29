package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.Clinic;

import java.io.Serializable;
import java.util.List;

public class ClinicName implements Serializable {
    private List<String> ClinicsName;
    private List<Integer> ClinicsId;
    private int flag; //to Know why we need clinic

    public ClinicName() {
    }

    public ClinicName(List<String> clinicsName, List<Integer> clinicsId, int flag) {
        ClinicsName = clinicsName;
        ClinicsId = clinicsId;
        this.flag = flag;
    }

    public List<String> getClinicsName() {
        return ClinicsName;
    }

    public void setClinicsName(List<String> clinicsName) {
        ClinicsName = clinicsName;
    }

    public List<Integer> getClinicsId() {
        return ClinicsId;
    }

    public void setClinicsId(List<Integer> clinicsId) {
        ClinicsId = clinicsId;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
