package il.cshaifasweng.OCSFMediatorExample.client.Events;
import il.cshaifasweng.OCSFMediatorExample.entities.EventBus.Login;
public class LoginEvent {
    Login login;
    public Login getLogin() {return login;}
    public  LoginEvent(Login login){this.login=login;}
}
