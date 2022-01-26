package il.cshaifasweng.OCSFMediatorExample.server.DataControl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.Login;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class AppointmentData extends DataClass{

    private static Doctor getClinicFamilyDoctor(Clinic clinic) throws Exception{
        Doctor Familydoctor=new Doctor();
        List<Doctor> doctors =clinic.getDoctors();
        for (Doctor doctor : doctors) {
            if(doctor.getRole().equals("FamilyDoctor"))
                Familydoctor= doctor;
        }
        return Familydoctor;
    }

    private static Doctor getClinicPediatricianDoctor(Clinic clinic) throws Exception{
        Doctor PediatricianDoctor=new Doctor();
        List<Doctor> doctors =clinic.getDoctors();
        for (Doctor doctor : doctors) {
            if(doctor.getRole().equals("PediatricianDoctor"))
                PediatricianDoctor= doctor;
        }
        return PediatricianDoctor;
    }

    public static List<DoctorAppointment> getFreeClinicDoctorApp(Patient patient) throws Exception {
        List<DoctorAppointment> doctorAppointments=new LinkedList<>();
        Doctor patientDoctor;
        if(patient.getAge()<= 18){
            patientDoctor=getClinicPediatricianDoctor(patient.getClinic());
        }else if(patient.getAge()>18){
            patientDoctor=getClinicFamilyDoctor(patient.getClinic());
        }
        return doctorAppointments;
    }
   /* public static List<DoctorAppointment> getFreeDoctorApp(Doctor doctor) throws Exception {
        //Doctor doctor = getDoctorByID(doctor_ID);
        //assert doctor != null;
        List<DoctorAppointment> doctorAppointments = doctor.getAppointments();
        LocalTime [][] DoctorReceptionTime =doctor.getActivityTime();
        //OpeningHours openingHours = doctor.getClinic().getOpeningHours();

        LocalDateTime today = LocalDateTime.now().withMinute(0);
        LocalDateTime after4weeks = LocalDateTime.now().plusWeeks(4);

        return newDoctorAppointments;
    }*/
}
