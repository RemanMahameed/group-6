package il.cshaifasweng.OCSFMediatorExample.entities.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

@MappedSuperclass
public abstract class Appointment implements Serializable {

    protected int AppNum;
    protected String AppointmentType;
    protected LocalDateTime Date;
    protected LocalTime RealTime;
    protected boolean Done;
    //private LocalDateTime ScheduledTime;

    public Appointment(String appointmentType, LocalDateTime date) {
        AppointmentType = appointmentType;
        Date = date;
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

    public boolean isDone() {
        return Done;
    }

    public void setDone(boolean done) {
        Done = done;
    }

}
