package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

/**
 * this is the Questionnaire page the patient must full it when he want to book a corona test appointment
 */

public class QuestionnaireBoundary extends PatientMainBoundary{

    ObservableList<String> choices1= FXCollections.observableArrayList("Yes","NO");
    ObservableList<String> choices2= FXCollections.observableArrayList("0","1","2","Other");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> Answer1;

    @FXML
    private ChoiceBox<String> Answer2;

    @FXML
    private ChoiceBox<String> Answer3;

    @FXML
    private ChoiceBox<String > Answer4;

    @FXML
    private ChoiceBox<String> Answer5;

    @FXML
    void Back(ActionEvent event) throws IOException {
        App.setRoot("patientmain");
    }

    @FXML
    void SubmitButton(ActionEvent event) throws IOException {
        message.clear();
        message.add("#GetAllClinicName");
        message.add("CoronaTest"); // add flag
        SimpleClient.getClient().sendToServer(message);
    }

    @FXML
    void initialize() {
        assert Answer1 != null : "fx:id=\"Answer1\" was not injected: check your FXML file 'questionnaire.fxml'.";
        assert Answer2 != null : "fx:id=\"Answer2\" was not injected: check your FXML file 'questionnaire.fxml'.";
        assert Answer3 != null : "fx:id=\"Answer3\" was not injected: check your FXML file 'questionnaire.fxml'.";
        assert Answer4 != null : "fx:id=\"Answer4\" was not injected: check your FXML file 'questionnaire.fxml'.";
        assert Answer5 != null : "fx:id=\"Answer5\" was not injected: check your FXML file 'questionnaire.fxml'.";
        Answer1.setValue("Yes");
        Answer2.setValue("Yes");
        Answer3.setValue("Yes");
        Answer4.setValue("0");
        Answer5.setValue("Yes");
        Answer1.setItems(choices1);
        Answer2.setItems(choices1);
        Answer3.setItems(choices1);
        Answer4.setItems(choices2);
        Answer5.setItems(choices1);

    }

}
