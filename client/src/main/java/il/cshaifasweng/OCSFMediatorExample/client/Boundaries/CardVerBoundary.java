package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;


import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.util.LinkedList;

public class CardVerBoundary extends Boundary{

    String clinicName = (String) params.get(0);

    @FXML
    private PasswordField password = new PasswordField();

    @FXML
    void BackAction(ActionEvent event) {
        try {
            App.setRoot("QueueManagement");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void SubmitAction(ActionEvent event) {
        String cardnum = password.getText();
        LinkedList<String> pass = new LinkedList<>();
        pass.add("#CardNum");
        pass.add(cardnum);
        pass.add(clinicName);
        try {
            SimpleClient.getClient().sendToServer(pass);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



}
