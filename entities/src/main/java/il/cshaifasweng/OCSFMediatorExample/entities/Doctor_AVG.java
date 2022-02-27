package il.cshaifasweng.OCSFMediatorExample.entities;

public class Doctor_AVG {
    private String doctor_name;
    private double AVG;

    public Doctor_AVG(String doctor_name, double AVG) {
        this.doctor_name = doctor_name;
        this.AVG = AVG;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public double getAVG() {
        return AVG;
    }

    public void setAVG(double AVG) {
        this.AVG = AVG;
    }
}
