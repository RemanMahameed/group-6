package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.Events.*;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.*;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;

import java.util.LinkedList;
import java.util.List;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;
	private  static List<Object>  params=new LinkedList<>();
	private static  List<Object> user_Ob=new LinkedList<>();

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		if (msg.getClass().equals(Warning.class)) {
			EventBus.getDefault().post(new WarningEvent((Warning) msg));

		} else if (msg.getClass().equals(Login.class)) {
			System.out.println("I am at simpleclient login!");
			System.out.println("success is:" + ((Login) msg).getSuccess());
			EventBus.getDefault().post(new LoginEvent((Login) msg));

		} else if (msg.getClass().equals(LogOut.class)) {
			EventBus.getDefault().post(new LogOutEvent((LogOut) msg));

		} else if (msg.getClass().equals(DoctorApp.class)) {
			EventBus.getDefault().post(new DoctorAppEvent((DoctorApp) msg));

		} else if (msg.getClass().equals(ProDoctorsList.class)) {
			EventBus.getDefault().post(new ProEvent((ProDoctorsList) msg));

		} else if (msg.getClass().equals(ScheduledApp.class)) {
			EventBus.getDefault().post(new ScheduledAppEvent((ScheduledApp) msg));

		}else if (msg.getClass().equals(UpdateObject.class)) {
			EventBus.getDefault().post(new UpdateObjectEvent((UpdateObject) msg));

		}else if (msg.getClass().equals(ClinicName.class)) {
			EventBus.getDefault().post(new ClinicNameEvent((ClinicName) msg));

		}else if (msg.getClass().equals(FreeAppointment.class)) {
			EventBus.getDefault().post(new FreeAppointmentEvent((FreeAppointment) msg));

		}else if (msg.getClass().equals(WorkingHours.class)) {
			EventBus.getDefault().post(new WorkingHoursEvent((WorkingHours) msg));

		}else if (msg.getClass().equals(DoctorNames.class)) {
			EventBus.getDefault().post(new DoctorNameEvent((DoctorNames) msg));
		}else if (msg.getClass().equals(ClinicBus.class)) {
			EventBus.getDefault().post(new ClinicBusEvent((ClinicBus) msg));
		}else if (msg.getClass().equals(cardinfo.class)) {
			EventBus.getDefault().post(new CardInfoEvent((cardinfo) msg));
		}
	}
	
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

	public static List<Object> getParams() {
		return params;
	}

	public static List<Object> getUser_Ob() {
		return user_Ob;
	}

	public static void setUser_Ob(List<Object> user_Ob) {
		SimpleClient.user_Ob = user_Ob;
	}

	public static void setParams(List<Object> params) {
		SimpleClient.params = params;
	}
}
