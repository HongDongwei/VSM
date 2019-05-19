package com.codvision.vsm.module.bean;

/**
 * Created by sxy on 2019/5/11 22:23
 * todo
 */
public class ScheduleSubmit {

    private int id;
    private int state;

    public ScheduleSubmit(int id, int state) {
        this.id = id;
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
