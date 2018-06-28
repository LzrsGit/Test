package com.znkj.zyjk.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lenovo-pc on 2018-01-29.
 */
public class User implements Serializable {

    private int id;
   // private String phoneNumber;
    private String password;
    private String userName;
    private String emailAddress;
    private Date registerTime;
    private String xm;
    private String sjh;
    public static String PASSWORD = "passWord";
    public static String USERNAME = "userName";

    public User(String xm, String sjh, String password, String userName){
       // this.phoneNumber = phoneNumber;
        this.password = password;
        this.userName = userName;
        this.xm = xm;
        this.sjh = sjh;

        //registerTime = new Date();
    }
    public User(){

    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
   /* public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }*/
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public Date getRegisterTime() {
        return registerTime;
    }
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }
}
