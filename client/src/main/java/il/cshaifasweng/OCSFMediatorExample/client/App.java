package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.client.Boundaries.MessageBoundary;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private SimpleClient client;

    @Override
    public void start(Stage stage) throws IOException {
    	EventBus.getDefault().register(this);
    	client = SimpleClient.getClient();
    	client.openConnection();
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    

    @Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
    	EventBus.getDefault().unregister(this);
		super.stop();
	}
    
    @Subscribe
    public void onWarningEvent(WarningEvent event) {
    	Platform.runLater(() -> {
    		Alert alert = new Alert(AlertType.WARNING,
        			String.format("Message: %s\nTimestamp: %s\n",
        					event.getWarning().getMessage(),
        					event.getWarning().getTime().toString())
        	);
        	alert.show();
    	});
    	
    }
    @Subscribe
    /* hon bdna nf7s sho el success
    * 0= failed
    * 1=patient
    * 2..
    *  */
    public void onLoginEvent(LoginEvent event) throws IOException {
        Platform.runLater(() -> {
            if(event.getLogin().getSuccess()==-1 )
                MessageBoundary.displayError("Wrong username or password\n");
            else if(event.getLogin().getSuccess()==-2)
                MessageBoundary.displayError("User already logged in!\n");
            else if(event.getLogin().getSuccess()== 1 )     //Is a doctor
            {
                //List<Object> params = new LinkedList<>();
                try {
                    setRoot("doctormain");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }

	public static void main(String[] args) {
        launch();
    }

}