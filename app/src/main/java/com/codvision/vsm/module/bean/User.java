package com.codvision.vsm.module.bean;

import android.content.SharedPreferences;

import java.io.Serializable;

/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public class User implements Serializable {

    private int id;

    private String username;

    private String password;

    private String email;

    private String image;

    private int gender;

    private String intro;

    private boolean state;

    public void putInSP(SharedPreferences.Editor editor) {
        editor.putInt("id", id);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("email", email);
        editor.putString("image", image);
        editor.putInt("gender", gender);
        editor.putString("intro", intro);
        editor.putBoolean("state", state);
    }

    public User(UserLogin userLogin, String pwd, boolean state) {
        this.id = userLogin.getId();
        this.username = userLogin.getUsername();
        this.password = pwd;
        this.email = userLogin.getEmail();
        this.image = userLogin.getImage();
        this.gender = userLogin.getGender();
        this.intro = userLogin.getIntro();
        this.state = state;
    }

    public User(int id, String username, String password, String email, String image, int gender, String intro) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.image = image;
        this.gender = gender;
        this.intro = intro;
    }

    public User(SharedPreferences preferences) {
        id = preferences.getInt("id", 0);
        username = preferences.getString("username", "");
        password = preferences.getString("password", "");
        email = preferences.getString("email", "您还没有邮箱");
        image = preferences.getString("image", "您还没有头像");
        gender = preferences.getInt("gender", 0);
        intro = preferences.getString("intro", "您还没有签名");
        state = preferences.getBoolean("state", false);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
