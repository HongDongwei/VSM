package com.codvision.vsm.module.bean;

import java.io.Serializable;
/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public class Futureplan implements Serializable {

    private int id;

    private int f_userid;

    private int fcontent;

    private int ftime;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getF_userid() {
        return f_userid;
    }

    public void setF_userid(int f_userid) {
        this.f_userid = f_userid;
    }

    public int getFcontent() {
        return fcontent;
    }

    public void setFcontent(int fcontent) {
        this.fcontent = fcontent;
    }

    public int getFtime() {
        return ftime;
    }

    public void setFtime(int ftime) {
        this.ftime = ftime;
    }
}
