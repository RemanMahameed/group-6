package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
public abstract class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id")
    protected int id;
    protected String FirstName;
    protected String LastName;
    protected String phoneNum;
    protected String email;
    protected String UserName;
    @Column(name = "PassWord")
    protected String PassWord;
    protected boolean Active;

    public Person(String firstName, String lastName, String phoneNum, String email, String userName, String passWord) {
        FirstName = firstName;
        LastName = lastName;
        this.phoneNum = phoneNum;
        this.email = email;
        UserName = userName;
        PassWord = passWord;
        Active= false;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public boolean getActive() {
        return Active;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public void setActive(boolean active) {
        Active = active;
    }
}
