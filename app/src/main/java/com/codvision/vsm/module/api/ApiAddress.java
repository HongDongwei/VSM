package com.codvision.vsm.module.api;

/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public class ApiAddress {

    //生成环境
//    public final static String API = "http://192.168.2.102:8767/";
    public final static String API = "http://192.168.1.105:8767/";

    /**************************************个人中心************************************************/

    //登录
    public final static String USER_LOGIN = "v1/vsm/user/login";
    //注册
    public final static String USER_REGISTER = "v1/vsm/user/register";
    //提交
    public final static String USER_SUBMIT = "v1/vsm/user/info/submit";
    //插入日程
    public final static String SCHEDULE_INSERT = "v1/vsm/schedule/insert";
    //获取日程
    public final static String SCHEDULE_SELECT = "v1/vsm/schedule/select";
    //删除日程
    public final static String SCHEDULE_DELETE = "v1/vsm/schedule/delete";
    //修改日程
    public final static String SCHEDULE_SUBMIT = "v1/vsm/schedule/submit";
    //获取未来计划
    public final static String FUTURE_PLAN_SELECT = "v1/vsm/futureplan/select";
    //添加未来计划
    public final static String FUTURE_PLAN_INSERT = "v1/vsm/futureplan/insert";
    //删除未来计划
    public final static String FUTURE_PLAN_DELETE = "v1/vsm/futureplan/delete";
    //获取日总结
    public final static String CONCLUSION_SELECT = "v1/vsm/conclusion/select";
    //添加日总结
    public final static String CONCLUSION_INSERT = "v1/vsm/conclusion/insert";
    //删除日总结
    public final static String CONCLUSION_DELETE = "v1/vsm/conclusion/delete";
    //修改日程
    public final static String CONCLUSION_SUBMIT = "v1/vsm/conclusion/submit";
}
