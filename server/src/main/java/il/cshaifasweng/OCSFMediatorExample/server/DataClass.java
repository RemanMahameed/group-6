package il.cshaifasweng.OCSFMediatorExample.server;

import java.beans.Statement;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
public class DataClass {

    private static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();

        // Add ALL of your entities here. You can also try adding a whole package.

//        configuration.addAnnotatedClass(Car.class);
//        configuration.addAnnotatedClass(Patient.class);
//        configuration.addAnnotatedClass(Doctor.class);
//        configuration.addAnnotatedClass(TreatmentDetails.class);
//        configuration.addAnnotatedClass(Department.class);
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


        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

//    private static List<Car> getAllCars() throws Exception {
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Car> query = builder.createQuery(Car.class);
//        query.from(Car.class);
//        List<Car> data = session.createQuery(query).getResultList();
//        return data;
//    }

//    private static void printAllCars() throws Exception {
//        List<Car> cars = getAllCars();
//        for (Car car : cars) {
//            System.out.print("Id: ");
//            System.out.print(car.getId());
//            System.out.print(", License plate: ");
//            System.out.print(car.getLicensePlate());
//            System.out.print(", Price: ");
//            System.out.print(car.getPrice());
//            System.out.print(", Year: ");
//            System.out.print(car.getYear());
//            System.out.print('\n');
//        }
//    }

    public static void generateNewData() throws Exception {
//        //Patient
//        LocalDateTime date1 = LocalDateTime.of(2021, 04, 24, 14, 33);
//        LocalDateTime date2 = LocalDateTime.of(2021, 05, 24, 12, 30);
//        LocalDateTime date3 = LocalDateTime.of(2021, 06, 24, 10, 00);
//        LocalDateTime date4 = LocalDateTime.of(2021, 07, 24, 18, 00);
//        Patient patient1=new Patient(2020,"John","Michael",date2);
//        Patient patient2=new Patient(1,"John","Michael",date2);
//        Patient patient3= new Patient(100,"Barbara","David",date4);
//        Patient patient4= new Patient(60,"Elizabeth","William",date3);
//        //Doctor
//        Doctor doctor1=new Doctor("Jose","Adam","Adam_Jose@gamil.com","111");
//        Doctor doctor2=new Doctor("Henry","Nathan","Nathan_Henry@gamil.com","222");
//        Doctor doctor3=new Doctor("Douglas","Zachary","Zachary_Douglas@gamil.com","333");
//        Doctor doctor4=new Doctor("Andrea","Christian","Christian_Andrea@gamil.com","444");
//        Doctor doctor5=new Doctor("Ann","Sean","Roger_Hannah@gamil.com","555");
//        Doctor doctor6=new Doctor("Ann","Roger","Sean_Ann@gamil.com","666");
//        //Department
//        Department department1=new Department("Nursing Department");
//        Department department2=new Department("Medical Department");
//        Department department3=new Department("Paramedical Department");
//        //
//        department1.getPatients().add(patient1);
//        department2.getPatients().add(patient2);
//        department3.getPatients().add(patient3);
//        department3.getPatients().add(patient4);
//        doctor1.setDepartment(department1);
//        doctor2.setDepartment(department1);
//        doctor3.setDepartment(department3);
//        //department1.setDoctor(doctor4);
//        //department2.setDoctor(doctor2);
//        //department3.setDoctor(doctor1);
//
//        //saving db
//        session.save(patient1);
//        session.save(patient2);
//        session.save(patient3);
//        session.save(patient4);
//        session.save(doctor1);
//        session.save(doctor2);
//        session.save(doctor3);
//        session.save(doctor4);
//        session.save(doctor5);
//        session.save(doctor6);
//        session.save(department1);
//        session.save(department2);
//        session.save(department3);
//        session.flush();

        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        //manager of HMO
        //HmoManager hmoManager=new HmoManager("hmoManager","hmoManager","0503104837","hmoManager@gmail.com","hmoManager","1111");

        //manager of clinic
        ClinicManager clinicManager1= new ClinicManager("Salsabeel","Saleh","0503104837","salehsalsabeel99@gmail.com","SalehSalsabeel","123456789");
        ClinicManager clinicManager2= new ClinicManager("Sara","Zreke","0526589829","sarazreke@gmail.com","ZrekeSara","1234");
        ClinicManager clinicManager3= new ClinicManager("Marah","Bhoooty","0532252582","marah.bhoty@gmail.com","BhotyMarah","56789");
        //doctors
        Doctor doctor1=new Doctor("doctor1","doctor","0503104837","Doctor11@gmail.com","Doctor1","1111","FamilyDoctor");
        Doctor doctor2=new Doctor("doctor2","doctor","0503104837","Doctor22@gmail.com","Doctor2","2222","FamilyDoctor");
        Doctor doctor3=new Doctor("doctor1","doctor","0503104837","Doctor33@gmail.com","Doctor3","3333","OtolaryngologyDoctor");
        //Nurse
        Nurse nurse1=new Nurse("nurse1","nurse","0503104837","nurse111@gmail.com","nurse1","1111");
        Nurse nurse2=new Nurse("nurse2","nurse","0503104837","nurse222@gmail.com","nurse2","2222");
        Nurse nurse3=new Nurse("nurse3","nurse","0503104837","nurse333@gmail.com","nurse3","3333");
        //LaboratoryFacts
        LaboratoryFacts laboratoryFacts1=new LaboratoryFacts("laboratoryFacts1","laboratoryFacts","0503104837","laboratoryFacts1111@gmail.com","laboratoryFacts1","1111");
        LaboratoryFacts laboratoryFacts2=new LaboratoryFacts("laboratoryFacts2","laboratoryFacts","0503104837","laboratoryFacts2222@gmail.com","laboratoryFacts1","2222");
        LaboratoryFacts laboratoryFacts3=new LaboratoryFacts("laboratoryFacts3","laboratoryFacts","0503104837","laboratoryFacts3333@gmail.com","laboratoryFacts1","3333");
        //Patient
        Patient patient1=new Patient("patient1","patient","0503104837","patient111@gmail.com","patient1","1111",34,false);
        Patient patient2=new Patient("patient2","patient","0503104837","patient222@gmail.com","patient2","2222",18,false);
        Patient patient3=new Patient("patient3","patient","0503104837","patient333@gmail.com","patient3","3333",10,false);
        Patient patient4=new Patient("patient4","patient","0503104837","patient444@gmail.com","patient4","4444",20,false);
        //List of doctor nurse laboratory
        List<Doctor> doctors=new ArrayList<>();
        doctors.add(doctor1);
        doctors.add(doctor2);
        doctors.add(doctor3);
        List<Nurse> nurses=new ArrayList<>();
        nurses.add(nurse1);
        nurses.add(nurse2);
        nurses.add(nurse3);
        List<LaboratoryFacts> laboratoryFacts=new ArrayList<>();
        laboratoryFacts.add(laboratoryFacts1);
        laboratoryFacts.add(laboratoryFacts2);
        laboratoryFacts.add(laboratoryFacts3);
        //matrix of openingHours
        LocalTime openingHours = LocalTime.of(8,00);
        LocalTime closingHours = LocalTime.of(16,00);
        LocalTime[][] ActivityTime={
                {openingHours,openingHours,openingHours,openingHours,openingHours,openingHours,openingHours},
                {closingHours,closingHours,closingHours,closingHours,closingHours,closingHours,closingHours}};
        LocalTime[][] CoronaTestTime={
                {openingHours,openingHours,openingHours,openingHours,openingHours,openingHours,openingHours},
                {closingHours,closingHours,closingHours,closingHours,closingHours,closingHours,closingHours}};
        LocalTime[][] VaccineTime={
                {openingHours,openingHours,openingHours,openingHours,openingHours,openingHours,openingHours},
                {closingHours,closingHours,closingHours,closingHours,closingHours,closingHours,closingHours}};
        HMO HMO1 = new HMO("HMO1");
        Clinic clinic1 = new Clinic("General clinic", ActivityTime,CoronaTestTime,VaccineTime,clinicManager1,HMO1);
        Clinic clinic2 = new Clinic("General clinic", ActivityTime,CoronaTestTime,VaccineTime,clinicManager2,HMO1);
        Clinic clinic3 = new Clinic("Professional clinic", ActivityTime,CoronaTestTime,VaccineTime,clinicManager3,HMO1);
        //connect clinic to HMO
        HMO1.getClinics().add(clinic1);
        HMO1.getClinics().add(clinic2);
        HMO1.getClinics().add(clinic3);
        //HMO1.setHmoManager(hmoManager);
        //connect list of doctor nurse laboratory to clinic
        clinic1.setDoctors(doctors);
        clinic1.setNurses(nurses);
        clinic1.setLaboratoryFacts(laboratoryFacts);
        //connect manger to clinic
        clinic1.setClinicManager(clinicManager1);
        clinicManager1.setClinic(clinic1);




        System.err.println("Generated starts ...");
        //session.saveOrUpdate(hmoManager);
        session.saveOrUpdate(patient1);
        session.saveOrUpdate(patient2);
        session.saveOrUpdate(patient3);
        session.saveOrUpdate(patient4);
        session.saveOrUpdate(doctor1);
        session.saveOrUpdate(doctor2);
        session.saveOrUpdate(doctor3);
        session.saveOrUpdate(nurse1);
        session.saveOrUpdate(nurse2);
        session.saveOrUpdate(nurse3);
        session.saveOrUpdate(laboratoryFacts1);
        session.saveOrUpdate(laboratoryFacts2);
        session.saveOrUpdate(laboratoryFacts3);
        session.saveOrUpdate(clinicManager1);
        session.saveOrUpdate(clinicManager2);
        session.saveOrUpdate(clinicManager3);
        session.saveOrUpdate(HMO1);
        session.saveOrUpdate(clinic1);
        session.saveOrUpdate(clinic2);
        session.saveOrUpdate(clinic3);
        System.err.println("Generated ends ...");

        session.flush();
        session.getTransaction().commit(); // Save everything.
        session.close();
    }


//    private static List<Patient> getAllPatients() throws Exception {
//        String query="FROM Patient order by patientNum";
//        List<Patient> result=session.createQuery(query).list();
//        return result;
//    }

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
