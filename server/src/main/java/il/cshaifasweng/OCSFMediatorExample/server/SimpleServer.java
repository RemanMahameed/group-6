package il.cshaifasweng.OCSFMediatorExample.server;

//import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.Login;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.*;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.AppointmentData;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.DataClass;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.LoginData;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.WorkingHoursData;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class SimpleServer extends AbstractServer {

	public SimpleServer(int port) {
		super(port);

//		try {
//			DataClass.generateNewData();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		getFreeClinicDoctorApp()

	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {

		String msgString = msg.toString();
		//System.out.format("%s\n",msgString);*/
		LinkedList<Object> message = (LinkedList<Object>) (msg);
		System.out.println("Message = " + message.get(0) + ", reached server");
		if (msgString.startsWith("#warning")) {
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (message.get(0).equals("#Login")) {
//			try {
//				LoginData.NotActive();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			try {
			DataClass.generateNewData();
		    } catch (Exception e) {
			e.printStackTrace();
		}
			System.out.format("I am in the else \n");
			Login login = null;
			try {
				login = LoginData.CheckExcision((String) message.get(1), (String) message.get(2));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(login);
				System.out.format("Sent login to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (message.get(0).equals("#Logout")) {
			LogOut logOut = new LogOut("Success");
			try {
				LoginData.logOutUser(message.get(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(logOut);
				System.out.format("Sent logout to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (message.get(0).equals("#AppFamilyChild")) {
			DoctorApp doctorApp = new DoctorApp();
			try {
				doctorApp = (AppointmentData.getFreeClinicDoctorApp((int)message.get(1)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(doctorApp);
				System.out.format("Sent doctorApp to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (message.get(0).equals("#SetSelectedAppointement")) {
			//Patient patient = (Patient) message.get(1);
			int patientId=(int)message.get(1);
			try {
				AppointmentData.SetSelectedDoctorAppointment(patientId, (Doctor) message.get(2), (DoctorAppointment) message.get(3));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.format("Set doctorApp %s\n", client.getInetAddress().getHostAddress());


		} else if (message.get(0).equals("#specilizationChoice ")) {
			ProDoctorsList doctors = null;
			try {
				doctors = AppointmentData.getdoctorsofsp((String) message.get(1), (String) message.get(2));
				if (doctors == null)
					System.out.println("there is no doctor");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(doctors);
				System.out.format("Sent pro doctors to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}else if (message.get(0).equals("#ProDocChoice")) {
			DoctorApp doctorApp = new DoctorApp();
			Doctor doc = null;
			try {
				doc = DataClass.getDoctorById(Integer.valueOf((String)message.get(1)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Patient P = null;
			try {
				P = DataClass.getPatientById(Integer.valueOf((String)message.get(2)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				//System.out.println("the clinic is: " + message.get(3));
				doctorApp = AppointmentData.getFreeProDoctorApp(doc ,P ,doc.getRole() ,(String) message.get(3) );
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(doctorApp);
				System.out.format("Sent doctorApp to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (message.get(0).equals("#ViewAppointment")) {
			String type = (String) message.get(1);
			int patientId = (int) message.get(2);
			ScheduledApp scheduledApp = new ScheduledApp();
			try {
				scheduledApp = AppointmentData.getScheduledAppointment(type, patientId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(scheduledApp);
				System.out.format("Sent doctorApp to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (message.get(0).equals("#CancelAppointment")) {
			String type=(String) message.get(2);
			try {
				AppointmentData.cancelAppointment((Appointment) message.get(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.format("Cancel App  to client %s\n", client.getInetAddress().getHostAddress());

//		} else if (message.get(0).equals("#BackToMainPatientboundary")) {
//			UpdateObject object = new UpdateObject();
//			String type = (String) message.get(2);
//			try {
//				object = AppointmentData.getObjectByIdByType((int) message.get(1), type);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			try {
//				client.sendToClient(object);
//				System.out.format("Sent UpdateObject to client %s\n", client.getInetAddress().getHostAddress());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}

		} else if (message.get(0).equals("#GetAllClinicName")) {
			ClinicName clinicName = new ClinicName();
			try {
				clinicName = AppointmentData.getAllClinicName((String) message.get(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(clinicName);
				System.out.format("Sent ClinicName to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (message.get(0).equals("#GetFreeApp")) {
			int clinicId = (int) message.get(1);
			String appType = (String) message.get(2);
			int PatientId = (int) message.get(3);
			FreeAppointment freeAppointment = new FreeAppointment();
			try {
				freeAppointment = AppointmentData.getFreeCoronaOrVaccine(clinicId, appType, PatientId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(freeAppointment);
				System.out.format("Sent FreeAppointment to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}else if (message.get(0).equals("#SetSelectedVaccineApp")) {
			VaccineAppointment vaccineAppointment=(VaccineAppointment)message.get(1);
			int clinicId=(int)message.get(2);
			int patientId=(int)message.get(3);
			try {
				AppointmentData.setSelectedVaccineApp(vaccineAppointment,clinicId,patientId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.format("Set Vaccine Appointment %s\n", client.getInetAddress().getHostAddress());

		}else if (message.get(0).equals("#setSelectedCoronaTestApp")) {
			CoronaTestAppointment coronaTestAppointment=(CoronaTestAppointment) message.get(1);
			int clinicId=(int)message.get(2);
			int patientId=(int)message.get(3);
			try {
				AppointmentData.setSelectedCoronaTestApp(coronaTestAppointment,clinicId,patientId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.format("Set Corona Appointment %s\n", client.getInetAddress().getHostAddress());

		}else if (message.get(0).equals("#getWorkingHours")) {
			WorkingHours workingHours=new WorkingHours();
			String doctorId=(String)message.get(1);
			String clinicName= (String) message.get(2);
			String type=(String) message.get(3);
			try {
				WorkingHoursData.getWorkingHours(workingHours,clinicName,type,doctorId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(workingHours);
				System.out.format("Sent WorkingHours to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (message.get(0).equals("#geClinicDoctor")) {
			DoctorNames doctorNames =new DoctorNames();
			String type=(String) message.get(1);
			String clinicName= (String) message.get(2);
			try {
				WorkingHoursData.getClinicDoctors(doctorNames,clinicName,type);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(doctorNames);
				System.out.format("Sent DoctorsName to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}else if (message.get(0).equals("#checkClinicisOpen")) {
			String clinicName=(String) message.get(1);
			OpenOrCloseClinic openOrCloseClinic=new OpenOrCloseClinic();
			try {
				openOrCloseClinic=AppointmentData.checkIfCISOpen(clinicName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(openOrCloseClinic);
				System.out.format("Sent openOrclose to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (message.get(0).equals("#SendReminderEmail")) {
			int clinicId=(int) message.get(1);
			try {
				AppointmentData.openClinic(clinicId);

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.format("Sent Reminder Email to patient %s\n", client.getInetAddress().getHostAddress());

		}else if (message.get(0).equals("#CloseSystem")) {
			int clinicId=(int) message.get(1);
			try {
				AppointmentData.CloseClinic(clinicId);

			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.format("Close system %s\n", client.getInetAddress().getHostAddress());

		}else if (message.get(0).equals("#SetNewWorkingHours")) {
			String type=(String) message.get(1);
			String clinicName=(String) message.get(2);
			LocalTime[][] newWorkingHours=(LocalTime[][])message.get(3);
			int doctorId=(int) message.get(4);
			try {
				WorkingHoursData.SetNewWorkingHours(clinicName,type,newWorkingHours,doctorId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.format("Set new Working Hours %s\n", client.getInetAddress().getHostAddress());

		}else if (message.get(0).equals("#DocClinicName")){
			ClinicBus clinic = new ClinicBus();
			try {
				System.out.println("my clinic is: " + (String) message.get(1));
				Clinic clin = DataClass.getClinicByName((String) message.get(1));
				System.out.println("my clinic is: " + clin.getClinicType());
				 clinic.setClinic(clin);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(clinic);
				System.out.format("Sent clinic to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}


		}else if (message.get(0).equals("#HMClinicName")) {
			ReportBus report = new ReportBus();
			try {
				System.out.println("my clinic is: " + (String) message.get(1));
				Clinic clin = DataClass.getClinicByName((String) message.get(1));
				System.out.println("my clinic is: " + clin.getClinicType());
				report.setClinic(clin);
				report.setReport_type((String) message.get(2));
				report.setUser_type("HM");
//				clinic.setFlag(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(report);
				System.out.format("Sent report to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}else if (message.get(0).equals("#CardNum")) {
			System.out.println(" I am in card num");
			String CardString = (String) message.get(1);
			long CardNum = Long.parseLong(CardString);
			String Clinic = (String) message.get(2);
			//System.out.println(" I am in card num1");
			cardinfo info = new cardinfo() ;
			try {
				//System.out.println(" I am in card num2");
				info = LoginData.CheckPatientCard(CardNum ,Clinic );
				//System.out.println(" I am in card num3");
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(info);
				System.out.format("Sent Card verification to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}else if (message.get(0).equals("#NurseApp")) {
			AppNum appNum = new AppNum();
			appNum.setAppType("NurseApp");
			NurseAppointment nurseAppointment=new NurseAppointment();
			Patient patientNurse = new Patient();
			try {
				patientNurse = DataClass.getPatientById(Integer.valueOf((String)message.get(1)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Clinic clinic = new Clinic();
			try {
				clinic = DataClass.getClinicById(Integer.valueOf((String)message.get(2)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			int numOfapp=0;
			try {
				numOfapp= AppointmentData.NumAppAndSetNurseAppointment(patientNurse, clinic,nurseAppointment);

			} catch (Exception e) {
				e.printStackTrace();
			}
			appNum.setAppnum(numOfapp);

			try {
				client.sendToClient(appNum);
				System.out.format("Sent Nurse Appointment to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (message.get(0).equals("#LabApp")) {
			AppNum appNum = new AppNum();
			appNum.setAppType("LabApp");
			LaboratoryFactsAppointment laboratoryFactsAppointment= new LaboratoryFactsAppointment();
			Patient patient = new Patient();
			try {
				patient = DataClass.getPatientById(Integer.valueOf((String)message.get(1)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Clinic clinic = new Clinic();
			try {
				clinic = DataClass.getClinicById(Integer.valueOf((String)message.get(2)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			int numOfapp=0;
			try {
				numOfapp=AppointmentData.NumAppAndSetLabAppointment(patient, clinic,laboratoryFactsAppointment);
			} catch (Exception e) {
				e.printStackTrace();
			}
			appNum.setAppnum(numOfapp);

			try {
				client.sendToClient(appNum);
				System.out.format("Sent Lab Appointment to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (message.get(0).equals("#SelectedDoctoreApp")){
			String AppDetails = (String) message.get(1);
			int indexOfP = AppDetails.indexOf("P");
			int EndOrBagan;
			EndOrBagan=indexOfP-1;
			String AppIdStr = AppDetails.substring(8,EndOrBagan);
			int AppId = Integer.parseInt(AppIdStr);
			EndOrBagan=indexOfP+12;
			String PatientIdStr = AppDetails.substring(EndOrBagan,(AppDetails.indexOf("\n")));
			int PatientId = Integer.parseInt(PatientIdStr);
			Patient patient=new Patient();
			try {
				patient= DataClass.getPatientById(PatientId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Doctor doctor=new Doctor();
			try {
				doctor=DataClass.getDoctorById(Integer.valueOf((String)message.get(2)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Clinic clinic=new Clinic();
			try {
				clinic=DataClass.getClinicById(Integer.valueOf((String)message.get(3)));
			} catch (Exception e) {
				e.printStackTrace();
			}

			DoneAppBus appIsDoneBus = AppointmentData.SetDoctorAppAsDone(patient,doctor,clinic,AppId);
			try {
				client.sendToClient(appIsDoneBus);
				System.out.format("Sent Done Doctor Appointment to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();

			}

		}else if (message.get(0).equals("#SelectedNurseApp")){

			String AppDetails = (String) message.get(1);
			int indexOfP = AppDetails.indexOf("P");
			int EndOrBagan;
			EndOrBagan=indexOfP-1;
			String AppIdStr = AppDetails.substring(8,EndOrBagan);
			int AppId = Integer.parseInt(AppIdStr);
			EndOrBagan=indexOfP+12;
			String PatientIdStr = AppDetails.substring(EndOrBagan,(AppDetails.indexOf("\n")));
			int PatientId = Integer.parseInt(PatientIdStr);
			Patient patient=new Patient();
			try {
				 patient= DataClass.getPatientById(PatientId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Nurse nurse=new Nurse();
			try {
				 nurse=DataClass.getNurseById(Integer.valueOf((String)message.get(2)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Clinic clinic=new Clinic();
			try {
				 clinic=DataClass.getClinicById(Integer.valueOf((String)message.get(3)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			DoneAppBus appIsDoneBus = AppointmentData.SetNurseAppAsDone(patient,nurse,clinic,AppId);
			try {
				client.sendToClient(appIsDoneBus);
				System.out.format("Sent Done Nurse Appointment to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if (message.get(0).equals("#SelectedLabFactApp")){
			String AppDetails = (String) message.get(1);
			int indexOfP = AppDetails.indexOf("P");
			int EndOrBagan;
			EndOrBagan=indexOfP-1;
			String AppIdStr = AppDetails.substring(8,EndOrBagan);
			int AppId = Integer.parseInt(AppIdStr);
			EndOrBagan=indexOfP+12;
			String PatientIdStr = AppDetails.substring(EndOrBagan,(AppDetails.indexOf("\n")));
			int PatientId = Integer.parseInt(PatientIdStr);
			Patient patient=new Patient();
			try {
				patient= DataClass.getPatientById(PatientId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			LaboratoryFacts labFact=new LaboratoryFacts();
			try {
				labFact=DataClass.getLabFactById(Integer.valueOf((String)message.get(2)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			Clinic clinic=new Clinic();
			try {
				clinic=DataClass.getClinicById(Integer.valueOf((String)message.get(3)));
			} catch (Exception e) {
				e.printStackTrace();
			}
			DoneAppBus appIsDoneBus = AppointmentData.SetLabAppAsDone(patient,labFact,clinic,AppId);
			try {
				client.sendToClient(appIsDoneBus);
				System.out.format("Sent Done Lab Fact Appointment to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}


		}else if (message.get(0).equals("#oderAllLabApps")){
			orderAllAppsBus orderAllAppsBus=new orderAllAppsBus();
			List<Object> laboratoryFactsAppointments=new LinkedList<>();
			try {
				 laboratoryFactsAppointments=DataClass.getAllLaBAppointment();
			} catch (Exception e) {
				e.printStackTrace();
			}
			orderAllAppsBus.setType("LabApps");
			orderAllAppsBus.setLabApp(laboratoryFactsAppointments);
			try {
				client.sendToClient(orderAllAppsBus);
				System.out.format("Sent All Labs Appointments to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}else if (message.get(0).equals("#oderAllNurseApps")){
			orderAllAppsBus orderAllAppsBus=new orderAllAppsBus();
			List<Object> nurseApointments=new LinkedList<>();
			try {
				nurseApointments=DataClass.getAllNurseAppointment();
			} catch (Exception e) {
				e.printStackTrace();
			}
			orderAllAppsBus.setType("NurseApps");
			orderAllAppsBus.setNApp(nurseApointments);
			try {
				client.sendToClient(orderAllAppsBus);
				System.out.format("Sent All Nurse Appointments to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}