package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@MappedSuperclass
public abstract class Appointment implements Serializable {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "Appointment_ID")
    //protected Long Id;
    protected int AppNum;
    protected String AppointmentType;
    protected LocalDateTime Date;
    //private LocalDateTime ScheduledTime;
    protected LocalTime RealTime;
    protected boolean Available;
    protected boolean Done;

    public Appointment(int appNum, String appointmentType, LocalDateTime date, LocalTime realTime, boolean available, boolean done) {
        AppNum = appNum;
        AppointmentType = appointmentType;
        Date = date;
        RealTime = realTime;
        Available = available;
        Done = done;
    }

    public Appointment() {
    }

    public int getAppNum() {
        return AppNum;
    }

    public void setAppNum(int appNum) {
        AppNum = appNum;
    }

    public String getAppointmentType() {
        return AppointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        AppointmentType = appointmentType;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }

    public LocalTime getRealTime() {
        return RealTime;
    }

    public void setRealTime(LocalTime realTime) {
        RealTime = realTime;
    }

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean available) {
        this.Available = available;
    }

    public boolean isDone() {
        return Done;
    }

    public void setDone(boolean done) {
        Done = done;
    }

}
