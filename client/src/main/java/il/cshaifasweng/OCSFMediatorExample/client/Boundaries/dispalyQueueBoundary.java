package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Patient;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;

/**
 * show the note with details of the relevant appointment if it exist
 */

public class dispalyQueueBoundary {
    static public void displayQueue(String dialog , Patient patient) throws IOException {
        System.out.println("I am in display");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("showing number");
        System.out.println(dialog);
        alert.setHeaderText(dialog);
        alert.setContentText("press ok if you are interested in scheduling a queue");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()== ButtonType.OK)
        {
            App.setRoot("otherActions");
        }else{
            System.out.println("log out from queue");
            LinkedList<Object> message = new LinkedList<Object>();
            message.add("#Logout");
            message.add(patient);
            System.out.println("sending Logout from client to server");
            SimpleClient.getClient().sendToServer(message);

        }
    }
}
