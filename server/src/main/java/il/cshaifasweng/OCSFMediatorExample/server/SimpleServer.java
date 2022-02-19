package il.cshaifasweng.OCSFMediatorExample.server;

//import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.Login;
import com.mysql.cj.util.DnsSrv;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.*;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.*;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.AppointmentData;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.DataClass;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.LoginData;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.WorkingHoursData;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;
import org.hibernate.engine.internal.ImmutableEntityEntry;

import java.io.IOException;
import java.time.LocalTime;
import java.util.LinkedList;

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
//			try {
//			DataClass.generateNewData();
//		    } catch (Exception e) {
//			e.printStackTrace();
//		}
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

		} else if (message.get(0).equals("#SendReminderEmail")) {
			int clinicId=(int) message.get(1);
			try {
				AppointmentData.sendReminderEmail(clinicId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.format("Sent Reminder Email to patient %s\n", client.getInetAddress().getHostAddress());

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

		}
	}
}