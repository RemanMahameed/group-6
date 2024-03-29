package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * this is the collection of alert that we used like information or error...
 * we match each situation to the wanted alert
 */

public class MessageBoundary {
    static public void displayInfo(String dialouge) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(dialouge);

        alert.showAndWait();
    }

    static public void displayWarning(String dialouge) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(dialouge);

        alert.showAndWait();
    }

    static public void displayError(String dialouge) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(dialouge);

        alert.showAndWait();
    }
    static public void displayGeenPass(String dialouge) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Green Pass");
        alert.setHeaderText("Green Pass");
        alert.setContentText(dialouge);

        alert.showAndWait();
    }

}

