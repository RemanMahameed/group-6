package il.cshaifasweng.OCSFMediatorExample.server.DataControl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.*;
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

    public static DoctorApp getFreeClinicDoctorApp(int patientId) throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        Patient patient=getPatientById(patientId);
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
        LocalDateTime now = LocalDateTime.now();
        //LocalDateTime date = LocalDateTime.of(2022,Month.JANUARY, 29, 10, 00);
        LocalDateTime after4weeks = LocalDateTime.now().plusWeeks(4);
        List<DoctorAppointment> doctorAppList = new LinkedList<>();
        List<String> doctorAppString = new LinkedList<>();
        DoctorAppointment doctorAppointment1;
        String newDoctorAppointment2;
        int flagIsAvailable=0;
        while (date.isBefore(after4weeks)) {
            if (date.getDayOfWeek().toString().equalsIgnoreCase("Sunday") && DoctorReceptionTime[0][0].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][0].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Monday") && DoctorReceptionTime[0][1].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][1].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Tuesday") && DoctorReceptionTime[0][2].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][2].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Wednesday") && DoctorReceptionTime[0][3].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][3].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Thursday") && DoctorReceptionTime[0][4].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][4].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Friday") && DoctorReceptionTime[0][5].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][5].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Saturday") && DoctorReceptionTime[0][6].isBefore(date.toLocalTime()) && DoctorReceptionTime[1][6].isAfter(date.toLocalTime())
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
                    if(date.toLocalDate().equals(now.toLocalDate()) ){
                        if(date.toLocalTime().isAfter(now.toLocalTime()))
                        {
                            doctorAppointment1 = new DoctorAppointment(type, date, doctor, patient, patient.getClinic());
                            doctorAppList.add(doctorAppointment1);
                            newDoctorAppointment2 = "Month: " + date.getMonth() + "   Day: " + date.getDayOfMonth() + "   hour: " + date.getHour() + "   minute: " + date.getMinute();
                            doctorAppString.add(newDoctorAppointment2);
                            System.out.println("The string is: " + newDoctorAppointment2);
                        }
                    }else {
                        doctorAppointment1 = new DoctorAppointment(type, date, doctor, patient, patient.getClinic());
                        doctorAppList.add(doctorAppointment1);
                        newDoctorAppointment2 = "Month: " + date.getMonth() + "   Day: " + date.getDayOfMonth() + "   hour: " + date.getHour() + "   minute: " + date.getMinute();
                        doctorAppString.add(newDoctorAppointment2);
                        System.out.println("The string is: " + newDoctorAppointment2);
                    }
                }
            }
            flagIsAvailable=0;
            date = date.plusMinutes(15);
        }
        return (new DoctorApp(doctorAppString, doctorAppList,patient,doctor));
    }
    public static void SetSelectedDoctorAppointment(int patientId, Doctor doctor, DoctorAppointment doctorAppointment) throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        Patient patient=getPatientById(patientId);
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
                             +"Date: "+ doctorAppointment.getDate().toLocalDate()+" Time: "+doctorAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                             +"At clinic: "+clinic.getClinicType();
        String subject="Appointment Configuration";
        //sendEmail(patient.getEmail(), messageContent,subject);

        session.saveOrUpdate(doctorAppointment);
        session.flush();
        session.getTransaction().commit();
        if (session != null)
            session.close();
    }

    public static void setSelectedVaccineApp(VaccineAppointment vaccineAppointment,int clinicId,int patientId) throws Exception {
        Clinic clinic=getClinicById(clinicId);
        Patient patient=getPatientById(patientId);
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
//        if(vaccineAppointment.getAppointmentType().equals("VaccineCorona")){
//            patient.setNumOfVaccine((patient.getNumOfVaccine()+1));
//        }
        clinic.getVaccineAppointments().add(vaccineAppointment);
        patient.getVaccineAppointments().add(vaccineAppointment);
        vaccineAppointment.setClinic(clinic);
        vaccineAppointment.setPatient(patient);

        //sending email..
        String messageContent="You Have "+ vaccineAppointment.getAppointmentType() +" Appointment at: \n"
                             +"Date: "+ vaccineAppointment.getDate().toLocalDate()+" Time: "+vaccineAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                             +"At clinic: "+clinic.getClinicType();
        String subject="Appointment Configuration";
        //sendEmail(patient.getEmail(), messageContent,subject);
        //sendEmail("salehsalsabeel99@gamil.com", messageContent,subject);
        //
        session.saveOrUpdate(vaccineAppointment);
        session.flush();
        session.getTransaction().commit();
        if (session != null)
            session.close();
    }

    public static void setSelectedCoronaTestApp(CoronaTestAppointment coronaTestAppointment,int clinicId,int patientId) throws Exception {
        Clinic clinic=getClinicById(clinicId);
        Patient patient=getPatientById(patientId);
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();

        clinic.getCoronaTestAppointments().add(coronaTestAppointment);
        patient.getCoronaTestAppointments().add(coronaTestAppointment);
        coronaTestAppointment.setClinic(clinic);
        coronaTestAppointment.setPatient(patient);

        //sending email..
        String messageContent="You Have "+ coronaTestAppointment.getAppointmentType() +" Appointment at: \n"
                             +"Date: "+ coronaTestAppointment.getDate().toLocalDate()+" Time: "+coronaTestAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                             +"At clinic: "+clinic.getClinicType();
        String subject="Appointment Configuration";
        //sendEmail(patient.getEmail(), messageContent,subject);
        //sendEmail("salehsalsabeel99@gamil.com", messageContent,subject);
        //

        session.saveOrUpdate(coronaTestAppointment);
        session.flush();
        session.getTransaction().commit();
        if (session != null)
            session.close();

    }
    public static void sendEmail(String patient ,String content,String subject){

        // Put sender’s address
        String from = "salehsalsabeel99@gmail.com";
        final String username = "salehsalsabeel99@gmail.com";
        final String password = "hgpl]ggi";

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
    public static void sendReminderEmail(int clinicId) throws Exception {
        LocalDate tomorrowDate = LocalDate.now().plusDays(1);
        Clinic clinic = getClinicById(clinicId);
        assert clinic != null;
        //get clinic's appointment
        List<DoctorAppointment> doctorAppointments=clinic.getDoctorAppointments();
        List<NurseAppointment> nurseAppointments=clinic.getNurseAppointments();
        List<LaboratoryFactsAppointment> laboratoryFactsAppointments=clinic.getLaboratoryFactsAppointments();
        List<CoronaTestAppointment> coronaTestAppointments=clinic.getCoronaTestAppointments();
        List<VaccineAppointment> vaccineAppointments=clinic.getVaccineAppointments();
        //Send reminder email
        sendReminderDoctorApp(doctorAppointments,tomorrowDate,clinicId);
        sendReminderNurseApp(nurseAppointments,tomorrowDate,clinicId);
        sendReminderLaboratoryFactsApp(laboratoryFactsAppointments,tomorrowDate,clinicId);
        sendReminderCoronaTestApp(coronaTestAppointments,tomorrowDate,clinicId);
        sendReminderVaccineApp(vaccineAppointments,tomorrowDate,clinicId);
    }
    public static void sendReminderDoctorApp(List<DoctorAppointment>doctorAppointments,LocalDate date,int clinicId) throws Exception {
        Clinic clinic= getClinicById(clinicId);
        for (DoctorAppointment doctorAppointment:doctorAppointments){
            if(doctorAppointment.getDate().toLocalDate().equals(date)){
                String patientEmail=doctorAppointment.getPatient().getEmail();
                //Email content..
                String messageContent="You Have "+ doctorAppointment.getAppointmentType() +" Appointment at: \n"
                        +"Date: "+ doctorAppointment.getDate().toLocalDate()+" Time: "+doctorAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                        +"At clinic: "+clinic.getClinicType();
                //Email subject
                String subject="Reminder Email";
                //sendEmail(patientEmail, messageContent,subject);
                sendEmail("salehsalsabeel99@gamil.com", messageContent,subject);
            }
        }
    }
    public static void sendReminderNurseApp(List<NurseAppointment>nurseAppointments,LocalDate date,int clinicId) throws Exception {
        Clinic clinic= getClinicById(clinicId);
        for (NurseAppointment nurseAppointment:nurseAppointments){
            if(nurseAppointment.getDate().toLocalDate().equals(date)){
                String patientEmail=nurseAppointment.getPatient().getEmail();
                //Email content..
                String messageContent="You Have "+ nurseAppointment.getAppointmentType() +" Appointment at: \n"
                        +"Date: "+ nurseAppointment.getDate().toLocalDate()+" Time: "+nurseAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                        +"At clinic: "+clinic.getClinicType();
                //Email subject
                String subject="Reminder Email";
                //sendEmail(patientEmail, messageContent,subject);
                sendEmail("salehsalsabeel99@gamil.com", messageContent,subject);
            }
        }
    }
    public static void sendReminderLaboratoryFactsApp(List<LaboratoryFactsAppointment>laboratoryFactsAppointments,LocalDate date,int clinicId) throws Exception {
        Clinic clinic= getClinicById(clinicId);
        for (LaboratoryFactsAppointment laboratoryFactsAppointment:laboratoryFactsAppointments){
            if(laboratoryFactsAppointment.getDate().toLocalDate().equals(date)){
                String patientEmail=laboratoryFactsAppointment.getPatient().getEmail();
                //Email content..
                String messageContent="You Have "+ laboratoryFactsAppointment.getAppointmentType() +" Appointment at: \n"
                        +"Date: "+ laboratoryFactsAppointment.getDate().toLocalDate()+" Time: "+laboratoryFactsAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                        +"At clinic: "+clinic.getClinicType();
                //Email subject
                String subject="Reminder Email";
                //sendEmail(patientEmail, messageContent,subject);
                sendEmail("salehsalsabeel99@gamil.com", messageContent,subject);
            }
        }
    }
    public static void sendReminderCoronaTestApp(List<CoronaTestAppointment>coronaTestAppointments,LocalDate date,int clinicId) throws Exception {
        Clinic clinic= getClinicById(clinicId);
        for (CoronaTestAppointment coronaTestAppointment:coronaTestAppointments){
            if(coronaTestAppointment.getDate().toLocalDate().equals(date)){
                String patientEmail=coronaTestAppointment.getPatient().getEmail();
                //Email content..
                String messageContent="You Have "+ coronaTestAppointment.getAppointmentType() +" Appointment at: \n"
                        +"Date: "+ coronaTestAppointment.getDate().toLocalDate()+" Time: "+coronaTestAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                        +"At clinic: "+clinic.getClinicType();
                //Email subject
                String subject="Reminder Email";
                //sendEmail(patientEmail, messageContent,subject);
                sendEmail("salehsalsabeel99@gamil.com", messageContent,subject);
            }
        }
    }
    public static void sendReminderVaccineApp(List<VaccineAppointment>vaccineAppointments,LocalDate date,int clinicId) throws Exception {
        Clinic clinic= getClinicById(clinicId);
        for (VaccineAppointment vaccineAppointment:vaccineAppointments){
            if(vaccineAppointment.getDate().toLocalDate().equals(date)){
                String patientEmail=vaccineAppointment.getPatient().getEmail();
                //Email content..
                String messageContent="You Have "+ vaccineAppointment.getAppointmentType() +" Appointment at: \n"
                        +"Date: "+ vaccineAppointment.getDate().toLocalDate()+" Time: "+vaccineAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                        +"At clinic: "+clinic.getClinicType();
                //Email subject
                String subject="Reminder Email";
                //sendEmail(patientEmail, messageContent,subject);
                sendEmail("salehsalsabeel99@gamil.com", messageContent,subject);
            }
        }
    }
    public static ScheduledApp getScheduledAppointment(String type,int patientId) throws Exception{
        Patient patient=getPatientById(patientId);
        switch (type) {
            case ("Doctor"):
                return getDoctorApp(patient);
            case ("Nurse"):
                return getNurseApp(patient);
            case ("LaboratoryFacts"):
                return getLaboratoryFactsApp(patient);
            case ("CoronaTest"):
                return getCoronaTestApp(patient.getId());
            case ("Vaccine"):
                return getVaccineApp(patient.getId());
        }
        return new ScheduledApp();
    }
    private static ScheduledApp getDoctorApp(Patient patient) throws Exception{
        List<DoctorAppointment> doctorAppointments =patient.getDoctorAppointments();
        List<String> stringApp=new LinkedList<>();
        String newString;
        if(doctorAppointments.size() != 0){
            for (DoctorAppointment doctorAppointment : doctorAppointments) {
                newString="AppointmentType: "+doctorAppointment.getAppointmentType()+"\n"
                         +"Date: "+ doctorAppointment.getDate().toLocalDate()+"  Time: "+doctorAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                         +"With doctor: "+doctorAppointment.getDoctor().getFirstName()+" "+doctorAppointment.getDoctor().getLastName()+"\n"
                         +"At clinic: "+doctorAppointment.getClinic().getClinicType();
                stringApp.add(newString);
            }
        }else {
            newString="There is not Scheduled Appointment!";
            stringApp.add(newString);
        }
        ScheduledApp scheduledApp=new ScheduledApp(stringApp,"Doctor");
        scheduledApp.setDoctorAppointments(doctorAppointments);
        return (scheduledApp);
    }

    private static ScheduledApp getLaboratoryFactsApp(Patient patient) throws Exception{
        List<LaboratoryFactsAppointment> laboratoryFactsAppointments=patient.getLaboratoryFactsAppointments();
        List<String> stringApp=new LinkedList<>();
        String newString;
        if(laboratoryFactsAppointments.size() != 0){
            for (LaboratoryFactsAppointment laboratoryFactsAppointment : laboratoryFactsAppointments) {
                newString="AppointmentType: "+laboratoryFactsAppointment.getAppointmentType()+"\n"
                         +"Date: "+ laboratoryFactsAppointment.getDate().toLocalDate()+"  Time: "+laboratoryFactsAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                         +"With doctor: "+laboratoryFactsAppointment.getLaboratoryFacts().getFirstName()+" "+laboratoryFactsAppointment.getLaboratoryFacts().getLastName()+"\n"
                         +"At clinic: "+laboratoryFactsAppointment.getClinic().getClinicType();
                stringApp.add(newString);
            }
        }else {
            newString="There is not Scheduled Appointment!";
            stringApp.add(newString);
        }
        ScheduledApp scheduledApp=new ScheduledApp(stringApp,"LaboratoryFacts");
        scheduledApp.setLaboratoryFactsAppointments(laboratoryFactsAppointments);
        return (scheduledApp);
    }

    private static ScheduledApp getNurseApp(Patient patient) throws Exception{
        List<NurseAppointment> nurseAppointments=patient.getNurseAppointments();
        List<String> stringApp=new LinkedList<>();
        String newString;
        if(nurseAppointments.size() != 0){
            for (NurseAppointment nurseAppointment : nurseAppointments) {
                newString="AppointmentType: "+nurseAppointment.getAppointmentType()+"\n"
                         +"Date: "+ nurseAppointment.getDate().toLocalDate()+"  Time: "+nurseAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                         +"With doctor: "+nurseAppointment.getNurse().getFirstName()+" "+nurseAppointment.getNurse().getLastName()+"\n"
                         +"At clinic: "+nurseAppointment.getClinic().getClinicType();
                stringApp.add(newString);
            }
        }else {
            newString="There is not Scheduled Appointment!";
            stringApp.add(newString);
        }
        ScheduledApp scheduledApp=new ScheduledApp(stringApp,"Nurse");
        scheduledApp.setNurseAppointments(nurseAppointments);
        return (scheduledApp);
    }

    private static ScheduledApp getCoronaTestApp(int patientId) throws Exception{
        List<CoronaTestAppointment> coronaTestAppointments=getCoronaTestPatientById(patientId);
        List<String> stringApp=new LinkedList<>();
        String newString;
        if(coronaTestAppointments.size() != 0){
            for (CoronaTestAppointment coronaTestAppointment : coronaTestAppointments) {
                newString="AppointmentType: "+coronaTestAppointment.getAppointmentType()+"\n"
                         +"Date: "+ coronaTestAppointment.getDate().toLocalDate()+"  Time: "+coronaTestAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                         +"At clinic: "+coronaTestAppointment.getClinic().getClinicType();
                stringApp.add(newString);
            }
        }else {
            newString="There is not Scheduled Appointment!";
            stringApp.add(newString);
        }
        ScheduledApp scheduledApp=new ScheduledApp(stringApp,"CoronaTest");
        scheduledApp.setCoronaTestAppointments(coronaTestAppointments);

        return (scheduledApp);
    }

    private static ScheduledApp getVaccineApp(int patientId) throws Exception{
        List<VaccineAppointment> vaccineAppointments=getVaccinePatientById(patientId);
        List<String> stringApp=new LinkedList<>();
        String newString;
        if(vaccineAppointments.size() != 0){
            for (VaccineAppointment vaccineAppointment : vaccineAppointments) {
                newString="AppointmentType: "+vaccineAppointment.getAppointmentType()+"\n"
                        +"Date: "+ vaccineAppointment.getDate().toLocalDate()+"  Time: "+vaccineAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                        +"At clinic: "+vaccineAppointment.getClinic().getClinicType();
                stringApp.add(newString);
            }
        }else {
            newString="There is not Scheduled Appointment!";
            stringApp.add(newString);
        }
        ScheduledApp scheduledApp=new ScheduledApp(stringApp,"Vaccine");
        scheduledApp.setVaccineAppointments(vaccineAppointments);
        return (scheduledApp);
    }
    public static void cancelAppointment(Appointment appointment) throws IOException{
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(appointment);
        session.getTransaction().commit();
        if (session != null)
            session.close();
    }
//    public static void cancelDoctorAppointment(DoctorAppointment appointment) throws IOException{
//        SessionFactory sessionFactory = getSessionFactory();
//        session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.delete(appointment);
//        session.getTransaction().commit();
//        if (session != null)
//            session.close();
//    }
//    public static UpdateObject getObjectByIdByType(int id, String type) throws Exception{
//        switch (type) {
//            case ("Patient"):
//                return getUpdtePatientByID(id);
//            case ("Nurse"):
//                return new UpdateObject();
//
//        }
//        return new UpdateObject();
//    }
//    private static UpdateObject getUpdtePatientByID(int Id) throws Exception {
//        UpdateObject updateObject=new UpdateObject();
//        List<Patient> patients=getAllPatients();
//        for (Patient patient:patients){
//            if(patient.getId()==Id)
//                updateObject.setObject(patient);
//
//        }
//        return updateObject;
//    }
    public static ClinicName getAllClinicName(String flag) throws Exception {
        List<Clinic> clinics=getAllClinic();
        List<String> clinicsName=new LinkedList<>();
        List<Integer> clinicsId=new LinkedList<>();
        for(Clinic clinic:clinics){
            clinicsName.add(clinic.getClinicType());
            clinicsId.add(clinic.getId());
        }

        return new ClinicName(clinicsName,clinicsId,flag);
    }
    //return free Appointment of corna test or vaccine depend on type
    public static FreeAppointment getFreeCoronaOrVaccine(int clinicId,String AppType,int PatientId) throws Exception {
        Clinic clinic=getClinicById(clinicId);
        FreeAppointment freeAppointment=new FreeAppointment();
        if(AppType.equals("CoronaTest")){
            LocalTime[][] CoronaTestTime=clinic.getCoronaTestTime();
            List<CoronaTestAppointment> coronaTestAppointments=clinic.getCoronaTestAppointments();
            freeAppointment=getFreeCoronaTestApp(coronaTestAppointments,CoronaTestTime,clinic,PatientId);
        }else {
            LocalTime[][] VaccineTime=clinic.getVaccineTime();
            List<VaccineAppointment> vaccineAppointments=clinic.getVaccineAppointments();
            freeAppointment=getFreeVaccineApp(vaccineAppointments,VaccineTime,clinic,PatientId,AppType);
        }
        return freeAppointment;
    }
    public static FreeAppointment getFreeCoronaTestApp(List<CoronaTestAppointment> coronaTestAppointments,LocalTime[][] ActiveTime,Clinic clinic,int PatientId) throws Exception {
        Patient patient =getPatientById(PatientId);
        int clinicId=clinic.getId();
        LocalDateTime date = LocalDateTime.now().withMinute(0);
        LocalDateTime now =LocalDateTime.now();
        //LocalDateTime date = LocalDateTime.of(2022,Month.JANUARY, 29, 10, 00);
        LocalDateTime after1weeks = LocalDateTime.now().plusWeeks(1);
        List<CoronaTestAppointment> newCoronaTestAppointments = new LinkedList<>();
        List<String> AppString = new LinkedList<>();
        CoronaTestAppointment coronaTestAppointment;
        String newCoronaApp;
        int flagIsAvailable=0;
        while (date.isBefore(after1weeks)) {
            if (date.getDayOfWeek().toString().equalsIgnoreCase("Sunday") && ActiveTime[0][0].isBefore(date.toLocalTime()) && ActiveTime[1][0].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Monday")   &&ActiveTime[0][1].isBefore(date.toLocalTime()) && ActiveTime[1][1].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Tuesday")  &&ActiveTime[0][2].isBefore(date.toLocalTime()) && ActiveTime[1][2].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Wednesday")&&ActiveTime[0][3].isBefore(date.toLocalTime()) && ActiveTime[1][3].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Thursday") &&ActiveTime[0][4].isBefore(date.toLocalTime()) && ActiveTime[1][4].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Friday")   &&ActiveTime[0][5].isBefore(date.toLocalTime()) && ActiveTime[1][5].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Saturday") &&ActiveTime[0][6].isBefore(date.toLocalTime()) && ActiveTime[1][6].isAfter(date.toLocalTime())
            ) {
                for (CoronaTestAppointment coronaTestAppointment1 : coronaTestAppointments) {
                    if (((coronaTestAppointment1.getDate().getYear() == date.getYear()) &&
                            (coronaTestAppointment1.getDate().getMonth() == date.getMonth()) &&
                            (coronaTestAppointment1.getDate().getDayOfMonth() == date.getDayOfMonth()) &&
                            (coronaTestAppointment1.getDate().getHour() == date.getHour()) &&
                            (coronaTestAppointment1.getDate().getMinute() == date.getMinute()))
                    ) {
                        flagIsAvailable = 1;
                    }
                }
                if (flagIsAvailable == 0) {
                    if (date.toLocalDate().equals(now.toLocalDate())) {
                        if (date.toLocalTime().isAfter(now.toLocalTime())) {
                            coronaTestAppointment = new CoronaTestAppointment("CoronaTest", date, patient, clinic);
                            newCoronaTestAppointments.add(coronaTestAppointment);
                            newCoronaApp = "Month: " + date.getMonth() + "   Day: " + date.getDayOfMonth() + "   hour: " + date.getHour() + "   minute: " + date.getMinute();
                            AppString.add(newCoronaApp);
                            System.out.println("The string is: " + newCoronaApp);
                        }
                    } else {
                        coronaTestAppointment = new CoronaTestAppointment("CoronaTest", date, patient, clinic);
                        newCoronaTestAppointments.add(coronaTestAppointment);
                        newCoronaApp = "Month: " + date.getMonth() + "   Day: " + date.getDayOfMonth() + "   hour: " + date.getHour() + "   minute: " + date.getMinute();
                        AppString.add(newCoronaApp);
                        System.out.println("The string is: " + newCoronaApp);
                    }
                }
            }
                flagIsAvailable = 0;
                date = date.plusMinutes(10);
        }
        return (new FreeAppointment("CoronaTest",clinicId,AppString,newCoronaTestAppointments));
    }
    public static FreeAppointment getFreeVaccineApp(List<VaccineAppointment> vaccineAppointmentList,LocalTime[][] ActiveTime,Clinic clinic,int PatientId,String AppType) throws Exception {
        Patient patient =getPatientById(PatientId);
        int clinicId=clinic.getId();
        LocalDateTime date = LocalDateTime.now().withMinute(0);
        LocalDateTime now = LocalDateTime.now();
        //LocalDateTime date = LocalDateTime.of(2022,Month.JANUARY, 29, 10, 00);
        LocalDateTime after1weeks = LocalDateTime.now().plusWeeks(1);
        List<VaccineAppointment> vaccineAppointments = new LinkedList<>();
        List<String> AppString = new LinkedList<>();
        VaccineAppointment vaccineAppointment;
        String newVaccineString;
        int flagIsAvailable=0;
        while (date.isBefore(after1weeks)) {
            if (date.getDayOfWeek().toString().equalsIgnoreCase("Sunday") && ActiveTime[0][0].isBefore(date.toLocalTime()) && ActiveTime[1][0].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Monday")   && ActiveTime[0][1].isBefore(date.toLocalTime()) && ActiveTime[1][1].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Tuesday")  && ActiveTime[0][2].isBefore(date.toLocalTime()) && ActiveTime[1][2].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Wednesday")&& ActiveTime[0][3].isBefore(date.toLocalTime()) && ActiveTime[1][3].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Thursday") && ActiveTime[0][4].isBefore(date.toLocalTime()) && ActiveTime[1][4].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Friday")   && ActiveTime[0][5].isBefore(date.toLocalTime()) && ActiveTime[1][5].isAfter(date.toLocalTime()) ||
                    date.getDayOfWeek().toString().equalsIgnoreCase("Saturday") && ActiveTime[0][6].isBefore(date.toLocalTime()) && ActiveTime[1][6].isAfter(date.toLocalTime())
            ) {
                for (VaccineAppointment vaccineAppointment1 : vaccineAppointmentList) {
                    if (((vaccineAppointment1.getDate().getYear() == date.getYear()) &&
                            (vaccineAppointment1.getDate().getMonth() == date.getMonth()) &&
                            (vaccineAppointment1.getDate().getDayOfMonth() == date.getDayOfMonth()) &&
                            (vaccineAppointment1.getDate().getHour() == date.getHour()) &&
                            (vaccineAppointment1.getDate().getMinute() == date.getMinute()))
                    )
                    {
                        flagIsAvailable = 1;
                    }
                }
                if (flagIsAvailable == 0) {
                    if (date.toLocalDate().equals(now.toLocalDate())) {
                        if (date.toLocalTime().isAfter(now.toLocalTime())) {
                            vaccineAppointment = new VaccineAppointment(AppType, date,patient,clinic);
                            vaccineAppointments.add(vaccineAppointment);
                            newVaccineString = "Month: " + date.getMonth() + "   Day: " + date.getDayOfMonth() + "   hour: " + date.getHour() + "   minute: " + date.getMinute();
                            AppString.add(newVaccineString);
                            System.out.println("The string is: " + newVaccineString);
                        }
                    }else {
                        vaccineAppointment = new VaccineAppointment(AppType, date,patient,clinic);
                        vaccineAppointments.add(vaccineAppointment);
                        newVaccineString = "Month: " + date.getMonth() + "   Day: " + date.getDayOfMonth() + "   hour: " + date.getHour() + "   minute: " + date.getMinute();
                        AppString.add(newVaccineString);
                        System.out.println("The string is: " + newVaccineString);
                    }
                }
            }
            flagIsAvailable=0;
            date = date.plusMinutes(10);
        }
        return (new FreeAppointment(vaccineAppointments,"Vaccine",clinicId,AppString));
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
