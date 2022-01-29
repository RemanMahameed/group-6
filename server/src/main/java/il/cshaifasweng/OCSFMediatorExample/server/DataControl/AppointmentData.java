package il.cshaifasweng.OCSFMediatorExample.server.DataControl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.DoctorApp;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ProDoctorsList;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.ScheduledApp;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class AppointmentData extends DataClass{
    private static Session session;
    //**********************************************************************************************************
    //**********************************************************************************************************
    //Salsabeeeeeeeel!!
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
        if(patient.getAge()> 18){
            patientDoctor=getClinicPediatricianDoctor(patient.getClinic());
            doctorAppointments=getFreeDoctorApp(patientDoctor,patient,"FamilyApp");
        }else if(patient.getAge()<=18){
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
        //LocalDateTime date = LocalDateTime.of(2022,Month.JANUARY, 29, 10, 00);
        LocalDateTime after4weeks = LocalDateTime.now().plusWeeks(4);
        List<DoctorAppointment> doctorAppList = new LinkedList<>();
        List<String> doctorAppString = new LinkedList<>();
        DoctorAppointment doctorAppointment1;
        String newDoctorAppointment2;
        int flagIsAvailable=0;
        while (date.isBefore(after4weeks)) {
            if (DoctorReceptionTime[0][0].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][0].isAfter(date.toLocalTime()) ||
                    DoctorReceptionTime[0][1].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][1].isAfter(date.toLocalTime()) ||
                    DoctorReceptionTime[0][2].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][2].isAfter(date.toLocalTime()) ||
                    DoctorReceptionTime[0][3].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][3].isAfter(date.toLocalTime()) ||
                    DoctorReceptionTime[0][4].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][4].isAfter(date.toLocalTime()) ||
                    DoctorReceptionTime[0][5].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][5].isAfter(date.toLocalTime()) ||
                    DoctorReceptionTime[0][6].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][6].isAfter(date.toLocalTime())
            ) {
                for (DoctorAppointment doctorAppointment : doctorAppointments) {
                    if (((doctorAppointment.getDate().getYear() == date.getYear()) &&
                            (doctorAppointment.getDate().getMonth() == date.getMonth()) &&
                            (doctorAppointment.getDate().getDayOfMonth() == date.getDayOfMonth()) &&
                            (doctorAppointment.getDate().getHour() == date.getHour()) &&
                            (doctorAppointment.getDate().getMinute() == date.getMinute()))
                    )
                    {
                        flagIsAvailable = 1;
                    }
                }
                if (flagIsAvailable == 0) {
                    doctorAppointment1 = new DoctorAppointment(type, date, doctor, patient, patient.getClinic());
                    doctorAppList.add(doctorAppointment1);
                    newDoctorAppointment2 = "Month: " + date.getMonth() + "   Day: " + date.getDayOfMonth() + "   hour: " + date.getHour() + "   minute: " + date.getMinute();
                    doctorAppString.add(newDoctorAppointment2);
                    System.out.println("The string is: " + newDoctorAppointment2);
                }
            }
            flagIsAvailable=0;
            date = date.plusMinutes(15);
        }
        return (new DoctorApp(doctorAppString, doctorAppList,patient,doctor));
    }
    public static void SetSelectedDoctorAppointment(Patient patient, Doctor doctor, DoctorAppointment doctorAppointment){
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Clinic clinic=patient.getClinic();
        doctorAppointment.setClinic(clinic);
        doctorAppointment.setDoctor(doctor);
        doctorAppointment.setPatient(patient);
        List<DoctorAppointment> doctorAppointments= new LinkedList<>();
        doctorAppointments.add(doctorAppointment);
        //connect app to clinic
        if(clinic.getDoctorAppointments().size() == 0)
            clinic.setDoctorAppointments(doctorAppointments);
        else
            clinic.getDoctorAppointments().add(doctorAppointment);
        //connect app to doctor
        if(doctor.getAppointments().size() == 0){
            doctor.setAppointments(doctorAppointments);
        }else {
            doctor.getAppointments().add(doctorAppointment);
        }
        //connect app to patient
        if(patient.getDoctorAppointments().size() == 0)
            patient.setDoctorAppointments(doctorAppointments);
        else
            patient.getDoctorAppointments().add(doctorAppointment);
        //sending email..
        String messageContent="You Have DoctorAppointment at: "+ doctorAppointment.getDate()+"\n"
                +"with doctor: "+doctor.getFirstName()+" "+doctor.getLastName()+"\n"
                +"at clinic: "+clinic.getClinicType();
        String subject="Appointment Configuration";
        setEmail(patient.getEmail(), messageContent,subject);

        //session.saveOrUpdate(doctor);
        //session.saveOrUpdate(patient);
        //session.saveOrUpdate(clinic);
        session.saveOrUpdate(doctorAppointment);
        session.flush();
        session.getTransaction().commit();
        if (session != null)
            session.close();
    }
    public static void setEmail(String patient ,String content,String subject){

        // Put sender’s address
        String from = "salehsalsabeel99@gmail.com";
        final String username = "salehsalsabeel99@gmail.com";
        final String password = "hsjytvhggi";

        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        // Get the Session object.
        javax.mail.Session session = javax.mail.Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field
            message.setFrom(new InternetAddress(from));
            // Set To: header field
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(patient));
            // Set Subject: header field
            message.setSubject(subject);
            // Put the content of your message
            message.setText(content);
            // Send message
            System.out.println("Sending message ...");
            Transport.send(message);
            System.out.println("Message sent successfully ...");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public static ScheduledApp getSchuledAppointment(String type,Patient patient) throws Exception{
        switch (type) {
            case ("Doctor"):
                return getDoctorApp(patient);
            case ("Nurse"):
                return getNurseApp(patient);
            case ("LaboratoryFacts"):
                return getLaboratoryFactsaApp(patient);
        }
        return new ScheduledApp();
    }
    private static ScheduledApp getDoctorApp(Patient patient) throws Exception{
        List<DoctorAppointment> doctorAppointments =patient.getDoctorAppointments();
        List<String> stringApp=new LinkedList<>();
        String newString;
        if(doctorAppointments.size() != 0){
            for (DoctorAppointment doctorAppointment : doctorAppointments) {
                newString="AppointmentType: "+doctorAppointment.getAppointmentType()
                        +"  Date: "+ doctorAppointment.getDate()
                        +"  with doctor: "+doctorAppointment.getDoctor().getFirstName()+" "+doctorAppointment.getDoctor().getLastName()+"\n"
                        +"  at clinic: "+doctorAppointment.getClinic().getClinicType();
                stringApp.add(newString);
            }
        }else {
            newString="There is not Scheduled Appointment!";
            stringApp.add(newString);
        }
        return (new ScheduledApp(stringApp,doctorAppointments,"Doctor"));
    }
    private static ScheduledApp getLaboratoryFactsaApp(Patient patient) throws Exception{
        List<LaboratoryFactsAppointment> laboratoryFactsAppointments=patient.getLaboratoryFactsAppointments();
        List<String> stringApp=new LinkedList<>();
        String newString;
        if(laboratoryFactsAppointments.size() != 0){
            for (LaboratoryFactsAppointment laboratoryFactsAppointment : laboratoryFactsAppointments) {
                newString="AppointmentType: "+laboratoryFactsAppointment.getAppointmentType()
                        +"  Date: "+ laboratoryFactsAppointment.getDate()
                        +"  with doctor: "+laboratoryFactsAppointment.getLaboratoryFacts().getFirstName()+" "+laboratoryFactsAppointment.getLaboratoryFacts().getLastName()+"\n"
                        +"  at clinic: "+laboratoryFactsAppointment.getClinic().getClinicType();
                stringApp.add(newString);
            }
        }else {
            newString="There is not Scheduled Appointment!";
            stringApp.add(newString);
        }
        return (new ScheduledApp("LaboratoryFactsa",stringApp,laboratoryFactsAppointments));
    }
    private static ScheduledApp getNurseApp(Patient patient) throws Exception{
        List<NurseAppointment> nurseAppointments=patient.getNurseAppointments();
        List<String> stringApp=new LinkedList<>();
        String newString;
        if(nurseAppointments.size() != 0){
            for (NurseAppointment nurseAppointment : nurseAppointments) {
                newString="AppointmentType: "+nurseAppointment.getAppointmentType()
                        +"  Date: "+ nurseAppointment.getDate()
                        +"  with doctor: "+nurseAppointment.getNurse().getFirstName()+" "+nurseAppointment.getNurse().getLastName()+"\n"
                        +"  at clinic: "+nurseAppointment.getClinic().getClinicType();
                stringApp.add(newString);
            }
        }else {
            newString="There is not Scheduled Appointment!";
            stringApp.add(newString);
        }
        return (new ScheduledApp(stringApp,"Nurse",nurseAppointments));
    }
    public static void cancelAppointment(DoctorAppointment appointment) throws IOException{
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(appointment);
        session.getTransaction().commit();
        if (session != null)
            session.close();
    }

    //**********************************************************************************************************
    //**********************************************************************************************************
    //**********************************************************************************************************
    //Srar namer reman
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
