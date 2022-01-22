package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;


import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.util.Pair;

public class PrimaryController {
	/*synchronized void logIn(String username, String password) throws IOException {

		// create message and send it to the server
		Login message = new Login();
		SimpleClient.getClient().sendToServer(message);
	}*/
	@FXML
	void MainSystemBTR(ActionEvent event) throws IOException {
		LoginBoundary msg_Login = new LoginBoundary();
		Optional<Pair<String, String>> result = msg_Login.displayLogIn();
		if (result!=null){
			//logIn(result.get().getKey(), result.get().getValue());
			//Login message = new Login("#Login",result.get().getKey(),result.get().getValue(),0);
			LinkedList<Object> message = new LinkedList<Object>();
			message.add("#Login");
			message.add(result.get().getKey());
			message.add(result.get().getValue());
			System.out.println("sending Login from client to server");
			SimpleClient.getClient().sendToServer(message);
		}

	}

	@FXML
	void QueueManageBTR(ActionEvent event) {

	}

	@FXML
	void sendWarning(ActionEvent event) {
		try {
			SimpleClient.getClient().sendToServer("#warning");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
