package il.cshaifasweng.OCSFMediatorExample.server;

//import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.Login;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.*;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Appointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.DoctorAppointment;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.AppointmentData;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.DataClass;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.LoginData;
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
				doctorApp = (AppointmentData.getFreeClinicDoctorApp(message.get(1)));
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
			Patient patient = (Patient) message.get(1);
			try {
				AppointmentData.SetSelectedDoctorAppointment(patient, (Doctor) message.get(2), (DoctorAppointment) message.get(3));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(patient);
				System.out.format("Sent doctorApp to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
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
			Patient patient = (Patient) message.get(2);
			ScheduledApp scheduledApp = new ScheduledApp();
			try {
				scheduledApp = AppointmentData.getSchuledAppointment(type, patient);
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
			try {
				AppointmentData.cancelAppointment((DoctorAppointment) message.get(1));
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.format("Sent doctorApp to client %s\n", client.getInetAddress().getHostAddress());
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
				System.out.format("Sent doctorApp to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}