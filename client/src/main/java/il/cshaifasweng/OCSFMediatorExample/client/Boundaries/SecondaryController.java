package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}