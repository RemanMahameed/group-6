package il.cshaifasweng.OCSFMediatorExample.client.Boundaries;

import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public abstract class Boundary {
    String title = "";
    List<Object> params;
    Stage stage;

    public Boundary() {

        this.params = new LinkedList<>();
    }
    public List<Object> getParams() {
        return this.params;
    }

    public  void setParams(List<Object> params) {
        params = params;
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
}
