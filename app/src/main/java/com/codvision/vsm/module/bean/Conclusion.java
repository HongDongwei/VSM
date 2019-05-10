package com.codvision.vsm.module.bean;

import java.io.Serializable;
/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public class Conclusion implements Serializable {

    private int id;

    private int c_userid;

    private int content;

    private int time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getC_userid() {
        return c_userid;
    }

    public void setC_userid(int c_userid) {
        this.c_userid = c_userid;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
