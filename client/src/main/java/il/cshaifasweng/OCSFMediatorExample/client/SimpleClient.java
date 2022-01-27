package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.DoctorApp;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.LogOut;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.Login;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.Warning;

import java.util.LinkedList;
import java.util.List;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;
	private  static List<Object>  params=new LinkedList<>();

	private SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		if (msg.getClass().equals(Warning.class)) {
			EventBus.getDefault().post(new WarningEvent((Warning) msg));

		}else if (msg.getClass().equals(Login.class)) {
			System.out.println("I am at simpleclient login!");
			System.out.println("success is:" + ((Login) msg).getSuccess());
			EventBus.getDefault().post(new LoginEvent((Login) msg));

		}else if (msg.getClass().equals(LogOut.class)) {
			EventBus.getDefault().post(new LogOutEvent((LogOut)msg));

		}else if (msg.getClass().equals(DoctorApp.class)) {
			EventBus.getDefault().post(new DoctorAppEvent((DoctorApp) msg));
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

	public static void setParams(List<Object> params) {
		SimpleClient.params = params;
	}
}
