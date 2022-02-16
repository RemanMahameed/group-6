package il.cshaifasweng.OCSFMediatorExample.server.DataControl;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.swing.event.ListDataListener;

public class DataClass {

    private static Session session;

    public static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();

        // Add ALL of your entities here. You can also try adding a whole package.

          configuration.addAnnotatedClass(Patient.class);
          configuration.addAnnotatedClass(Clinic.class);
          configuration.addAnnotatedClass(ClinicManager.class);
          configuration.addAnnotatedClass(Doctor.class);
          configuration.addAnnotatedClass(DoctorAppointment.class);
          configuration.addAnnotatedClass(HMO.class);
          configuration.addAnnotatedClass(HmoManager.class);
          configuration.addAnnotatedClass(LaboratoryFacts.class);
          configuration.addAnnotatedClass(LaboratoryFactsAppointment.class);
          configuration.addAnnotatedClass(Nurse.class);
          configuration.addAnnotatedClass(NurseAppointment.class);
          configuration.addAnnotatedClass(ReceptionTime.class);
          configuration.addAnnotatedClass(CoronaTestAppointment.class);
          configuration.addAnnotatedClass(VaccineAppointment.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static List<Patient> getAllPatients() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        String query = "FROM Patient";
        List<Patient> result = session.createQuery(query).list();
        session.getTransaction().commit(); // Save everything.
        session.close();
        return result;
    }

    public static List<ClinicManager> getAllClinicManager() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        String query = "FROM ClinicManager";
        List<ClinicManager> result = session.createQuery(query).list();
        session.getTransaction().commit(); // Save everything.
        session.close();
        return result;
    }

    public static List<LaboratoryFacts> getAllLaboratoryFacts() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        String query = "FROM LaboratoryFacts";
        List<LaboratoryFacts> result = session.createQuery(query).list();
        session.getTransaction().commit(); // Save everything.
        session.close();
        return result;
    }

    public static List<Nurse> getAllNurse() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        String query = "FROM Nurse";
        List<Nurse> result = session.createQuery(query).list();
        session.getTransaction().commit(); // Save everything.
        session.close();
        return result;
    }

    public static List<Doctor> getAllDoctor() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        String query = "FROM Doctor";
        List<Doctor> result = session.createQuery(query).list();
        session.getTransaction().commit(); // Save everything.
        session.close();
        return result;
    }
    public static List<HmoManager> getAllHmoManger() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        String query = "FROM HmoManager";
        List<HmoManager> result = session.createQuery(query).list();
        session.getTransaction().commit(); // Save everything.
        session.close();
        return result;
    }
    public static List<Clinic> getAllClinic() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        String query = "FROM Clinic";
        List<Clinic> result = session.createQuery(query).list();
        session.getTransaction().commit(); // Save everything.
        session.close();
        return result;
    }
    public static List<CoronaTestAppointment> getAllCoronaTestAppointment() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        String query = "FROM CoronaTestAppointment";
        List<CoronaTestAppointment> result = session.createQuery(query).list();
        session.getTransaction().commit(); // Save everything.
        session.close();
        return result;
    }
    public static List<VaccineAppointment> getAllVaccineAppointment() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        String query = "FROM VaccineAppointment";
        List<VaccineAppointment> result = session.createQuery(query).list();
        session.getTransaction().commit(); // Save everything.
        session.close();
        return result;
    }
    public static Clinic getClinicById(int Id) throws Exception {

        List<Clinic> clinics=getAllClinic();
        for (Clinic clinic:clinics){
            if(clinic.getId()==Id)
                return clinic;
        }
        return new Clinic();
    }
    public static Clinic getClinicByName(String name) throws Exception {

        List<Clinic> clinics=getAllClinic();
        for (Clinic clinic:clinics){
            if(clinic.getClinicType().equals(name))
                return clinic;
        }
        return new Clinic();
    }
    public static Patient getPatientById(int Id) throws Exception {

        List<Patient> patients=getAllPatients();
        for (Patient patient:patients){
            if(patient.getId()==Id)
                return patient;
        }
        return new Patient();
    }
    public static Doctor getDoctorById(int Id) throws Exception {

        List<Doctor> doctors=getAllDoctor();
        for (Doctor doctor:doctors){
            if(doctor.getId()==Id)
                return doctor;
        }
        return new Doctor();
    }
    public static List<CoronaTestAppointment> getCoronaTestPatientById(int Id) throws Exception {

        List<CoronaTestAppointment> coronaTestAppointments=getAllCoronaTestAppointment();
        List<CoronaTestAppointment> coronaTestAppointmentList=new LinkedList<>();
        for (CoronaTestAppointment coronaTestAppointment:coronaTestAppointments){
            if(coronaTestAppointment.getPatient().getId()==Id)
                coronaTestAppointmentList.add(coronaTestAppointment);
        }
        return coronaTestAppointmentList;
    }
    public static List<VaccineAppointment> getVaccinePatientById(int Id) throws Exception {

        List<VaccineAppointment> vaccineAppointments=getAllVaccineAppointment();
        List<VaccineAppointment> vaccineAppointmentList=new LinkedList<>();
        for (VaccineAppointment vaccineAppointment:vaccineAppointments){
            if(vaccineAppointment.getPatient().getId()==Id)
                vaccineAppointmentList.add(vaccineAppointment);
        }
        return vaccineAppointmentList;
    }

    public static void generateNewData() throws Exception {

        //matrix of openingHours
        LocalTime openingHours = LocalTime.of(8,00);
        LocalTime closingHours = LocalTime.of(16,00);
        LocalTime begineReceptionTime = LocalTime.of(10,00);
        LocalTime endReceptionTime = LocalTime.of(15,00);
        LocalTime begineReceptionTime1 = LocalTime.of(8,00);
        LocalTime endReceptionTime1 = LocalTime.of(9,00);
        LocalTime[][] ActivityTime={
                {openingHours,openingHours,openingHours,openingHours,openingHours,openingHours,openingHours},
                {closingHours,closingHours,closingHours,closingHours,closingHours,closingHours,closingHours}};
        LocalTime[][] CoronaTestTime={
                {openingHours,openingHours,openingHours,openingHours,openingHours,openingHours,openingHours},
                {closingHours,closingHours,closingHours,closingHours,closingHours,closingHours,closingHours}};
        LocalTime[][] VaccineTime={
                {openingHours,openingHours,openingHours,openingHours,openingHours,openingHours,openingHours},
                {closingHours,closingHours,closingHours,closingHours,closingHours,closingHours,closingHours}};
        //form 10 -14
        LocalTime[][] DoctorReceptionTime1={
                {begineReceptionTime,begineReceptionTime,begineReceptionTime,begineReceptionTime,begineReceptionTime,begineReceptionTime,begineReceptionTime},
                {endReceptionTime,endReceptionTime,endReceptionTime,endReceptionTime,endReceptionTime,endReceptionTime,endReceptionTime}};
        //from 8-9
        LocalTime[][] DoctorReceptionTime2={
                {begineReceptionTime1,begineReceptionTime1,begineReceptionTime1,begineReceptionTime1,begineReceptionTime1,begineReceptionTime1,begineReceptionTime1},
                {endReceptionTime1,endReceptionTime1,endReceptionTime1,endReceptionTime1,endReceptionTime1,endReceptionTime1,endReceptionTime1}};
        //LinkedList
        LinkedList<Patient> patients1=new LinkedList<>();
        LinkedList<Patient> patients2=new LinkedList<>();
        LinkedList<Patient> patients3=new LinkedList<>();
        LinkedList<Doctor> doctors1=new LinkedList<>();
        LinkedList<Doctor> doctors2=new LinkedList<>();
        LinkedList<Doctor> doctors3=new LinkedList<>();
        LinkedList<Nurse> nurses1=new LinkedList<>();
        LinkedList<Nurse> nurses2=new LinkedList<>();
        LinkedList<Nurse> nurses3=new LinkedList<>();
        LinkedList<LaboratoryFacts> laboratoryFact1=new LinkedList<>();
        LinkedList<LaboratoryFacts> laboratoryFact2=new LinkedList<>();
        LinkedList<LaboratoryFacts> laboratoryFact3=new LinkedList<>();
        LinkedList<Clinic> clinics1=new LinkedList<>();
        LinkedList<Clinic> clinics2=new LinkedList<>();
        LinkedList<Clinic> clinics3=new LinkedList<>();
        LinkedList<ReceptionTime> receptionTimes1=new LinkedList<>();
        LinkedList<ReceptionTime> receptionTimes2=new LinkedList<>();
        LinkedList<ReceptionTime> receptionTimes3=new LinkedList<>();
        LinkedList<ReceptionTime> receptionTimes4=new LinkedList<>();
        LinkedList<ReceptionTime> receptionTimes5=new LinkedList<>();
        LinkedList<ReceptionTime> receptionTimes6=new LinkedList<>();
        LinkedList<ReceptionTime> receptionTimes7=new LinkedList<>();
        LinkedList<ReceptionTime> receptionTimes8=new LinkedList<>();
        LinkedList<ReceptionTime> receptionTimes9=new LinkedList<>();
        //manager of HMO
        HmoManager hmoManager=new HmoManager("hmoManager","hmoManager","0503104837","hmoManager@gmail.com","HM-hmoManager","1111");
        //manager of clinic
        ClinicManager clinicManager1= new ClinicManager("Salsabeel","Saleh","0503104837","CLinicM11@gmail.com","CM-SalehSalsabeel","1111");
        ClinicManager clinicManager2= new ClinicManager("Sara","Zreke","0526589829","CLinicM22@gmail.com","CM-ZrekeSara","2222");
        ClinicManager clinicManager3= new ClinicManager("Marah","Bhoooty","0532252582","CLinicM33@gmail.com","CM-BhotyMarah","3333");
        //doctors
        Doctor doctor1=new Doctor("doctor1","doctor","0503104837","Doctor11@gmail.com","D-Doctor1","1111","FamilyDoctor");
        Doctor doctor2=new Doctor("doctor2","doctor","0503104837","Doctor22@gmail.com","D-Doctor2","2222","FamilyDoctor");
        Doctor doctor3=new Doctor("doctor3","doctor","0503104837","Doctor33@gmail.com","D-Doctor3","3333","ProfessionalDoctor-Otolaryngologies");
        Doctor doctor4=new Doctor("doctor4","doctor","0503104837","Doctor44@gmail.com","D-Doctor4","4444","PediatricianDoctor");
        Doctor doctor5=new Doctor("doctor5","doctor","0503104837","Doctor55@gmail.com","D-Doctor5","5555","PediatricianDoctor");
        Doctor doctor6=new Doctor("doctor6","doctor","0503104837","Doctor66@gmail.com","D-Doctor6","6666","ProfessionalDoctor-Gynecologist");
        Doctor doctor7=new Doctor("doctor7","doctor","0503104837","Doctor77@gmail.com","D-Doctor7","7777","ProfessionalDoctor-Otolaryngologies");
        Doctor doctor8=new Doctor("doctor8","doctor","0503104837","Doctor88@gmail.com","D-Doctor8","8888","ProfessionalDoctor-Otolaryngologies");
        Doctor doctor9=new Doctor("doctor9","doctor","0503104837","Doctor99@gmail.com","D-Doctor9","9999","ProfessionalDoctor-Otolaryngologies");
        //Nurse
        Nurse nurse1=new Nurse("nurse1","nurse","0503104837","nurse111@gmail.com","N-nurse1","1111");
        Nurse nurse2=new Nurse("nurse2","nurse","0503104837","nurse222@gmail.com","N-nurse2","2222");
        Nurse nurse3=new Nurse("nurse3","nurse","0503104837","nurse333@gmail.com","N-nurse3","3333");
        //LaboratoryFacts
        LaboratoryFacts laboratoryFacts1=new LaboratoryFacts("laboratoryFacts1","laboratoryFacts","0503104837","laboratoryFacts1111@gmail.com","L-laboratoryFacts1","1111");
        LaboratoryFacts laboratoryFacts2=new LaboratoryFacts("laboratoryFacts2","laboratoryFacts","0503104837","laboratoryFacts2222@gmail.com","L-laboratoryFacts1","2222");
        LaboratoryFacts laboratoryFacts3=new LaboratoryFacts("laboratoryFacts3","laboratoryFacts","0503104837","laboratoryFacts3333@gmail.com","L-laboratoryFacts1","3333");
        //Patient
        Patient patient1=new Patient("patient1","patient","0503104837","reman.2000.h@gmail.com","P-patient1","1111",34,false,120);
        Patient patient2=new Patient("patient2","patient","0503104837","sarazreke@gmail.com","P-patient2","2222",18,true,162);
        Patient patient3=new Patient("patient3","patient","0503104837","marah.bhoty@gmail.com","P-patient3","3333",10,false,158);
        Patient patient4=new Patient("patient4","patient","0503104837","nameer.egbaria14@gmail.com","P-patient4","4444",20,true,132);

        //HMO
        HMO HMO1 = new HMO("HMO1");

        //Clinic
        Clinic clinic1 = new Clinic("General clinic", ActivityTime,CoronaTestTime,VaccineTime,clinicManager1,HMO1);
        Clinic clinic2 = new Clinic("Professional clinic", ActivityTime,CoronaTestTime,VaccineTime,clinicManager2,HMO1);
        Clinic clinic3 = new Clinic("General && Professional clinic", ActivityTime,CoronaTestTime,VaccineTime,clinicManager3,HMO1);

        //connect clinics to HMO
        HMO1.getClinics().add(clinic1);
        HMO1.getClinics().add(clinic2);
        HMO1.getClinics().add(clinic3);
        HMO1.setHmoManager(hmoManager);
        hmoManager.setHmo(HMO1);

        //receptionTime
        ReceptionTime reception1=new ReceptionTime(clinic1.getClinicType(),DoctorReceptionTime1);//10-14
        ReceptionTime reception2=new ReceptionTime(clinic3.getClinicType(),DoctorReceptionTime1);//10-14
        ReceptionTime reception3=new ReceptionTime(clinic2.getClinicType(),DoctorReceptionTime1);//10-14
        ReceptionTime reception4=new ReceptionTime(clinic1.getClinicType(),DoctorReceptionTime1);//10-14
        ReceptionTime reception5=new ReceptionTime(clinic3.getClinicType(),DoctorReceptionTime1);//10-14
        ReceptionTime reception6_3=new ReceptionTime(clinic3.getClinicType(),DoctorReceptionTime2);//8-9
        ReceptionTime reception6_2=new ReceptionTime(clinic2.getClinicType(),DoctorReceptionTime1);//10-14
        ReceptionTime reception7=new ReceptionTime(clinic3.getClinicType(),DoctorReceptionTime1);//10-14
        ReceptionTime reception8=new ReceptionTime(clinic3.getClinicType(),DoctorReceptionTime1);//10-14
        ReceptionTime reception9=new ReceptionTime(clinic2.getClinicType(),DoctorReceptionTime1);//10-14


        //Set doctor1 reception time
        receptionTimes1.add(reception1);
        doctor1.setReceptionTime(receptionTimes1);
        reception1.setDoctor(doctor1);

        //Set doctor2 reception time
        receptionTimes2.add(reception2);
        doctor2.setReceptionTime(receptionTimes2);
        reception2.setDoctor(doctor2);

        //Set doctor3 reception time
        receptionTimes3.add(reception3);
        doctor3.setReceptionTime(receptionTimes3);
        reception3.setDoctor(doctor3);

        //Set doctor4 reception time
        receptionTimes4.add(reception4);
        doctor4.setReceptionTime(receptionTimes4);
        reception4.setDoctor(doctor4);

        //Set doctor5 reception time
        receptionTimes5.add(reception5);
        doctor5.setReceptionTime(receptionTimes5);
        reception5.setDoctor(doctor5);

        //Set doctor6 reception time
        receptionTimes6.add(reception6_2);
        receptionTimes6.add(reception6_3);
        doctor6.setReceptionTime(receptionTimes6);
        reception6_2.setDoctor(doctor6);
        reception6_3.setDoctor(doctor6);

        //Set doctor7 reception time
        receptionTimes7.add(reception7);
        doctor7.setReceptionTime(receptionTimes7);
        reception7.setDoctor(doctor7);

        //Set doctor8 reception time
        receptionTimes8.add(reception8);
        doctor8.setReceptionTime(receptionTimes8);
        reception8.setDoctor(doctor8);

        //Set doctor9 reception time
        receptionTimes9.add(reception9);
        doctor9.setReceptionTime(receptionTimes9);
        reception9.setDoctor(doctor9);


        //connect list of doctor, nurse, laboratory,clinicManger,patient to  clinic 1
        //create list
        doctors1.add(doctor1);
        doctors1.add(doctor4);
        nurses1.add(nurse1);
        laboratoryFact1.add(laboratoryFacts1);
        patients1.add(patient1);
        patients1.add(patient2);
        clinics1.add(clinic1);
        //insert list
        clinic1.setDoctors(doctors1);
        clinic1.setNurses(nurses1);
        clinic1.setLaboratoryFacts(laboratoryFact1);
        clinic1.setPatients(patients1);
        doctor1.setClinicList(clinics1);
        doctor4.setClinicList(clinics1);
        nurse1.setClinicList(clinics1);
        laboratoryFacts1.setClinicList(clinics1);
        patient1.setClinic(clinic1);
        patient2.setClinic(clinic1);
       //connect manger to clinic
        clinic1.setClinicManager(clinicManager1);
        clinicManager1.setClinic(clinic1);

        //connect list of doctor, nurse, laboratory,clinicManger to  clinic 2
        //create list
        doctors2.add(doctor3);
        doctors2.add(doctor6);
        doctors2.add(doctor9);
        nurses2.add(nurse2);
        laboratoryFact2.add(laboratoryFacts2);
        //patients2.add(patient3);
        clinics2.add(clinic2);
        //insert list
        clinic2.setDoctors(doctors2);
        clinic2.setNurses(nurses2);
        clinic2.setLaboratoryFacts(laboratoryFact2);
        //clinic2.setPatients(patients2);
        doctor3.setClinicList(clinics2);
        doctor6.setClinicList(clinics2);
        doctor9.setClinicList(clinics2);
        nurse2.setClinicList(clinics2);
        laboratoryFacts2.setClinicList(clinics2);
        //patient2.setClinic(clinic2);
        //connect manger to clinic
        clinic2.setClinicManager(clinicManager2);
        clinicManager2.setClinic(clinic2);

        //connect list of doctor, nurse, laboratory,clinicManger to  clinic 3
        //create list
        doctors3.add(doctor2);
        doctors3.add(doctor5);
        doctors3.add(doctor6);
        doctors3.add(doctor7);
        doctors3.add(doctor8);
        nurses3.add(nurse3);
        laboratoryFact3.add(laboratoryFacts3);
        patients3.add(patient4);
        patients3.add(patient3);
        clinics3.add(clinic3);
        //insert list
        clinic3.setDoctors(doctors3);
        clinic3.setNurses(nurses3);
        clinic3.setLaboratoryFacts(laboratoryFact3);
        clinic3.setPatients(patients3);
        doctor2.setClinicList(clinics3);
        doctor5.setClinicList(clinics3);
        doctor6.setClinicList(clinics3);
        doctor7.setClinicList(clinics3);
        doctor8.setClinicList(clinics3);
        nurse3.setClinicList(clinics3);
        laboratoryFacts3.setClinicList(clinics3);
        patient4.setClinic(clinic3);
        patient3.setClinic(clinic3);
        //connect manger to clinic
        clinic3.setClinicManager(clinicManager3);
        clinicManager3.setClinic(clinic3);



        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();

            System.err.println("Generated starts ...");
            session.saveOrUpdate(HMO1);
            session.saveOrUpdate(clinic1);
            session.saveOrUpdate(clinic2);
            session.saveOrUpdate(clinic3);
            session.saveOrUpdate(hmoManager);
            session.saveOrUpdate(clinicManager1);
            session.saveOrUpdate(clinicManager2);
            session.saveOrUpdate(clinicManager3);
            session.saveOrUpdate(doctor1);
            session.saveOrUpdate(doctor2);
            session.saveOrUpdate(doctor3);
            session.saveOrUpdate(doctor4);
            session.saveOrUpdate(doctor5);
            session.saveOrUpdate(doctor6);
            session.saveOrUpdate(doctor7);
            session.saveOrUpdate(doctor8);
            session.saveOrUpdate(doctor9);
            session.saveOrUpdate(nurse1);
            session.saveOrUpdate(nurse2);
            session.saveOrUpdate(nurse3);
            session.saveOrUpdate(laboratoryFacts1);
            session.saveOrUpdate(laboratoryFacts2);
            session.saveOrUpdate(laboratoryFacts3);
            session.saveOrUpdate(patient1);
            session.saveOrUpdate(patient2);
            session.saveOrUpdate(patient3);
            session.saveOrUpdate(patient4);
            session.saveOrUpdate(reception1);
            session.saveOrUpdate(reception2);
            session.saveOrUpdate(reception3);
            session.saveOrUpdate(reception4);
            session.saveOrUpdate(reception5);
            session.saveOrUpdate(reception6_2);
            session.saveOrUpdate(reception6_3);
            session.saveOrUpdate(reception7);
            session.saveOrUpdate(reception8);
            session.saveOrUpdate(reception9);
            System.err.println("Generated ends ...");
            session.flush();
            session.getTransaction().commit(); // Save everything.

        }catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();
           // generateNewData();
           // printAllPatient();
           // printAllDepartment();
           // printAllDoctor();
            //generateCars();
            //printAllCars();
            session.getTransaction().commit(); // Save everything.

        } catch (Exception exception) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            System.err.println("An error occured, changes have been rolled back.");
            exception.printStackTrace();
        } finally {
            if(session != null)
                session.close();
        }
    }
}
