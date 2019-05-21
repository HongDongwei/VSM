package com.codvision.vsm.module.bean;

import java.io.Serializable;

/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public class ConclusionGet implements Serializable {


    private int c_userid;

    public ConclusionGet(int c_userid) {
        this.c_userid = c_userid;
    }


    public int getC_userid() {
        return c_userid;
    }

    public void setC_userid(int c_userid) {
        this.c_userid = c_userid;
    }

}
