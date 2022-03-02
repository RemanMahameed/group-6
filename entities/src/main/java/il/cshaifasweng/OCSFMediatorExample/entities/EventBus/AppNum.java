package il.cshaifasweng.OCSFMediatorExample.entities.EventBus;

import java.io.Serializable;

/**
 * passing the appointment number
 */

public class AppNum implements Serializable {
    int Appnum;
    String AppType;

    public AppNum(){

    }

    public AppNum(int appnum) {
        Appnum = appnum;
    }

    public int getAppnum() {
        return Appnum;
    }

    public void setAppnum(int appnum) {
        Appnum = appnum;
    }

    public String getAppType() {
        return AppType;
    }

    public void setAppType(String appType) {
        AppType = appType;
    }
}
