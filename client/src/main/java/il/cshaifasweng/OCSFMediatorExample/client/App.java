package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.MessageBoundary;
import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.dispalyQueueBoundary;
import il.cshaifasweng.OCSFMediatorExample.client.Events.*;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.OpenOrCloseClinic;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;
    private List<Object> params=new LinkedList<>();
    public Clinic  my_clinic=null;
    static List<Object> p;

    //static List<Show> shows;


    @Override
    public void start(Stage stage) throws IOException {
    	EventBus.getDefault().register(this);
    	client = SimpleClient.getClient();
    	client.openConnection();
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

//    @SuppressWarnings("exports")
//    public static void setRoot(String fxml, List<Object> params) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        Parent root = fxmlLoader.load();
//        // System.out.println(stage.getUserData());
//
//        Boundary boundary = fxmlLoader.<Boundary>getController();
//        boundary.setParams(params);
//        System.out.println("BBBBBBBBBBBBBBBBBBBB");
//        System.out.println(" boundary.setParams(params); +   " +  boundary.getParams().size());
//        //boundary.setStage(stage);
//        scene.setRoot(root);
//    }
    public static void setParams(List<Object> params) {
        p = params;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    @Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
    	EventBus.getDefault().unregister(this);
		super.stop();
	}
    
    @Subscribe
    public void onWarningEvent(WarningEvent event) {
    	Platform.runLater(() -> {
    		Alert alert = new Alert(AlertType.WARNING,
        			String.format("Message: %s\nTimestamp: %s\n",
        					event.getWarning().getMessage(),
        					event.getWarning().getTime().toString())
        	);
        	alert.show();
    	});
    	
    }
    private static final int NOTFOUND = -1;
    private static final int ISACTIVE = -2;
    private static final int ISPATIENT = 0;
    private static final int ISDOCTOR = 1;
    private static final int ISNURSE = 2;
    private static final int ISLAB = 3;
    private static final int ISCM = 4;
    private static final int ISHM = 5;
    @Subscribe
    /* hon bdna nf7s sho el success
     * 0= failed
     * 1=patient
     * 2..
     *  */
    public void onLoginEvent(LoginEvent event) throws IOException {
        Platform.runLater(() -> {
            if(event.getLogin().getSuccess()== NOTFOUND )
                MessageBoundary.displayError("Wrong username or password\n");
            else if(event.getLogin().getSuccess()== ISACTIVE )
                MessageBoundary.displayError("User already logged in!\n");
            else
            {
                List<Object> params = new LinkedList<>();
                params.add(event.getLogin().getObject());
                SimpleClient.setParams(params);
                SimpleClient.setUser_Ob(params);
                if(event.getLogin().getSuccess()== ISPATIENT ) {            //Is a patient
                    try {
                        setRoot("PatientMain");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else  if(event.getLogin().getSuccess()== ISDOCTOR) {     //Is a doctor
                    try {
                        setRoot("DoctorMain");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else  if(event.getLogin().getSuccess()== ISCM) {     //User working as clinicManager
                    try {
                        setRoot("MainCm");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else  if(event.getLogin().getSuccess()== ISHM) {     //User working HmoManager
                    try {
                        setRoot("MainHm");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (event.getLogin().getSuccess() == ISNURSE) {     //Is a nurse
                    try {
                        setRoot("nursemain");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (event.getLogin().getSuccess() == ISLAB) {     //Is a Laboratory factor
                    try {
                        System.out.println(" im going to LaboratoryFactMain boundary");
                        setRoot("LaboratoryFactMain");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    @Subscribe
    public void onLogOutEvent(LogOutEvent event) throws IOException {
        Platform.runLater(() -> {
            try {
                setRoot("Primary");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void onDoctorAppEvent(DoctorAppEvent event) throws IOException {
        Platform.runLater(() -> {
            //List<Object> params = new LinkedList<>();
            //params.add(event.getDoctorApp());
            SimpleClient.getParams().add(event.getDoctorApp());
            try {
                setRoot("appointmentdoctor");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void onScheduledAppEvent(ScheduledAppEvent event) throws IOException {
        Platform.runLater(() -> {
            SimpleClient.getParams().add(event.getScheduledApp());
            try {
                setRoot("viewscheduledApp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void onClinicNameEvent(ClinicNameEvent event) throws IOException {
        Platform.runLater(() -> {
            params.clear();
            params.add(event.getClinicName());
            SimpleClient.setParams(params);
            if(event.getClinicName().getFlag().equals("UpdateOperatingHours")){
                try {
                    setRoot("UpdateOperatingHours");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    setRoot("clinic");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Subscribe
    public void onFreeAppointmentEvent(FreeAppointmentEvent event) throws IOException {
        Platform.runLater(() -> {
            params.clear();
            params.add(event.getFreeAppointment());
            SimpleClient.setParams(params);
            try {
                setRoot("coronvaccineapp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void onUpdateObjectEvent(UpdateObjectEvent event) throws IOException {
        Platform.runLater(() -> {
            List<Object> params=new LinkedList<>();
            params.add(event.getUpdateObject().getObject());
            SimpleClient.setUser_Ob(params);
            try {
                setRoot("patientmain");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void onWorkingHoursEvent(WorkingHoursEvent event) throws IOException {
        Platform.runLater(() -> {
            params.clear();
            params.add(event.getWorkingHours());
            SimpleClient.setParams(params);
            try {
                setRoot("WorkingHours");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void onDoctorNamesEvent(DoctorNameEvent event) throws IOException {
        Platform.runLater(() -> {
            params.clear();
            params.add(event.getDoctorNames());
            SimpleClient.setParams(params);
            try {
                setRoot("DoctorWorkingHours");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    //************************************************************************
    //Sara nameer reeman
    @Subscribe
    public void onProEvent(ProEvent event) throws IOException {
        Platform.runLater(() -> {
            //List<Object> params = new LinkedList<>();
            //params.add(event.getProDoctors());
            //SimpleClient.setParams(params);
            SimpleClient.getParams().add(event.getProDoctors());
            try {
                setRoot("ProDoctorsList");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public void onClinicBusEvent(ClinicBusEvent event) throws IOException {
        Platform.runLater(() -> {
            System.out.println("i am is ClinicBusEvent");
            SimpleClient.getParams().add(event.getClinicBus().getClinic());
            try {
                setRoot("DoctorActions");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @Subscribe
    public  void onReportEvent(ReportEvent event)throws IOException{
        Platform.runLater(() -> {
            System.out.println("i am is ReportBusEvent");
            if(event.getReportBus().getUser_type().equals("HM"))
                my_clinic=event.getReportBus().getClinic();
            else
                my_clinic=event.getReportBus().getManager().getClinic();
            SimpleClient.getParams().add(my_clinic);
            if(event.getReportBus().getReport_type().equals("WeaklyDoneApps")||event.getReportBus().getReport_type().equals("WeaklyNOTDoneApps"))
            {
                try {
                    SimpleClient.getParams().add(event.getReportBus().getReport_type());
                    App.setRoot("WeeklyReport");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                try {
                    App.setRoot("DailyReport");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

    }
    @Subscribe
    public  void onCardInfoEvent(CardInfoEvent event)throws IOException {
        Platform.runLater(() -> {
            System.out.println("i am in CardInfoEvent");
            if(event.getInf().getSuccess()== NOTFOUND )
                MessageBoundary.displayError("Wrong Card number\n");
            else if(event.getInf().getSuccess()== ISACTIVE )
                MessageBoundary.displayError("User already logged in!\n");
            else{
                params.clear();
                params.add(event.getInf().getC());
                SimpleClient.setParams(params);
                LinkedList<Object> user = new LinkedList<>();
                user.add(event.getInf().getP());
                SimpleClient.setUser_Ob(user);
                if(event.getInf().getDetails().equals("$")){
                    try {
                        App.setRoot("otherActions");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        dispalyQueueBoundary.displayQueue(event.getInf().getDetails() , event.getInf().getP());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
    @Subscribe
    public  void onAppNumEvent(AppNumEvent event)throws IOException {
        Platform.runLater(() -> {
            if(event.getAppNum().getAppType().equalsIgnoreCase("NurseApp")) {
                MessageBoundary.displayInfo("your nurse Appointment number:\n               N-" + event.getAppNum().getAppnum());
            }
            else if(event.getAppNum().getAppType().equalsIgnoreCase("LabApp")) {
                MessageBoundary.displayInfo("your Lab Appointment number:\n                 L-" + event.getAppNum().getAppnum());
            }
            LinkedList<Object> message = new LinkedList<Object>();
            message.add("#Logout");
            message.add(SimpleClient.getUser_Ob().get(0));
            System.out.println("sending Logout from client to server");
            try {
                SimpleClient.getClient().sendToServer(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    @Subscribe
    public void onDoneAppEvent(DoneAppEvent event) throws IOException {
        Platform.runLater(() -> {
            if (event.getDoneAppBus().getPatientISHere().equalsIgnoreCase("NotHere"))
            {
                MessageBoundary.displayError("the patient is not here!");
            }
            else {
                LinkedList<Object> user = new LinkedList<>();
                if (SimpleClient.getUser_Ob().get(0).getClass().equals(Doctor.class)) {
                    user.add(event.getDoneAppBus().getDoctor());
                } else if (SimpleClient.getUser_Ob().get(0).getClass().equals(Nurse.class)) {
                    user.add(event.getDoneAppBus().getNurse());
                } else if (SimpleClient.getUser_Ob().get(0).getClass().equals(LaboratoryFacts.class)) {
                    user.add(event.getDoneAppBus().getLabFact());
                }
                SimpleClient.setUser_Ob(user);
                MessageBoundary.displayInfo("the Appointment is called Successfully");
            }
            try {
                setRoot("AppView");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    @Subscribe
    public void onDoneAppEvent(OrderAllAppsBusEvent event) throws IOException {
        Platform.runLater(() -> {
            if(event.getOrderAllAppsBus().getType().equalsIgnoreCase("NurseApps")){
                List<Object> NurseApps = event.getOrderAllAppsBus().getNApp();
                SimpleClient.setAppointmentTable(NurseApps);
                try {
                    App.setRoot("ViewPatientList");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(event.getOrderAllAppsBus().getType().equalsIgnoreCase("LabApps")){
                List<Object> LabApps = event.getOrderAllAppsBus().getLabApp();
                SimpleClient.setAppointmentTable(LabApps);
                try {
                     setRoot("ViewPatientList");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        });

    }
    @Subscribe
    public void onOpenOrCloseEvent(OpenOrCloseEvent event) throws IOException {
        Platform.runLater(() -> {
            OpenOrCloseClinic openOrCloseClinic=event.getOpenOrCloseClinic();
            if(openOrCloseClinic.isOpenOrclose()){
                params.clear();
                params.add(openOrCloseClinic.getClinicName());
                SimpleClient.setParams(params);
                try {
                    App.setRoot("CardVer");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else
            {
                MessageBoundary.displayWarning("The clinic is closed");
            }

        });
    }

    @Subscribe
    public  void onUSerBusEvent(UserBusEvent event) throws IOException{
        Platform.runLater(() -> {
            List<Object> user = new LinkedList<>();
            user.add(event.getUserBus().getPerson());
            SimpleClient.setUser_Ob(user);
            try {
                setRoot("AppView");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }

}