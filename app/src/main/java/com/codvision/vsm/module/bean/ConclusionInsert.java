package com.codvision.vsm.module.bean;

import java.io.Serializable;

/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public class ConclusionInsert implements Serializable {


    private int c_userid;

    private String content;

    private String time;

    public ConclusionInsert(int c_userid, String content, String time) {
        this.c_userid = c_userid;
        this.content = content;
        this.time = time;
    }


    public int getC_userid() {
        return c_userid;
    }

    public void setC_userid(int c_userid) {
        this.c_userid = c_userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
