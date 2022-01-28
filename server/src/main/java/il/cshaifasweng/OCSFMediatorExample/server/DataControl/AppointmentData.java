package il.cshaifasweng.OCSFMediatorExample.server.DataControl;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.DoctorApp;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ProDoctorsList;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class AppointmentData extends DataClass{
    private static Session session;
    private static Doctor getClinicFamilyDoctor(Clinic clinic) throws Exception{
        Doctor Familydoctor=new Doctor();
        List<Doctor> doctors =clinic.getDoctors();
        for (Doctor doctor : doctors) {
            if(doctor.getRole().equals("FamilyDoctor"))
                Familydoctor= doctor;
        }
        return Familydoctor;
    }

    private static Doctor getClinicPediatricianDoctor(Clinic clinic) throws Exception {
        Doctor PediatricianDoctor=new Doctor();
        List<Doctor> doctors =clinic.getDoctors();
        for (Doctor doctor : doctors) {
            if(doctor.getRole().equals("PediatricianDoctor"))
                PediatricianDoctor= doctor;
        }
        session.getTransaction().commit();
        if (session != null)
            session.close();
        return PediatricianDoctor;
    }

    public static DoctorApp getFreeClinicDoctorApp(Object object) throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Patient patient=(Patient) object;
        DoctorApp doctorAppointments=new DoctorApp();
        Doctor patientDoctor;
        if(patient.getAge()<= 18){
            patientDoctor=getClinicPediatricianDoctor(patient.getClinic());
            doctorAppointments=getFreeDoctorApp(patientDoctor,patient,"FamilyApp");
        }else if(patient.getAge()>18){
            patientDoctor=getClinicFamilyDoctor(patient.getClinic());
            doctorAppointments=getFreeDoctorApp(patientDoctor,patient,"PediatricianApp");
        }
            //session.getTransaction().commit();
        if (session != null)
            session.close();

        return doctorAppointments;
    }
   public static DoctorApp getFreeDoctorApp(Doctor doctor,Patient patient,String type) throws Exception {
       List<DoctorAppointment> doctorAppointments = doctor.getAppointments();
       LocalTime[][] DoctorReceptionTime = doctor.getReceptionTime().get(0).getActiveTime();
       LocalDateTime date = LocalDateTime.now().withMinute(0);
       LocalDateTime after4weeks = LocalDateTime.now().plusWeeks(4);
       List<DoctorAppointment> doctorAppList = new LinkedList<>();
       List<String> doctorAppString = new LinkedList<>();
       DoctorAppointment doctorAppointment1;
       String newDoctorAppointment2;
       while (date.isBefore(after4weeks)) {
           if (DoctorReceptionTime[0][0].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][0].isAfter(date.toLocalTime()) ||
                   DoctorReceptionTime[0][1].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][1].isAfter(date.toLocalTime()) ||
                   DoctorReceptionTime[0][2].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][2].isAfter(date.toLocalTime()) ||
                   DoctorReceptionTime[0][3].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][3].isAfter(date.toLocalTime()) ||
                   DoctorReceptionTime[0][4].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][4].isAfter(date.toLocalTime()) ||
                   DoctorReceptionTime[0][5].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][5].isAfter(date.toLocalTime()) ||
                   DoctorReceptionTime[0][6].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][6].isAfter(date.toLocalTime())
           ) {
               if (doctorAppointments.size() != 0) {
                   for (DoctorAppointment doctorAppointment : doctorAppointments) {
                       if (!((doctorAppointment.getDate().getYear() == date.getYear()) &&
                               (doctorAppointment.getDate().getMonth() == date.getMonth()) &&
                               (doctorAppointment.getDate().getDayOfMonth() == date.getDayOfMonth()) &&
                               (doctorAppointment.getDate().getHour() == date.getHour()) &&
                               (doctorAppointment.getDate().getMinute() == date.getMinute()))
                       ) {
                           doctorAppointment1 = new DoctorAppointment(type, date, doctor, patient, patient.getClinic());
                           doctorAppList.add(doctorAppointment1);
                           newDoctorAppointment2 ="Month: "+date.getMonth()+ "  Day: " + date.getDayOfMonth() + "  hour: " + date.getHour() + "  minute: " + date.getMinute();
                           doctorAppString.add(newDoctorAppointment2);
                           //System.out.println("The string is: "+ newDoctorAppointment2);
                       }
                   }
               } else {
                   doctorAppointment1 = new DoctorAppointment(type, date, doctor, patient, patient.getClinic());
                   doctorAppList.add(doctorAppointment1);
                   newDoctorAppointment2 ="Month: "+date.getMonth()+ "   Day: " + date.getDayOfMonth() + "   hour: " + date.getHour() + "   minute: " + date.getMinute();
                   doctorAppString.add(newDoctorAppointment2);
                  //System.out.println("The string is: "+ newDoctorAppointment2);
               }
           }
           date = date.plusMinutes(15);
       }
       return (new DoctorApp(doctorAppString, doctorAppList,patient));
   }
    public static ProDoctorsList getdoctorsofsp (String major , String id_P) throws Exception {
        // int id_patient = Integer.valueOf(id_P);
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        LinkedList<Doctor> doctors = new LinkedList<>();
        System.out.println(major);
        List<Doctor> doctorstable = getAllDoctor();
        major="ProfessionalDoctor-"+ major;
        System.out.println(major);
        for (Doctor doctor : doctorstable) {
            if (doctor.getRole().equals(major) ) {
                doctors.add(doctor);
            }
        }
        //Patient patient = getPbyID(id_P);
        ProDoctorsList SortedDoctors = new ProDoctorsList(doctors);
        if (session != null) {
            session.close();

        }
        return SortedDoctors;
    }
}
