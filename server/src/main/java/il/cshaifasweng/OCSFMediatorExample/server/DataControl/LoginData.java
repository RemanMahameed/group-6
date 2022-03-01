package il.cshaifasweng.OCSFMediatorExample.server.DataControl;

import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.Login;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.cardinfo;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static il.cshaifasweng.OCSFMediatorExample.server.DataControl.AppointmentData.getClosestApp;


public class LoginData extends DataClass{
    private static Session session;
    private static final int NOTFOUND = -1;
    private static final int ISACTIVE = -2;
    private static final int ISPATIENT = 0;
    private static final int ISDOCTOR = 1;
    private static final int ISNURSE = 2;
    private static final int ISLAB = 3;
    private static final int ISCM = 4;
    private static final int ISHM = 5;


    private static Login CheckLogPatient(String userName, String passWord) throws Exception {

        List<Patient> Patients = getAllPatients();
        for (Patient patient : Patients) {
            if (patient.getPassWord().equals(passWord) && patient.getUserName().equalsIgnoreCase(userName)) {
                if (patient.getActive() == true)
                    return (new Login(userName, passWord, ISACTIVE));
                else {
                    patient.setActive(true);
                    session.saveOrUpdate(patient);
                    session.flush();
                    return  (new Login(userName, passWord, ISPATIENT,patient));
                }
            }
        }
        return (new Login(userName, passWord, NOTFOUND));
    }

    private static Login CheckLogDoctor(String userName, String passWord) throws Exception {
        Login login;
        List<Doctor> doctors = getAllDoctor();
        for (Doctor doctor : doctors) {
            System.out.println("User Name: "+ doctor.getUserName() + '\n'
                              +"Pass Word: "+ doctor.getPassWord()+'\n');
            if (doctor.getPassWord().equals(passWord) && doctor.getUserName().equalsIgnoreCase(userName)) {
                if (doctor.getActive() == true)
                    return login = new Login(userName, passWord, ISACTIVE);
                else {
                    doctor.setActive(true);
                    session.saveOrUpdate(doctor);
                    session.flush();
                    return login = new Login(userName, passWord, ISDOCTOR,doctor);
                }
            }
        }
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
                    return login = new Login(userName, passWord, ISLAB,laboratoryFacts1);
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
                login = CheckLogDoctor(userName, passWord);
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
    public static void logOutUser(Object object) throws Exception{
        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        if(object.getClass().equals(Doctor.class)){
            Doctor user=(Doctor) object;
            Doctor doctor=getDoctorById(user.getId());
            doctor.setActive(false);
            session.saveOrUpdate(doctor);

        }else if(object.getClass().equals(Nurse.class)) {
            Nurse user = (Nurse) object;
            Nurse nurse=getNurseById(user.getId());
            nurse.setActive(false);
            session.saveOrUpdate(nurse);

        }else if(object.getClass().equals(LaboratoryFacts.class)) {
            LaboratoryFacts user = (LaboratoryFacts) object;
            LaboratoryFacts laboratoryFacts=getLabFactById(user.getId());
            laboratoryFacts.setActive(false);
            session.saveOrUpdate(laboratoryFacts);

        }else if(object.getClass().equals(HmoManager.class)) {
            HmoManager user = (HmoManager) object;
            HmoManager hmoManager=getHMOManagerById(user.getId());
            hmoManager.setActive(false);
            session.saveOrUpdate(hmoManager);

        }else if(object.getClass().equals(ClinicManager.class)) {
            ClinicManager user = (ClinicManager) object;
            ClinicManager clinicManager=getClinicManagerById(user.getId());
            clinicManager.setActive(false);
            session.saveOrUpdate(clinicManager);

        }else if(object.getClass().equals(Patient.class)) {
            Patient user = (Patient) object;
            Patient patient=getPatientById(user.getId());
            patient.setActive(false);
            session.saveOrUpdate(patient);
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
        List<Patient> patients = getAllPatients();
        for (Patient patient : patients) {
            patient.setActive(false);
            session.saveOrUpdate(patients);
            session.flush();
        }
        List<Nurse> nurses = getAllNurse();
        for (Nurse nurse : nurses) {
            nurse.setActive(false);
            session.saveOrUpdate(nurses);
            session.flush();
        }

        session.close();
    }
    //////////////////////////////
    /// sara nameer reeman
    public static cardinfo CheckPatientCard(long card , String clinicName) throws Exception {

        SessionFactory sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        List<Patient> Patients = getAllPatients();
        // Clinic clinic = new Clinic();
        Clinic clinic = getClinicByName(clinicName);
        String det = "$";
//        for(Clinic element : clinics){
//            if(clinicName.equals(element.getClinicType()))
//                clinic=element;
//        }
        for (Patient patient : Patients) {
            if (patient.getCard()== card ) {
                if (patient.getActive() == true)
                    return (new cardinfo(patient , clinic , ISACTIVE ,""));
                else {
                    det = getClosestApp(patient , clinic);
                    patient.setActive(true);
                    session.saveOrUpdate(patient);
                    session.flush();
                    session.getTransaction().commit();
                    cardinfo cardinfo=new cardinfo(patient , clinic, ISPATIENT , det);
                    return cardinfo;
                }
            }
        }
        session.getTransaction().commit();
        if (session != null)
            session.close();
        return (new cardinfo(Patients.get(0) , clinic, NOTFOUND , ""));
    }
}


