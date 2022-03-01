package il.cshaifasweng.OCSFMediatorExample.server.DataControl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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
                            newDoctorAppointment2 = "Date: " + date.getDayOfMonth() +"-" + date.getMonth()  + "   Time: " + date.getHour() + ":"+ (date.getMinute()<10?"0":"") + date.getMinute();
                            doctorAppString.add(newDoctorAppointment2);
                            //System.out.println("The string is: " + newDoctorAppointment2);
                        }
                    }else {
                        doctorAppointment1 = new DoctorAppointment(type, date, doctor, patient, patient.getClinic());
                        doctorAppList.add(doctorAppointment1);
                        newDoctorAppointment2 = "Date: " + date.getDayOfMonth() +"-" + date.getMonth()  + "   Time: " + date.getHour() + ":"+ (date.getMinute()<10?"0":"") + date.getMinute();
                        doctorAppString.add(newDoctorAppointment2);
                        //System.out.println("The string is: " + newDoctorAppointment2);
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
        Patient patient = getPatientById(patientId);
        //Clinic clinic = patient.getClinic();   sara changed it in order to get the right clinic
        Clinic clinic = doctorAppointment.getClinic();
        doctorAppointment.setClinic(clinic);
        doctorAppointment.setDoctor(doctor);
        doctorAppointment.setPatient(patient);
        List<DoctorAppointment> doctorAppointments = new LinkedList<>();
        doctorAppointments.add(doctorAppointment);
        List<Patient> firstPatient = new LinkedList<>();
        firstPatient.add(patient);
        List<Doctor> firstDoctor = new LinkedList<>();
        firstDoctor.add(doctor);
        //connect app to clinic
        if (clinic.getDoctorAppointments().size() == 0)
            clinic.setDoctorAppointments(doctorAppointments);
        else
            clinic.getDoctorAppointments().add(doctorAppointment);
        //connect app to doctor
        if (doctor.getAppointments().size() == 0) {
            doctor.setAppointments(doctorAppointments);
        } else {
            doctor.getAppointments().add(doctorAppointment);
        }
        //connect app to patient
        if (patient.getDoctorAppointments().size() == 0)
            patient.setDoctorAppointments(doctorAppointments);
        else
            patient.getDoctorAppointments().add(doctorAppointment);
//        //connect doctor to patient
//        int exist = 0;
//        if (patient.getDoctors().size() == 0) {
//            patient.setDoctors(firstDoctor);
//            session.saveOrUpdate(doctor);
//        }
//        else
//        {
//             exist = 0;
//            for (Doctor element : patient.getDoctors())
//                if(element.getId()==doctor.getId())
//            {
//                exist=1;
//                System.out.println("is exist");
//            }
//            if (exist==0)
//            {
//                System.out.println("not exist");
//                patient.getDoctors().add(doctor);
//                session.saveOrUpdate(doctor);
//            }
//             }
//        //connect patient to doctor
//        if(doctor.getPatients().size()==0) {
//            doctor.setPatients(firstPatient);
//            session.saveOrUpdate(patient);
//        }
//        else
//        {
//            exist=0;
//            for (Patient element : doctor.getPatients())
//                if (element.getId() == patient.getId())
//            {
//                exist=1;
//                System.out.println("is exist");
//            }
//            if (exist==0){
//                System.out.println("not exist");
//                doctor.getPatients().add(patient);
//                session.saveOrUpdate(patient);
//            }
//
//        }



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

    public static OpenOrCloseClinic checkIfCISOpen(String clinicName)throws Exception{
        Clinic clinic=getClinicByName(clinicName);
        OpenOrCloseClinic openOrCloseClinic=new OpenOrCloseClinic();
        openOrCloseClinic.setClinicName(clinicName);
        openOrCloseClinic.setOpenOrclose(clinic.getClinicManager().isOpenORclose());
        return openOrCloseClinic;
    }

    public static void CloseClinic(int clinicId)throws Exception
    {
        Clinic clinic=getClinicById(clinicId);
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        clinic.getClinicManager().setOpenORclose(false);
        session.saveOrUpdate(clinic.getClinicManager());
        session.flush();
        session.getTransaction().commit();
        if (session != null)
            session.close();

    }
    public static void openClinic(int clinicId)throws Exception
    {
        Clinic clinic=getClinicById(clinicId);
        ClinicManager clinicManager=clinic.getClinicManager();
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        sendReminderEmail(clinic);
        clinicManager.setOpenORclose(true);
        session.saveOrUpdate(clinicManager);
        session.saveOrUpdate(clinic);
        session.flush();
        session.getTransaction().commit();
        if (session != null)
            session.close();

    }

    public static void sendReminderEmail(Clinic clinic) throws Exception {
        LocalDate tomorrowDate = LocalDate.now().plusDays(1);
        int clinicId=clinic.getId();
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
        List<DoctorAppointment> NotDonedoctorAppointments=new LinkedList<>();
        List<String> stringApp=new LinkedList<>();
        String newString;
        if(doctorAppointments.size() != 0){
            for (DoctorAppointment doctorAppointment : doctorAppointments) {
                if (!doctorAppointment.isDone())
                {
                    newString="AppointmentType: "+doctorAppointment.getAppointmentType()+"\n"
                         +"Date: "+ doctorAppointment.getDate().toLocalDate()+"  Time: "+doctorAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                         +"With doctor: "+doctorAppointment.getDoctor().getFirstName()+" "+doctorAppointment.getDoctor().getLastName()+"\n"
                         +"At clinic: "+doctorAppointment.getClinic().getClinicType();
                stringApp.add(newString);
                NotDonedoctorAppointments.add(doctorAppointment);
                }
            }
        }else {
            newString="There is not Scheduled Appointment!";
            stringApp.add(newString);
        }
        ScheduledApp scheduledApp=new ScheduledApp(stringApp,"Doctor");
        scheduledApp.setDoctorAppointments(NotDonedoctorAppointments);
        return (scheduledApp);
    }

    private static ScheduledApp getLaboratoryFactsApp(Patient patient) throws Exception{
        List<LaboratoryFactsAppointment> laboratoryFactsAppointments=patient.getLaboratoryFactsAppointments();
        List<LaboratoryFactsAppointment> NOTDONElaboratoryFactsAppointments=new LinkedList<>();
        List<String> stringApp=new LinkedList<>();
        String newString;
        if(laboratoryFactsAppointments.size() != 0){
            for (LaboratoryFactsAppointment laboratoryFactsAppointment : laboratoryFactsAppointments) {
                if(!laboratoryFactsAppointment.isDone()) {
                    newString = "AppointmentType: " + laboratoryFactsAppointment.getAppointmentType() + "\n"
                            + "Date: " + laboratoryFactsAppointment.getDate().toLocalDate() + "  Time: " + laboratoryFactsAppointment.getDate().toLocalTime().withSecond(0) + "\n"
                            + "At clinic: " + laboratoryFactsAppointment.getClinic().getClinicType();
                    stringApp.add(newString);
                    NOTDONElaboratoryFactsAppointments.add(laboratoryFactsAppointment);
                }
            }
        }else {
            newString="There is not Scheduled Appointment!";
            stringApp.add(newString);
        }
        ScheduledApp scheduledApp=new ScheduledApp(stringApp,"LaboratoryFacts");
        scheduledApp.setLaboratoryFactsAppointments(NOTDONElaboratoryFactsAppointments);
        return (scheduledApp);
    }

    private static ScheduledApp getNurseApp(Patient patient) throws Exception{
        List<NurseAppointment> nurseAppointments=patient.getNurseAppointments();
        List<NurseAppointment> NOTDONEnurseAppointments=new LinkedList<>();
        List<String> stringApp=new LinkedList<>();
        String newString;
        if(nurseAppointments.size() != 0){
            for (NurseAppointment nurseAppointment : nurseAppointments) {
                if (!nurseAppointment.isDone())
                {
                newString="AppointmentType: "+nurseAppointment.getAppointmentType()+"\n"
                         +"Date: "+ nurseAppointment.getDate().toLocalDate()+"  Time: "+nurseAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                         +"At clinic: "+nurseAppointment.getClinic().getClinicType();
                stringApp.add(newString);
                NOTDONEnurseAppointments.add(nurseAppointment);
                }
            }
        }else {
            newString="There is not Scheduled Appointment!";
            stringApp.add(newString);
        }
        ScheduledApp scheduledApp=new ScheduledApp(stringApp,"Nurse");
        scheduledApp.setNurseAppointments(NOTDONEnurseAppointments);
        return (scheduledApp);
    }

    private static ScheduledApp getCoronaTestApp(int patientId) throws Exception{
        List<CoronaTestAppointment> coronaTestAppointments=getCoronaTestPatientById(patientId);
        List<CoronaTestAppointment> NOTDONEcoronaTestAppointments=new LinkedList<>();
        List<String> stringApp=new LinkedList<>();
        String newString;
        if(coronaTestAppointments.size() != 0){
            for (CoronaTestAppointment coronaTestAppointment : coronaTestAppointments) {
                if (!coronaTestAppointment.isDone())
                {
                newString="AppointmentType: "+coronaTestAppointment.getAppointmentType()+"\n"
                         +"Date: "+ coronaTestAppointment.getDate().toLocalDate()+"  Time: "+coronaTestAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                         +"At clinic: "+coronaTestAppointment.getClinic().getClinicType();
                stringApp.add(newString);
                NOTDONEcoronaTestAppointments.add(coronaTestAppointment);
                }
            }
        }else {
            newString="There is not Scheduled Appointment!";
            stringApp.add(newString);
        }
        ScheduledApp scheduledApp=new ScheduledApp(stringApp,"CoronaTest");
        scheduledApp.setCoronaTestAppointments(NOTDONEcoronaTestAppointments);

        return (scheduledApp);
    }

    private static ScheduledApp getVaccineApp(int patientId) throws Exception{
        List<VaccineAppointment> vaccineAppointments=getVaccinePatientById(patientId);
        List<VaccineAppointment> NOTDONEvaccineAppointments=new LinkedList<>();
        List<String> stringApp=new LinkedList<>();
        String newString;
        if(vaccineAppointments.size() != 0){
            for (VaccineAppointment vaccineAppointment : vaccineAppointments) {
                if (!vaccineAppointment.isDone())
                {
                newString="AppointmentType: "+vaccineAppointment.getAppointmentType()+"\n"
                        +"Date: "+ vaccineAppointment.getDate().toLocalDate()+"  Time: "+vaccineAppointment.getDate().toLocalTime().withSecond(0)+"\n"
                        +"At clinic: "+vaccineAppointment.getClinic().getClinicType();
                stringApp.add(newString);
                NOTDONEvaccineAppointments.add(vaccineAppointment);
                }
            }
        }else {
            newString="There is not Scheduled Appointment!";
            stringApp.add(newString);
        }
        ScheduledApp scheduledApp=new ScheduledApp(stringApp,"Vaccine");
        scheduledApp.setVaccineAppointments(NOTDONEvaccineAppointments);
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
                            newCoronaApp = "Date: " + date.getDayOfMonth() +"-" + date.getMonth()  + "   Time: " + date.getHour() + ":"+ (date.getMinute()<10?"0":"") + date.getMinute();
                            AppString.add(newCoronaApp);
                            System.out.println("The string is: " + newCoronaApp);
                        }
                    } else {
                        coronaTestAppointment = new CoronaTestAppointment("CoronaTest", date, patient, clinic);
                        newCoronaTestAppointments.add(coronaTestAppointment);
                        newCoronaApp = "Date: " + date.getDayOfMonth() +"-" + date.getMonth()  + "   Time: " + date.getHour() + ":"+ (date.getMinute()<10?"0":"") + date.getMinute();
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
                            newVaccineString =  "Date: " + date.getDayOfMonth() +"-" + date.getMonth()  + "   Time: " + date.getHour() + ":"+ (date.getMinute()<10?"0":"") +  date.getMinute();
                            AppString.add(newVaccineString);
                            System.out.println("The string is: " + newVaccineString);
                        }
                    }else {
                        vaccineAppointment = new VaccineAppointment(AppType, date,patient,clinic);
                        vaccineAppointments.add(vaccineAppointment);
                        newVaccineString =  "Date: " + date.getDayOfMonth() +"-" + date.getMonth()  + "   Time: " + date.getHour() + ":"+ (date.getMinute()<10?"0":"") + date.getMinute();
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
    // this func returns the pro doc by his name an clinic

    /**
     * this function returns the
     * @param doctor  doctor
     * @param patient patient - we need it in case the patient choose a free app in future
     * @param type the type of the doctor appointment
     * @param clinic clinic that the appointment scheduled at
     * @return returns a DoctorApp object witch contains all free appointments of the doctor
     * @throws Exception
     */
    public static DoctorApp getFreeProDoctorApp(Doctor doctor,Patient patient,String type , String clinic) throws Exception {

        List<DoctorAppointment> doctorAppointments = doctor.getAppointments();
        LocalTime[][] DoctorReceptionTime = doctor.getRecepByClinic(clinic).getActiveTime();
        LocalDateTime date = LocalDateTime.now().withMinute(0);
        LocalDateTime now = LocalDateTime.now();
        //LocalDateTime date = LocalDateTime.of(2022,Month.JANUARY, 29, 10, 00);
        LocalDateTime after3months = LocalDateTime.now().plusMonths(3);
        List<DoctorAppointment> doctorAppList = new LinkedList<>();
        List<String> doctorAppString = new LinkedList<>();
        DoctorAppointment doctorAppointment1;
        String newDoctorAppointment2;
        Clinic proClinic = getClinicByName(clinic);
        //System.out.println("th pro clinic choice is : "+proClinic.getClinicType());
        int flagIsAvailable=0;
        while (date.isBefore(after3months)) {
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
                            doctorAppointment1 = new DoctorAppointment(type, date, doctor, patient, proClinic);
                            doctorAppList.add(doctorAppointment1);
                            newDoctorAppointment2 =  "Date: " + date.getDayOfMonth() +"-" + date.getMonth()  + "   Time: " + date.getHour() + ":" + (date.getMinute()<10?"0":"") + date.getMinute();
                            doctorAppString.add(newDoctorAppointment2);
                            //System.out.println("The string is: " + newDoctorAppointment2);
                        }
                    }else {
                        doctorAppointment1 = new DoctorAppointment(type, date, doctor, patient, proClinic);
                        doctorAppList.add(doctorAppointment1);
                        newDoctorAppointment2 =  "Date: " + date.getDayOfMonth() +"-" + date.getMonth()  + "   Time: " + date.getHour() + ":" + (date.getMinute()<10?"0":"") + date.getMinute();
                        doctorAppString.add(newDoctorAppointment2);
                        //System.out.println("The string is: " + newDoctorAppointment2);
                    }
                }
            }
            flagIsAvailable=0;
            date = date.plusMinutes(20);
        }

        return (new DoctorApp(doctorAppString, doctorAppList,patient,doctor));
    }

    /**
     *
     * @param major a string of doctor major
     * @param id_P string of patient id
     * @return ProDoctorsList witch contains sorted professional doctors
     * @throws Exception
     */
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
            Patient patient = getPatientById(Integer.parseInt(id_P));
            List<DoctorAppointment> docApp = patient.getDoctorAppointments();
        int Sdoc = docApp.size();
        for (int i=0 ; i<Sdoc; i++) {
            if (!(docApp.get(i).getAppointmentType().equalsIgnoreCase(major)) || !docApp.get(i).isDone())
            {
                docApp.remove(i);
                Sdoc--;
                i--;

            }
        }
            docApp.sort(Comparator.comparing(DoctorAppointment :: getDate).reversed());
            LinkedList<Doctor> finalDocList = new LinkedList<>();
            int exist=0;
            for (DoctorAppointment element : docApp)
            {
                exist=0;
                for (Doctor docelement : finalDocList)
                {
                    if (docelement.getId()==element.getDoctor().getId())
                        exist=1;
                }
                if (exist==0)
                    finalDocList.add(element.getDoctor());
            }
            for (Doctor element : doctors)
            {
                exist=0;
                for (Doctor docelement : finalDocList)
                {
                    if (docelement.getId()==element.getId())
                        exist=1;
                }
                if (exist==0)
                    finalDocList.add(element);
            }


            ProDoctorsList SortedDoctors = new ProDoctorsList(finalDocList);
            if (session != null) {
                session.close();

            }
            return SortedDoctors;
        }

    /**
     * this function used by the appointments
     * @param p patient
     * @param c clinic
     * @return the closest appointments of today's appointments
     */
        public static String getClosestApp(Patient p , Clinic c)
        {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            List<DoctorAppointment> docApps = p.getDoctorAppointments();
            List<VaccineAppointment> vaccine = null;
            try {
                vaccine = getVaccinePatientById(p.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            List<CoronaTestAppointment> corona = null;
            try {
                corona = getCoronaTestPatientById(p.getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            int Sdoc = docApps.size();
            int Svaccine = vaccine.size();
            int Scorona = corona.size();
            String details = "$";
            LocalDateTime date = LocalDateTime.now();
            int Qlength = 1 ;
            String doc_num = "";
            int i;
            String corona_num = "";
            String vaccine_num = "";
            // removing the appointments of other clinics and other dates
            for(i=0 ; i< Sdoc ; i++){
                if(docApps.get(i).getClinic().getId() != c.getId() || docApps.get(i).getDate().getYear() != date.getYear() || docApps.get(i).getDate().getDayOfYear() != date.getDayOfYear() ||! docApps.get(i).getAppNum().equals("initial val")){
                    {
                        docApps.remove(i);
                        Sdoc--;
                        i--;
                    }
                }
            }

            for(i=0 ; i< Scorona ; i++){
                if(corona.get(i).getClinic().getId() != c.getId() || corona.get(i).getDate().getYear() != date.getYear() || corona.get(i).getDate().getDayOfYear() != date.getDayOfYear()||! corona.get(i).getAppNum().equals("initial val")){
                    corona.remove(i);
                    Scorona--;
                    i--;
                }
            }
            if(Svaccine!=0){
                for(i=0 ; i< Svaccine ; i++){

                    if(vaccine.get(i).getClinic().getId() != c.getId() || vaccine.get(i).getDate().getYear() != date.getYear() || vaccine.get(i).getDate().getDayOfYear() != date.getDayOfYear() ||! vaccine.get(i).getAppNum().equals("initial val")){
                        vaccine.remove(i);
                        Svaccine--;
                        i--;
                    }
                }}
            docApps.sort(Comparator.comparing(DoctorAppointment :: getDate));
            vaccine.sort(Comparator.comparing(VaccineAppointment :: getDate));
            corona.sort(Comparator.comparing(CoronaTestAppointment :: getDate));

            int returned = Checkmin(docApps , vaccine, corona );
            System.out.println("the returned val from check min is :"+returned);
            if(Sdoc!=0 && returned==1 ){
                if(docApps.get(0).getDoctor().getRole().startsWith("ProfessionalDoctor")){
                    Qlength=20;
                }else
                    Qlength=15;
                doc_num = getSAppnum(docApps.get(0) , Qlength , docApps.get(0).getDate() , docApps.get(0).getClinic());
                details = "the doctor name is : " + docApps.get(0).getDoctor().getFirstName()+"\n"
                        + "the scheduled date is " + dtf.format(docApps.get(0).getDate())+"\n"
                        + "your number is :" + doc_num;
            }
            if(Scorona!=0 && returned==2) {
                System.out.println("here i am at Scorona");
                SetCoronaTestAppAsDone(corona.get(0));
                corona_num = getSAppnum(corona.get(0) , 10 , corona.get(0).getDate() , corona.get(0).getClinic());
                details = "the scheduled date is " + dtf.format(corona.get(0).getDate())
                        + "\n your number is :" + corona_num;
            }
            if(Svaccine!=0 && returned==3){
                System.out.println("here i am at Scorona");
                SetVaccineAppAsDone(p,vaccine.get(0));
                vaccine_num = getSAppnum(vaccine.get(0) , 10 , vaccine.get(0).getDate() , vaccine.get(0).getClinic());
                details =  "the scheduled date is " + dtf.format(vaccine.get(0).getDate())
                        + "\n your number is :" + vaccine_num;
            }

            if (session != null) {
                session.close();

            }
            System.out.println(details);
            return details ;
        }

    /**
     * we needed this function because of the null cases
     * @param docApps doctor appointments
     * @param vaccine vaccine appointments
     * @param corona corona appointments
     * @return the min appointment from the three lists
     */
        public  static int Checkmin(List<DoctorAppointment> docApps, List<VaccineAppointment> vaccine,List<CoronaTestAppointment> corona){
            int Sdoc = docApps.size();
            int Svaccine = vaccine.size();
            int Scorona = corona.size();

            ///// just one is not empty
            if (Sdoc!=0 && Scorona==0 && Svaccine==0)
            {
                //the app is docApp
                return 1;
            }
            else if(Sdoc==0 && Scorona!=0 && Svaccine==0)
            {
                //the app is corona
                return 2;
            }
            else if(Sdoc==0 && Scorona==0 && Svaccine!=0)
            {
                //the app is vaccine
               return 3;
            }


            ///// just two is not empty
            if(Sdoc==0 && Scorona!=0 && Svaccine!=0) {
                if (corona.get(0).getDate().isBefore(vaccine.get(0).getDate()))
                {
                    //the App is corona
                    return 2;
                }
                else if (vaccine.get(0).getDate().isBefore(corona.get(0).getDate()))
                {
                    //the app is vaccine
                    return 3;
                }
            }
            else if (Sdoc!=0 && Scorona==0 && Svaccine!=0)
            {
                if (docApps.get(0).getDate().isBefore(vaccine.get(0).getDate()))
                {
                    //the App is docApp
                    return 1;
                }
                else if (vaccine.get(0).getDate().isBefore(docApps.get(0).getDate()))
                {
                    //the app is vaccine
                    return 3;
                }
            }
            else if (Sdoc!=0 && Scorona!=0 && Svaccine==0)
            {
                if (docApps.get(0).getDate().isBefore(corona.get(0).getDate()))
                {
                    //the App is docApp
                    return 1;
                }
                else if (corona.get(0).getDate().isBefore(docApps.get(0).getDate()))
                {
                    //the app is corona
                    return 2;
                }
            }
            ////// all of them are not empty
            else if (Sdoc!=0 && Scorona!=0 && Svaccine!=0) {
                if (docApps.get(0).getDate().isBefore(corona.get(0).getDate()) && docApps.get(0).getDate().isBefore(vaccine.get(0).getDate())) {
                    /// the app is docApp
                    return 1;
                }
                if (corona.get(0).getDate().isBefore(docApps.get(0).getDate()) && corona.get(0).getDate().isBefore(vaccine.get(0).getDate())) {
                    /// the app is corona
                    return 2;
                }
                if (vaccine.get(0).getDate().isBefore(corona.get(0).getDate()) && vaccine.get(0).getDate().isBefore(docApps.get(0).getDate())) {
                    /// the app is vaccine
                    return 3;
                }
            }
        return 0;
        }

    /**
     * this function calculates the appointment number returns it and update it to the database
     * @param App the scheduled app
     * @param Qlength length of the appointment
     * @param date
     * @param clinic clinic that the appointment scheduled at
     * @return the calculated appointment number
     */
    public static String getSAppnum (Appointment App , int Qlength , LocalDateTime date , Clinic clinic)
    {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        LocalTime now = LocalTime.now();
        int MOfApp = date.getMinute() + (60 * date.getHour());
        System.out.println(MOfApp);
        int nowM = now.getMinute() + (60 * now.getHour());
        System.out.println(nowM);
        System.out.println(now.getHour());
        int day = date.getDayOfWeek().getValue();
        int MOfAc = clinic.getActivityTime()[0][day%7].getMinute() +(60* clinic.getActivityTime()[0][day-1].getHour());
        int Appnum = 0 ;
        int dif =MOfApp - MOfAc ;
        String num ="";

        if(nowM > MOfApp){
            dif= dif + nowM-MOfApp;
            Appnum = dif/Qlength ;
            Appnum+=3;
            num = String.valueOf(Appnum) ;
            num = num + "A";
            App.setAppNum(num);
        }else{
            Appnum = dif / Qlength ;
            Appnum++ ;
            num = String.valueOf(Appnum) ;
            num = num + "B";
            App.setAppNum(num);
        }
        session.saveOrUpdate(App);
        session.flush();
        session.getTransaction().commit();
        if (session != null) {
            session.close();

        }
        System.out.println("the num is: " + num);
        return num;
    }
        public static int NumAppAndSetNurseAppointment(Patient patient , Clinic clinic,NurseAppointment nurseApp) throws Exception {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            int AppNum=(clinic.getNurseAppointments().size()+1)%144;
            nurseApp.setClinic(clinic);
            nurseApp.setPatient(patient);
            nurseApp.setAppointmentType("NurseApp");
            nurseApp.setDate(LocalDateTime.now());
            nurseApp.setDone(false);
            nurseApp.setAppNum(String.valueOf(AppNum));
            session.saveOrUpdate(nurseApp);
//            long id =nurseApp.getId();
//            int int_id= (Math.toIntExact(id))%144;
//            nurseApp.setAppNum(String.valueOf(int_id));


            List<NurseAppointment> NurseAppointments = new LinkedList<>();
            NurseAppointments.add(nurseApp);
            //connect app to clinic
            if (clinic.getNurseAppointments().size() == 0)
                clinic.setNurseAppointments(NurseAppointments);
            else
                clinic.getNurseAppointments().add(nurseApp);
            //connect app to patient
            if (patient.getNurseAppointments().size() == 0)
                patient.setNurseAppointments(NurseAppointments);
            else
                patient.getNurseAppointments().add(nurseApp);


            session.saveOrUpdate(nurseApp);
            session.flush();
            session.getTransaction().commit();
            if (session != null)
                session.close();
            return AppNum;
        }

        public static int NumAppAndSetLabAppointment(Patient patient , Clinic clinic, LaboratoryFactsAppointment LabApp) throws Exception {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            int AppNum=(clinic.getNurseAppointments().size()+1)%144;
            LabApp.setClinic(clinic);
            LabApp.setPatient(patient);
            LabApp.setAppointmentType("LabApp");
            LabApp.setDate(LocalDateTime.now());
            LabApp.setDone(false);
            LabApp.setAppNum(String.valueOf(AppNum));
            session.saveOrUpdate(LabApp);
//            long id =LabApp.getId();
//            int int_id= (Math.toIntExact(id))%144;
//            LabApp.setAppNum(String.valueOf(int_id));


            List<LaboratoryFactsAppointment> LabAppointments = new LinkedList<>();
            LabAppointments.add(LabApp);
            //connect app to clinic
            if (clinic.getLaboratoryFactsAppointments().size() == 0)
                clinic.setLaboratoryFactsAppointments(LabAppointments);
            else
                clinic.getLaboratoryFactsAppointments().add(LabApp);

            //connect app to patient
            if (patient.getNurseAppointments().size() == 0)
                patient.setLaboratoryFactsAppointments(LabAppointments);
            else
                patient.getLaboratoryFactsAppointments().add(LabApp);

            session.saveOrUpdate(LabApp);
            session.flush();
            session.getTransaction().commit();
            if (session != null)
                session.close();
            return AppNum;
        }
        public static DoneAppBus SetNurseAppAsDone(Patient patient, Nurse nurse, Clinic clinic, int AppId){
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
            DoneAppBus appIsDoneBus=new DoneAppBus();
            NurseAppointment selectedApp=new NurseAppointment();
            List<NurseAppointment> nurseApps = clinic.getNurseAppointments();
            for (NurseAppointment element : nurseApps){
                if (element.getId()==AppId){
                    selectedApp=element;
                }
            }
            selectedApp.setDone(true);
            selectedApp.setRealTime(LocalDateTime.now());
            selectedApp.setNurse(nurse);

            List<NurseAppointment> nurseAppointments=new LinkedList<>();
        nurseAppointments.add(selectedApp);
        //connect app to nurse
        if (nurse.getAppointments().size() == 0) {
            nurse.setAppointments(nurseAppointments);
        } else {
            nurse.getAppointments().add(selectedApp);
        }
        session.saveOrUpdate(selectedApp);
//        List<Patient> firstPatient = new LinkedList<>();
//        firstPatient.add(patient);
//        List<Nurse> firstNurse = new LinkedList<>();
//        firstNurse.add(nurse);
//
//
//        //connect nurse to patient
//        int exist = 0;
//        if (patient.getNurses().size() == 0) {
//            patient.setNurses(firstNurse);
//            session.saveOrUpdate(nurse);
//        }
//        else
//        {
//            exist = 0;
//            for (Nurse element : patient.getNurses())
//                if(element.getId()==nurse.getId())
//                {
//                    exist=1;
//                    System.out.println("is exist");
//                }
//            if (exist==0)
//            {
//                System.out.println("not exist");
//                patient.getNurses().add(nurse);
//                session.saveOrUpdate(nurse);
//            }
//        }
//        //connect patient to nurse
//        if(nurse.getPatients().size()==0) {
//            nurse.setPatients(firstPatient);
//            session.saveOrUpdate(patient);
//        }
//        else
//        {
//            exist=0;
//            for (Patient element : nurse.getPatients())
//                if (element.getId() == patient.getId())
//                {
//                    exist=1;
//                    System.out.println("is exist");
//                }
//            if (exist==0){
//                System.out.println("not exist");
//                nurse.getPatients().add(patient);
//                session.saveOrUpdate(patient);
//            }

//      }
        session.saveOrUpdate(clinic);
        List<Clinic> UpdatedClinic = new LinkedList<>();
        UpdatedClinic.add(clinic);
        nurse.setClinicList(UpdatedClinic);
        session.flush();
        session.getTransaction().commit();
        if (session != null)
            session.close();
        appIsDoneBus.setNurse(nurse);
        return appIsDoneBus;
    }
    public static DoneAppBus SetLabAppAsDone(Patient patient, LaboratoryFacts labfact, Clinic clinic, int AppId){
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        DoneAppBus appIsDoneBus=new DoneAppBus();
        LaboratoryFactsAppointment selectedApp=new LaboratoryFactsAppointment();
        List<LaboratoryFactsAppointment> LabApps = clinic.getLaboratoryFactsAppointments();
        for (LaboratoryFactsAppointment element : LabApps){
            if (element.getId()==AppId){
                selectedApp=element;
            }
        }
        selectedApp.setDone(true);
        selectedApp.setRealTime(LocalDateTime.now());
        selectedApp.setLaboratoryFacts(labfact);

        List<LaboratoryFactsAppointment> laboratoryFactsAppointments=new LinkedList<>();
        laboratoryFactsAppointments.add(selectedApp);
        //connect app to Lab fact
        if (labfact.getAppointments().size() == 0) {
            labfact.setAppointments(laboratoryFactsAppointments);
        } else {
            labfact.getAppointments().add(selectedApp);
        }
        session.saveOrUpdate(selectedApp);
//        List<Patient> firstPatient = new LinkedList<>();
//        firstPatient.add(patient);
//        List<LaboratoryFacts> firstlab = new LinkedList<>();
//        firstlab.add(labfact);
        //connect Lab fact to patient
//        int exist = 0;
//        if (patient.getLaboratoryFacts().size() == 0) {
//            patient.setLaboratoryFacts(firstlab);
//            session.saveOrUpdate(patient);
//        }
//        else
//        {
//            exist = 0;
//            for (LaboratoryFacts element : patient.getLaboratoryFacts())
//                if(element.getId()==labfact.getId())
//                {
//                    exist=1;
//                    System.out.println("is exist");
//                }
//            if (exist==0)
//            {
//                System.out.println("not exist");
//                patient.getLaboratoryFacts().add(labfact);
//                session.saveOrUpdate(patient);
//            }
//        }
        //connect patient to Lab fact
//        if(labfact.getPatients().size()==0) {
//            labfact.setPatients(firstPatient);
//            session.saveOrUpdate(labfact);
//        }
//        else
//        {
//            exist=0;
//            for (Patient element : labfact.getPatients())
//                if (element.getId() == patient.getId())
//                {
//                    exist=1;
//                    System.out.println("is exist");
//                }
//            if (exist==0){
//                System.out.println("not exist");
//                labfact.getPatients().add(patient);
//                session.saveOrUpdate(labfact);
//            }
//
//        }

        session.saveOrUpdate(clinic);

        List<Clinic> UpdatedClinic = new LinkedList<>();
        UpdatedClinic.add(clinic);
        labfact.setClinicList(UpdatedClinic);

        session.flush();
        session.getTransaction().commit();
        if (session != null)
            session.close();
        appIsDoneBus.setLabFact(labfact);
        return appIsDoneBus;
    }

    /**
     * this function sets the app as done when tha doctor calls a patient
     * @param patient
     * @param doctor
     * @param clinic
     * @param AppId
     * @return event bus to update the current window of showed patient
     */
    public static DoneAppBus SetDoctorAppAsDone(Patient patient, Doctor doctor, Clinic clinic, int AppId){
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        DoneAppBus appIsDoneBus=new DoneAppBus();
        List<Patient> firstPatient = new LinkedList<>();
        firstPatient.add(patient);
        List<Doctor> firstDoctor = new LinkedList<>();
        firstDoctor.add(doctor);
        //int exist;
        DoctorAppointment selectedAppinDoc=new  DoctorAppointment();
        List< DoctorAppointment> docAppsInDoc = doctor.getAppointments();
        for ( DoctorAppointment element : docAppsInDoc){
            if (element.getId()==AppId){
                selectedAppinDoc=element;
            }
        }
        if(selectedAppinDoc.getAppNum().equalsIgnoreCase("initial val"))
        {
            appIsDoneBus.setPatientISHere("NotHere");
        }
        else {
            selectedAppinDoc.setDone(true);
            selectedAppinDoc.setRealTime(LocalDateTime.now());
            session.saveOrUpdate(selectedAppinDoc);
            //connect doctor to patient
            int exist = 0;
            if (patient.getDoctors().size() == 0) {
                patient.setDoctors(firstDoctor);
                session.saveOrUpdate(doctor);
            } else {
                exist = 0;
                for (Doctor element : patient.getDoctors())
                    if (element.getId() == doctor.getId()) {
                        exist = 1;
                        System.out.println("is exist");
                    }
                if (exist == 0) {
                    System.out.println("not exist");
                    patient.getDoctors().add(doctor);
                    session.saveOrUpdate(doctor);
                }
            }

            if (doctor.getPatients().size() == 0) {
                doctor.setPatients(firstPatient);
                session.saveOrUpdate(patient);
            } else {
                exist = 0;
                for (Patient element : doctor.getPatients())
                    if (element.getId() == patient.getId()) {
                        exist = 1;
                        System.out.println("is exist");
                    }
                if (exist == 0) {
                    System.out.println("not exist");
                    doctor.getPatients().add(patient);
                    session.saveOrUpdate(patient);
                }

            }
            session.saveOrUpdate(doctor);
            // session.saveOrUpdate(clinic);
//        List<Clinic> UpdatedClinic = new LinkedList<>();
//        UpdatedClinic.add(clinic);
//        doctor.setClinicList(UpdatedClinic);
            session.flush();
            session.getTransaction().commit();
        }
        if (session != null)
            session.close();
        appIsDoneBus.setDoctor(doctor);
        return appIsDoneBus;
    }
    public static void SetCoronaTestAppAsDone(CoronaTestAppointment coronaTestAppointment){
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        coronaTestAppointment.setDone(true);
        coronaTestAppointment.setRealTime(LocalDateTime.now());
        session.saveOrUpdate(coronaTestAppointment);
        // session.saveOrUpdate(clinic);
//        List<Clinic> UpdatedClinic = new LinkedList<>();
//        UpdatedClinic.add(clinic);
//        doctor.setClinicList(UpdatedClinic);
        session.flush();
        session.getTransaction().commit();
        if (session != null)
            session.close();
    }
    public static void SetVaccineAppAsDone(Patient patient,VaccineAppointment vaccineAppointment){
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        vaccineAppointment.setDone(true);
        vaccineAppointment.setRealTime(LocalDateTime.now());
        session.saveOrUpdate(vaccineAppointment);
        int numOfVaccine=0;
        if (vaccineAppointment.getAppointmentType().equalsIgnoreCase("CoronaVaccine"))
        {
            numOfVaccine = (patient.getNumOfVaccine())+1;
            patient.setNumOfVaccine(numOfVaccine);
            session.saveOrUpdate(patient);
        }
        // session.saveOrUpdate(clinic);
//        List<Clinic> UpdatedClinic = new LinkedList<>();
//        UpdatedClinic.add(clinic);
//        doctor.setClinicList(UpdatedClinic);
        session.flush();
        session.getTransaction().commit();
        if (session != null)
            session.close();
    }

    public static void connectFactPatient (Patient patient,LaboratoryFacts labfact){
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        int exist = 0;
        List<Patient> firstPatient = new LinkedList<>();
        firstPatient.add(patient);
        List<LaboratoryFacts> firstlab = new LinkedList<>();
        firstlab.add(labfact);
        //connect Lab fact to patient
        if (patient.getLaboratoryFacts().size() == 0) {
            patient.setLaboratoryFacts(firstlab);
            //session.saveOrUpdate(patient);
        }
        else
        {
            exist = 0;
            for (LaboratoryFacts element : patient.getLaboratoryFacts())
                if(element.getId()==labfact.getId())
                {
                    exist=1;
                    System.out.println("is exist");
                }
            if (exist==0)
            {
                System.out.println("not exist");
                patient.getLaboratoryFacts().add(labfact);
                //session.saveOrUpdate(patient);
            }
        }
        //connect patient to Lab fact
        if(labfact.getPatients().size()==0) {
            labfact.setPatients(firstPatient);
            session.saveOrUpdate(labfact);
        }
        else
        {
            exist=0;
            for (Patient element : labfact.getPatients())
                if (element.getId() == patient.getId())
                {
                    exist=1;
                    System.out.println("is exist");
                }
            if (exist==0){
                System.out.println("not exist");
                labfact.getPatients().add(patient);
                session.saveOrUpdate(labfact);
            }

        }
        session.flush();
        session.getTransaction().commit();
        if (session != null)
            session.close();

    }

    }

