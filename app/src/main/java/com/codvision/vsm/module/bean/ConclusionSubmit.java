package com.codvision.vsm.module.bean;

import java.io.Serializable;

/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public class ConclusionSubmit implements Serializable {

    private int id;

    private String content;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
