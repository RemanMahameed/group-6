package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Login;
import il.cshaifasweng.OCSFMediatorExample.server.DataControl.LoginData;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.util.LinkedList;

import il.cshaifasweng.OCSFMediatorExample.entities.Warning;

public class SimpleServer extends AbstractServer {

	public SimpleServer(int port) {
		super(port);
		
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		//try {
		//	DataClass.generateNewData();
		//} catch (Exception e) {
		//	e.printStackTrace();
		//}
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
		}
		if (message.get(0).equals("#Login")) {
			System.out.format("I am in the else \n");
			Login login= null;
			try {
				login = LoginData.CheckExcision((String) message.get(1),(String) message.get(2));
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				client.sendToClient(login);
				System.out.format("Sent login to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		};

	}

}
