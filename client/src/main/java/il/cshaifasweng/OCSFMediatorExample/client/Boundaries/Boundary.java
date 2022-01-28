package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Table.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public abstract class Boundary {
    protected String title = "";
    protected List<Object> params;
    protected List<Object> user_Ob;
    protected Stage stage;
    protected  LinkedList<Object> message = new LinkedList<Object>();


    public Boundary() {

        this.params = SimpleClient.getParams();
        this.user_Ob = SimpleClient.getUser_Ob();
    }

    public LinkedList<Object> getMessage() {
        return message;
    }

    public void setMessage(LinkedList<Object> message) {
        this.message = message;
    }

    public List<Object> getParams() {
        return this.params;
    }

    public  void setParams(List<Object> params) {
        params = params;
    }

    public List<Object> getUser_Ob() {
        return user_Ob;
    }

    public void setUser_Ob(List<Object> user_Ob) {
        this.user_Ob = user_Ob;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private Button LogoutBTR;

    @FXML
    void LogOut(ActionEvent event) throws IOException {
        System.out.println("buttom log out");
        //Object object= (Object) SimpleClient.getParams().get(0);
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#Logout");
        message.add(user_Ob.get(0));
        System.out.println("sending Logout from client to server");
        SimpleClient.getClient().sendToServer(message);
    }
}




