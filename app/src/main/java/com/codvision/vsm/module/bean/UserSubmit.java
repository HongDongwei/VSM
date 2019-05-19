package com.codvision.vsm.module.bean;

import java.io.Serializable;

/**
 * Created by sxy on 2019/5/10 13:50
 * todo
 */
public class UserSubmit implements Serializable {
    private int id;

    private String password;

    private String email;

    private String image;

    private int gender;

    private String intro;

    public UserSubmit(int id, String password, String email, String image, int gender, String intro) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.image = image;
        this.gender = gender;
        this.intro = intro;
    }

    public UserSubmit(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.image = user.getImage();
        this.gender = user.getGender();
        this.intro = user.getIntro();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
