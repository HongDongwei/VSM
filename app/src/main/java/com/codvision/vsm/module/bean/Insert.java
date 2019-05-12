package com.codvision.vsm.module.bean;

import java.util.Date;

/**
 * Created by sxy on 2019/5/11 15:56
 * todo
 */
public class Insert {


    private String time;

    private String place;

    private String thing;

    private String note;

    private int degree;

    private String label;

    private int isremind;

    private String type;

    private int state;

    private String startdate;

    private String enddate;

    private int s_userid;

    private int status;

    public Insert(String time, String place, String thing, String note, int degree, String label, int isremind, String type, int state, String startdate, String enddate, int s_userid, int status) {
        this.status = status;
        this.time = time;
        this.place = place;
        this.thing = thing;
        this.note = note;
        this.degree = degree;
        this.label = label;
        this.isremind = isremind;
        this.type = type;
        this.state = state;
        this.startdate = startdate;
        this.enddate = enddate;
        this.s_userid = s_userid;
    }

    public Insert(Schedule schedule) {
        this.status = schedule.getId();
        //this.time = schedule.getTime();
        this.place = schedule.getPlace();
        this.thing = schedule.getThing();
        this.note = schedule.getNote();
        this.degree = schedule.getDegree();
        this.label = schedule.getLabel();
        this.isremind = schedule.getIsremind();
        this.type = schedule.getType();
        this.state = schedule.getState();
        // this.startdate = schedule.getStartdate();
        // this.enddate = schedule.getEnddate();
        this.s_userid = schedule.getS_userid();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getIsremind() {
        return isremind;
    }

    public void setIsremind(int isremind) {
        this.isremind = isremind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate() {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public int getS_userid() {
        return s_userid;
    }

    public void setS_userid(int s_userid) {
        this.s_userid = s_userid;
    }
}
