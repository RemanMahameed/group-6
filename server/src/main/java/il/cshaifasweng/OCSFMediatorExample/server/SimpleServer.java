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

import java.io.IOException;
import java.util.LinkedList;

public class SimpleServer extends AbstractServer {

	public SimpleServer(int port) {
		super(port);

		//try {
		//	DataClass.generateNewData();
		//} catch (Exception e) {
		//	e.printStackTrace();
		//}

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

		} else if (message.get(0).equals("#BackToMainPatientboundary")) {
			UpdateObject object = new UpdateObject();
			String type = (String) message.get(2);
			try {
				object = AppointmentData.getObjectByIdByType((int) message.get(1), type);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(object);
				System.out.format("Sent UpdateObject to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

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
			String clinicName= (String) message.get(1);
			String type=(String) message.get(2);
			try {
				WorkingHoursData.getWorkingHours(workingHours,clinicName,type);
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
			String clinicName= (String) message.get(2);
			String type=(String) message.get(1);
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

		}
	}
}