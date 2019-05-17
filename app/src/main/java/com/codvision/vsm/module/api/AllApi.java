package com.codvision.vsm.module.api;


import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.DeleteSchedule;
import com.codvision.vsm.module.bean.GetSchedule;
import com.codvision.vsm.module.bean.Insert;
import com.codvision.vsm.module.bean.InsertId;
import com.codvision.vsm.module.bean.Login;
import com.codvision.vsm.module.bean.Schedule;
import com.codvision.vsm.module.bean.Submit;
import com.codvision.vsm.module.bean.User;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by sxy on 2019/5/8 13:48
 * todo
 */
public interface AllApi {

    /**
     * 登录
     */
    @POST(ApiAddress.LOGIN)
    Observable<WrapperEntity<Login>> login(@Body Map<String, String> maps);

    /**
     * 注册
     */
    @POST(ApiAddress.REGISTER)
    Observable<WrapperEntity<InsertId>> register(@Body Map<String, String> maps);

    /**
     * 提交
     */
    @POST(ApiAddress.SUBMIT)
    Observable<WrapperEntity<InsertId>> submit(@Body Submit submit);
    /**
     * 插入日程
     */
    @POST(ApiAddress.INSERT)
    Observable<WrapperEntity<InsertId>> insert(@Body Insert insert);
    /**
     * 获取日程
     */
    @POST(ApiAddress.SELECT)
    Observable<WrapperEntity<ArrayList<Schedule>>> select(@Body GetSchedule getSchedule);
    /**
     * 删除日程
     */
    @POST(ApiAddress.DELETE)
    Observable<WrapperEntity<ArrayList<Schedule>>> delete(@Body DeleteSchedule deleteSchedule);

}
