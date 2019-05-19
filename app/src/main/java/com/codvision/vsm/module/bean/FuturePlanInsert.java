package com.codvision.vsm.module.bean;

import java.io.Serializable;

/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public class FuturePlanInsert implements Serializable {


    private int f_userid;


    private String fcontent;

    private String ftime;

    public FuturePlanInsert(int f_userid, String fcontent, String ftime) {
        this.f_userid = f_userid;
        this.fcontent = fcontent;
        this.ftime = ftime;
    }


    public int getF_userid() {
        return f_userid;
    }

    public void setF_userid(int f_userid) {
        this.f_userid = f_userid;
    }

    public String getFcontent() {
        return fcontent;
    }

    public void setFcontent(String fcontent) {
        this.fcontent = fcontent;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
    }
}
