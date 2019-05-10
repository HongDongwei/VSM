package com.codvision.vsm.module.api;


import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.Login;
import com.codvision.vsm.module.bean.User;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
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
    Observable<WrapperEntity<User>> register(@Body Map<String, String> maps);
}
