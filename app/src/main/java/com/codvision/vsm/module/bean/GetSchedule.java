package com.codvision.vsm.module.bean;

import java.util.Date;

/**
 * Created by sxy on 2019/5/11 22:23
 * todo
 */
public class GetSchedule {

    private String time;
    private int s_userid;

    public GetSchedule(String time, int s_userid) {
        this.time = time;
        this.s_userid = s_userid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getS_userid() {
        return s_userid;
    }

    public void setS_userid(int s_userid) {
        this.s_userid = s_userid;
    }



}
