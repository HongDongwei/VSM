package com.codvision.vsm.module.api;

/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public class ApiAddress {

    //生成环境
//    public final static String API = "http://192.168.2.102:8767/";
    public final static String API = "http://192.168.2.103:8767/";

    /**************************************个人中心************************************************/

    //登录
    public final static String LOGIN = "v1/vsm/user/login";
    //注册
    public final static String REGISTER = "v1/vsm/user/register";
    //提交
    public final static String SUBMIT = "v1/vsm/user/info/submit";
    //插入日程
    public final static String INSERT = "v1/vsm/schedule/insert";
    //获取日程
    public final static String SELECT = "v1/vsm/schedule/select";
    //删除日程
    public final static String DELETE = "v1/vsm/schedule/delete";
}
