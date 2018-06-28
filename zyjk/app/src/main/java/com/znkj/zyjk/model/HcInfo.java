package com.znkj.zyjk.model;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by lenovo-pc on 2018-01-29.
 */
public class HcInfo implements Serializable {

    private String hcbb;//核查版本
    private String hclx;//核查类型，jq，mh，cl，zh     精确、模糊（人员）车辆综合

    private String hcr_xm;
    private String hcr_sfzh;
    private String hcr_sjh;
    private String hcdz;

    private String bhcr_jnjw;
    private String bhcr_xm;
    private String bhcr_sfzh;
    private String bhcr_sjh;
    private String bhcr_hjszd;
    private String bhcr_mz;
    private String bhcr_csrqks;
    private String bhcr_csrqjs;

    private String cl_clhm;
    private String cl_cllx;
    private String cl_scrs;
    private String cl_scwps;
    private JSONObject cl_wjwp;

    public String getHcbb() {
        return hcbb;
    }

    public void setHcbb(String hcbb) {
        this.hcbb = hcbb;
    }

    public String getHclx() {
        return hclx;
    }

    public void setHclx(String hclx) {
        this.hclx = hclx;
    }

    public String getHcr_xm() {
        return hcr_xm;
    }

    public void setHcr_xm(String hcr_xm) {
        this.hcr_xm = hcr_xm;
    }

    public String getHcr_sfzh() {
        return hcr_sfzh;
    }

    public void setHcr_sfzh(String hcr_sfzh) {
        this.hcr_sfzh = hcr_sfzh;
    }

    public String getHcr_sjh() {
        return hcr_sjh;
    }

    public void setHcr_sjh(String hcr_sjh) {
        this.hcr_sjh = hcr_sjh;
    }

    public String getHcdz() {
        return hcdz;
    }

    public void setHcdz(String hcdz) {
        this.hcdz = hcdz;
    }

    public String getBhcr_jnjw() {
        return bhcr_jnjw;
    }

    public void setBhcr_jnjw(String bhcr_jnjw) {
        this.bhcr_jnjw = bhcr_jnjw;
    }

    public String getBhcr_xm() {
        return bhcr_xm;
    }

    public void setBhcr_xm(String bhcr_xm) {
        this.bhcr_xm = bhcr_xm;
    }

    public String getBhcr_sfzh() {
        return bhcr_sfzh;
    }

    public void setBhcr_sfzh(String bhcr_sfzh) {
        this.bhcr_sfzh = bhcr_sfzh;
    }

    public String getBhcr_sjh() {
        return bhcr_sjh;
    }

    public void setBhcr_sjh(String bhcr_sjh) {
        this.bhcr_sjh = bhcr_sjh;
    }

    public String getBhcr_hjszd() {
        return bhcr_hjszd;
    }

    public void setBhcr_hjszd(String bhcr_hjszd) {
        this.bhcr_hjszd = bhcr_hjszd;
    }

    public String getBhcr_mz() {
        return bhcr_mz;
    }

    public void setBhcr_mz(String bhcr_mz) {
        this.bhcr_mz = bhcr_mz;
    }

    public String getBhcr_csrqks() {
        return bhcr_csrqks;
    }

    public void setBhcr_csrqks(String bhcr_csrqks) {
        this.bhcr_csrqks = bhcr_csrqks;
    }

    public String getBhcr_csrqjs() {
        return bhcr_csrqjs;
    }

    public void setBhcr_csrqjs(String bhcr_csrqjs) {
        this.bhcr_csrqjs = bhcr_csrqjs;
    }

    public String getCl_clhm() {
        return cl_clhm;
    }

    public void setCl_clhm(String cl_clhm) {
        this.cl_clhm = cl_clhm;
    }

    public String getCl_cllx() {
        return cl_cllx;
    }

    public void setCl_cllx(String cl_cllx) {
        this.cl_cllx = cl_cllx;
    }

    public String getCl_scrs() {
        return cl_scrs;
    }

    public void setCl_scrs(String cl_scrs) {
        this.cl_scrs = cl_scrs;
    }

    public String getCl_scwps() {
        return cl_scwps;
    }

    public void setCl_scwps(String cl_scwps) {
        this.cl_scwps = cl_scwps;
    }

    public JSONObject getCl_wjwp() {
        return cl_wjwp;
    }

    public void setCl_wjwp(JSONObject cl_wjwp) {
        this.cl_wjwp = cl_wjwp;
    }
}
