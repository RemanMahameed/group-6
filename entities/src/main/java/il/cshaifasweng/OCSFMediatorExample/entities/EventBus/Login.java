package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import java.io.Serializable;

public class Login implements Serializable {

    //private static final long serialVersionUID = -8224097662914849956L;

    private String username;
    private String password;
    private int success ;
    private Object object;

    public Login( String username, String password, int success) {
        this.username = username;
        this.password = password;
        this.success = success;
    }

    public Login(String username, String password, int success, Object object) {
        this.username = username;
        this.password = password;
        this.success = success;
        this.object = object;
    }

    public Login() {
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
