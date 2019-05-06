package com.codvision.vsm.bean;

import java.sql.Date;

public class Schedule {
    private int id;
    private Date time;
    private String place;
    private String thing;
    private String note;
    private int degree;
    private String type;
    private int state;
    private Date startdate;
    private Date enddate;

    public Schedule(int id, Date time, String place, String thing, String note, int degree, String type, int state, Date startdate, Date enddate) {
        this.id = id;
        this.time = time;
        this.place = place;
        this.thing = thing;
        this.note = note;
        this.degree = degree;
        this.type = type;
        this.state = state;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }


}
