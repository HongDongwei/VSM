package com.codvision.vsm.module.bean;

import java.io.Serializable;

/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public class ConclusionDelete implements Serializable {

    private int id;

    public ConclusionDelete(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
