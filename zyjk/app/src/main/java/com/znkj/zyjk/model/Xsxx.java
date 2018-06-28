package com.znkj.zyjk.model;

import java.io.Serializable;

/**
 * Created by lenovo-pc on 2018-01-29.
 */
public class Xsxx implements Serializable {

    private String xsnr;
    private String sfzh;
    private String xm;
    private String sjh;

    public Xsxx(String xm, String sjh, String password, String userName){
       // this.phoneNumber = phoneNumber;
        this.xsnr = password;
        this.sfzh = userName;
        this.xm = xm;
        this.sjh = sjh;

        //registerTime = new Date();
    }
    public Xsxx(){

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

    public String getXsnr() {
        return xsnr;
    }

    public void setXsnr(String xsnr) {
        this.xsnr = xsnr;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }
}
