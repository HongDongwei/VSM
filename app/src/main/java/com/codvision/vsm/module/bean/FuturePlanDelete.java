package com.codvision.vsm.module.bean;

import java.io.Serializable;

/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public class FuturePlanDelete implements Serializable {

    private int fid;

    public FuturePlanDelete(int fid) {
        this.fid = fid;
    }


    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }
}
