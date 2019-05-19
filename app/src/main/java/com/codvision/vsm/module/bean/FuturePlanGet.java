package com.codvision.vsm.module.bean;

import java.io.Serializable;

/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public class FuturePlanGet implements Serializable {

    private int f_userid;

    public FuturePlanGet(int f_userid) {
        this.f_userid = f_userid;
    }

    public int getF_userid() {
        return f_userid;
    }

    public void setF_userid(int f_userid) {
        this.f_userid = f_userid;
    }
}
