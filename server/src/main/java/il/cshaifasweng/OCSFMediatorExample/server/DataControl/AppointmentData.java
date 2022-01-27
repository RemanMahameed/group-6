package il.cshaifasweng.OCSFMediatorExample.server.DataControl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import org.hibernate.SessionFactory;


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
        System.out.println("getClinicPediatricianDoctor!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        Doctor PediatricianDoctor=new Doctor();
        List<Doctor> doctors =clinic.getDoctors();
        System.out.println("getClinicPediatricianDoctor"+doctors.get(0)+ "size "+doctors.size());
        for (Doctor doctor : doctors) {
            if(doctor.getRole().equals("PediatricianDoctor"))
                PediatricianDoctor= doctor;
        }
        return PediatricianDoctor;
    }

    public static List<DoctorAppointment> getFreeClinicDoctorApp(Object object) throws Exception {
        System.out.println("getFreeClinicDoctorApp");
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("AFteeeeeeer getFreeClinicDoctorApp!!!!");

        Patient patient=(Patient) object;
        List<DoctorAppointment> doctorAppointments=new LinkedList<>();
        Doctor patientDoctor;
        if(patient.getAge()<= 18){
            patientDoctor=getClinicPediatricianDoctor(patient.getClinic());
            doctorAppointments=getFreeDoctorApp(patientDoctor,patient,"FamilyApp");
        }else if(patient.getAge()>18){
            patientDoctor=getClinicFamilyDoctor(patient.getClinic());
            doctorAppointments=getFreeDoctorApp(patientDoctor,patient,"PediatricianApp");
        }

        session.getTransaction().commit();
        if (session != null)
            session.close();

        return doctorAppointments;
    }
   public static List<DoctorAppointment> getFreeDoctorApp(Doctor doctor,Patient patient,String type) throws Exception {
        List<DoctorAppointment> doctorAppointments = doctor.getAppointments();
        LocalTime [][] DoctorReceptionTime =doctor.getReceptionTime().get(0).getActiveTime();
        LocalDateTime today = LocalDateTime.now().withMinute(0);
        LocalDateTime after4weeks = LocalDateTime.now().plusWeeks(4);
        List<DoctorAppointment> newDoctorAppointments = new LinkedList<>();
        while (today.isBefore(after4weeks)){
            if ( DoctorReceptionTime[0][0].isBefore(today.toLocalTime())&&DoctorReceptionTime[1][0].isAfter(today.toLocalTime())||
                 DoctorReceptionTime[0][1].isBefore(today.toLocalTime())&&DoctorReceptionTime[1][1].isAfter(today.toLocalTime())||
                 DoctorReceptionTime[0][2].isBefore(today.toLocalTime())&&DoctorReceptionTime[1][2].isAfter(today.toLocalTime())||
                 DoctorReceptionTime[0][3].isBefore(today.toLocalTime())&&DoctorReceptionTime[1][3].isAfter(today.toLocalTime())||
                 DoctorReceptionTime[0][4].isBefore(today.toLocalTime())&&DoctorReceptionTime[1][4].isAfter(today.toLocalTime())||
                 DoctorReceptionTime[0][5].isBefore(today.toLocalTime())&&DoctorReceptionTime[1][5].isAfter(today.toLocalTime())||
                 DoctorReceptionTime[0][6].isBefore(today.toLocalTime())&&DoctorReceptionTime[1][6].isAfter(today.toLocalTime())
            ){
                for (DoctorAppointment doctorAppointment : doctorAppointments) {
                    if (!(  (doctorAppointment.getDate().getYear() == today.getYear())&&
                            (doctorAppointment.getDate().getMonth() == today.getMonth())&&
                            (doctorAppointment.getDate().getDayOfMonth() == today.getDayOfMonth())&&
                            (doctorAppointment.getDate().getHour() == today.getHour())&&
                            (doctorAppointment.getDate().getMinute() == today.getMinute()))
                    ){
                        DoctorAppointment newDoctorAppointment = new DoctorAppointment(type,today,doctor,patient,patient.getClinic());
                        newDoctorAppointments.add(newDoctorAppointment);
                        System.out.println("Free Doctor appointment at : "+today.getDayOfMonth()+"d "+today.getHour()+"h "+today.getMinute()+"m ");
                    }
                }
            }
            today=today.plusMinutes(15);
        }
        return newDoctorAppointments;
    }
}
