package il.cshaifasweng.OCSFMediatorExample.server.DataControl;

import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.Login;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class LoginData {
    private static final int NOTFOUND = -1;
    private static final int ISACTIVE = -2;
    private static final int ISPATIENT = 0;
    private static final int ISDOCTOR = 1;
    private static final int ISNURSE = 2;
    private static final int ISLAB = 3;
    private static final int ISCM = 4;
    private static final int ISHM = 5;

    private static Session session;

    private static SessionFactory getSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();

        // Add ALL of your entities here. You can also try adding a whole package.

        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(ClinicManager.class);
        configuration.addAnnotatedClass(Doctor.class);
        configuration.addAnnotatedClass(Nurse.class);
        configuration.addAnnotatedClass(HmoManager.class);
        configuration.addAnnotatedClass(LaboratoryFacts.class);
        configuration.addAnnotatedClass(DoctorAppointment.class);
        configuration.addAnnotatedClass(HMO.class);
        configuration.addAnnotatedClass(Clinic.class);
        configuration.addAnnotatedClass(LaboratoryFactsAppointment.class);
         configuration.addAnnotatedClass(NurseAppointment.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static List<Patient> getAllPatients() throws Exception {
        String query = "FROM Patient";
        List<Patient> result = session.createQuery(query).list();
        return result;
    }

    private static List<ClinicManager> getAllClinicManager() throws Exception {
        String query = "FROM ClinicManager";
        List<ClinicManager> result = session.createQuery(query).list();
        return result;
    }

    private static List<LaboratoryFacts> getAllLaboratoryFacts() throws Exception {
        String query = "FROM LaboratoryFacts";
        List<LaboratoryFacts> result = session.createQuery(query).list();
        return result;
    }

    private static List<Nurse> getAllNurse() throws Exception {
        String query = "FROM Nurse";
        List<Nurse> result = session.createQuery(query).list();
        return result;
    }

    private static List<Doctor> getAllDoctor() throws Exception {
        String query = "FROM Doctor";
        List<Doctor> result = session.createQuery(query).list();
        return result;
    }
    private static List<HmoManager> getAllHmoManger() throws Exception {
        String query = "FROM HmoManger";
        List<HmoManager> result = session.createQuery(query).list();
        return result;
    }


    private static Login CheckLogPatient(String userName, String passWord) throws Exception {
        Login login;
        List<Patient> Patients = getAllPatients();
        for (Patient patient : Patients) {
            if (patient.getPassWord().equals(passWord) && patient.getUserName().equalsIgnoreCase(userName)) {
                if (patient.getActive() == true)
                    return login = new Login(userName, passWord, ISACTIVE);
                else {
                    patient.setActive(true);
                    session.saveOrUpdate(patient);
                    session.flush();
                    Object object=patient;
                    return login = new Login(userName, passWord, ISPATIENT,object);
                }
            }
        }
        return login = new Login(userName, passWord, NOTFOUND);
    }

    private static Login CheckLogDoctor(String userName, String passWord) throws Exception {
        Login login;
        List<Doctor> doctors = getAllDoctor();
        System.out.println("size of listdoctor is: "+ doctors.size());
        for (Doctor doctor : doctors) {
            System.out.println("User Name: "+ doctor.getUserName() + '\n'
                              +"Pass Word: "+ doctor.getPassWord()+'\n');
            if (doctor.getPassWord().equals(passWord) && doctor.getUserName().equalsIgnoreCase(userName)) {
                if (doctor.getActive() == true)
                    return login = new Login(userName, passWord, ISACTIVE);
                else {
                    System.out.println("found Doctor ");
                    doctor.setActive(true);
                    session.saveOrUpdate(doctor);
                    session.flush();
                    return login = new Login(userName, passWord, ISDOCTOR,doctor);
                }
            }
        }
        System.out.println("NOT found Doctor ");
        return login = new Login(userName, passWord, NOTFOUND);
    }

    private static Login CheckLogNurse(String userName, String passWord) throws Exception {
        Login login;
        List<Nurse> nurses = getAllNurse();
        for (Nurse nurse : nurses) {
            if (nurse.getPassWord().equals(passWord) && nurse.getUserName().equalsIgnoreCase(userName)) {
                if (nurse.getActive() == true)
                    return login = new Login(userName, passWord, ISACTIVE);
                else {
                    nurse.setActive(true);
                    session.saveOrUpdate(nurse);
                    session.flush();
                    return login = new Login(userName, passWord, ISNURSE,nurse);
                }
            }
        }
        return login = new Login(userName, passWord, NOTFOUND);
    }

    private static Login CheckLogLaboratory(String userName, String passWord) throws Exception {
        Login login;
        List<LaboratoryFacts> laboratoryFacts = getAllLaboratoryFacts();
        for (LaboratoryFacts laboratoryFacts1 : laboratoryFacts) {
            if (laboratoryFacts1.getPassWord().equals(passWord) && laboratoryFacts1.getUserName().equalsIgnoreCase(userName)) {
                if (laboratoryFacts1.getActive() == true)
                    return login = new Login(userName, passWord, ISACTIVE);
                else {
                    laboratoryFacts1.setActive(true);
                    session.saveOrUpdate(laboratoryFacts1);
                    session.flush();
                    return login = new Login(userName, passWord, ISLAB,laboratoryFacts);
                }
            }
        }
        return login = new Login(userName, passWord, NOTFOUND);
    }

    private static Login CheckLogCM(String userName, String passWord) throws Exception {
        Login login;
        List<ClinicManager> clinicManagers = getAllClinicManager();
        for (ClinicManager clinicManager : clinicManagers) {
            if (clinicManager.getPassWord().equals(passWord) && clinicManager.getUserName().equalsIgnoreCase(userName)) {
                if (clinicManager.getActive() == true)
                    return login = new Login(userName, passWord, ISACTIVE);
                else {
                    clinicManager.setActive(true);
                    session.saveOrUpdate(clinicManager);
                    session.flush();
                    return login = new Login(userName, passWord, ISCM,clinicManager);
                }
            }
        }
        return login = new Login(userName, passWord, NOTFOUND);
    }
    private static Login CheckLogHM(String userName, String passWord) throws Exception {
        Login login;
        List<HmoManager> hmoManagers = getAllHmoManger();
        for (HmoManager hmoManager : hmoManagers) {
            if (hmoManager.getPassWord().equals(passWord) && hmoManager.getUserName().equalsIgnoreCase(userName)) {
                if (hmoManager.getActive() == true)
                    return login = new Login(userName, passWord, ISACTIVE);
                else {
                    hmoManager.setActive(true);
                    session.saveOrUpdate(hmoManager);
                    session.flush();
                    return login = new Login(userName, passWord, ISHM,hmoManager);
                }
            }
        }
        return login = new Login(userName, passWord, NOTFOUND);
    }

    // input : userName & PassWord and check if is already found in User table
    public static Login CheckExcision(String userName, String passWord) throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        Login login = new Login(userName, passWord, NOTFOUND);
        System.out.println("I am at CheckExcision : " + userName.substring(0, 2));
        switch (userName.substring(0, 2)) {
            case ("P-"):
                login = CheckLogPatient(userName, passWord);
                break;
            case ("D-"):
                System.out.println("Before Login:");
                login = CheckLogDoctor(userName, passWord);
                System.out.println("Before Login:" + login.getSuccess());
                break;
            case ("N-"):
                login = CheckLogNurse(userName, passWord);
                break;
            case ("L-"):
                login = CheckLogLaboratory(userName, passWord);
                break;
            case ("CM"):
                login = CheckLogCM(userName, passWord);
                break;

            case ("HM"):
                login = CheckLogHM(userName, passWord);
                break;
            default:
                return login;
        }
        session.getTransaction().commit();
        if (session != null)
           session.close();
        return login;
    }
    public static void logOutUser(Object object){
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        if(object.getClass().equals(Doctor.class)){
            Doctor user=(Doctor) object;
            user.setActive(false);
            session.saveOrUpdate(user);
        }else if(object.getClass().equals(Nurse.class)) {
            Nurse user = (Nurse) object;
            user.setActive(false);
            session.saveOrUpdate(user);
        }else if(object.getClass().equals(LaboratoryFacts.class)) {
            LaboratoryFacts user = (LaboratoryFacts) object;
            user.setActive(false);
            session.saveOrUpdate(user);
        }else if(object.getClass().equals(HmoManager.class)) {
            HmoManager user = (HmoManager) object;
            user.setActive(false);
            session.saveOrUpdate(user);
        }else if(object.getClass().equals(ClinicManager.class)) {
            ClinicManager user = (ClinicManager) object;
            user.setActive(false);
            session.saveOrUpdate(user);
        }else if(object.getClass().equals(Patient.class)) {
            Patient user = (Patient) object;
            user.setActive(false);
            session.saveOrUpdate(user);
        }
        session.flush();
        session.close();
    }
    public static void NotActive() throws Exception {
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        List<Doctor> doctors = getAllDoctor();
        for (Doctor doctor : doctors) {
            doctor.setActive(false);
            session.saveOrUpdate(doctor);
            session.flush();
        }
        session.close();
    }
}


