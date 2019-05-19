package com.codvision.vsm.module.api;


import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.Conclusion;
import com.codvision.vsm.module.bean.ConclusionDelete;
import com.codvision.vsm.module.bean.ConclusionGet;
import com.codvision.vsm.module.bean.ConclusionInsert;
import com.codvision.vsm.module.bean.ConclusionSubmit;
import com.codvision.vsm.module.bean.FuturePlanDelete;
import com.codvision.vsm.module.bean.FuturePlanGet;
import com.codvision.vsm.module.bean.FuturePlan;
import com.codvision.vsm.module.bean.FuturePlanInsert;
import com.codvision.vsm.module.bean.ScheduleDelete;
import com.codvision.vsm.module.bean.ScheduleGet;
import com.codvision.vsm.module.bean.ScheduleSubmit;
import com.codvision.vsm.module.bean.UserInsert;
import com.codvision.vsm.module.bean.InsertId;
import com.codvision.vsm.module.bean.UserLogin;
import com.codvision.vsm.module.bean.Schedule;
import com.codvision.vsm.module.bean.UserSubmit;

import java.util.ArrayList;
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
    @POST(ApiAddress.USER_LOGIN)
    Observable<WrapperEntity<UserLogin>> userLogin(@Body Map<String, String> maps);

    /**
     * 注册
     */
    @POST(ApiAddress.USER_REGISTER)
    Observable<WrapperEntity<InsertId>> userRegister(@Body Map<String, String> maps);

    /**
     * 提交
     */
    @POST(ApiAddress.USER_SUBMIT)
    Observable<WrapperEntity<InsertId>> userSubmit(@Body UserSubmit userSubmit);

    /**
     * 插入日程
     */
    @POST(ApiAddress.SCHEDULE_INSERT)
    Observable<WrapperEntity<InsertId>> scheduleInsert(@Body UserInsert userInsert);

    /**
     * 获取日程
     */
    @POST(ApiAddress.SCHEDULE_SELECT)
    Observable<WrapperEntity<ArrayList<Schedule>>> scheduleSelect(@Body ScheduleGet scheduleGet);

    /**
     * 删除日程
     */
    @POST(ApiAddress.SCHEDULE_DELETE)
    Observable<WrapperEntity<InsertId>> scheduleDelete(@Body ScheduleDelete scheduleDelete);

    /**
     * 修改日程
     */
    @POST(ApiAddress.SCHEDULE_SUBMIT)
    Observable<WrapperEntity<InsertId>> scheduleSubmit(@Body ScheduleSubmit scheduleSubmit);


    /**
     * 获取未来计划
     */
    @POST(ApiAddress.FUTURE_PLAN_SELECT)
    Observable<WrapperEntity<ArrayList<FuturePlan>>> futurePlanGet(@Body FuturePlanGet futurePlanGet);

    /**
     * 插入未来计划
     */
    @POST(ApiAddress.FUTURE_PLAN_INSERT)
    Observable<WrapperEntity<InsertId>> futurePlanInsert(@Body FuturePlanInsert futurePlanInsert);

    /**
     * 删除未来计划
     */
    @POST(ApiAddress.FUTURE_PLAN_DELETE)
    Observable<WrapperEntity<InsertId>> futurePlanDelete(@Body FuturePlanDelete futurePlanDelete);

    /**
     * 获取日总结
     */
    @POST(ApiAddress.CONCLUSION_SELECT)
    Observable<WrapperEntity<ArrayList<Conclusion>>> conclusionGet(@Body ConclusionGet conclusionGet);

    /**
     * 插入日总结
     */
    @POST(ApiAddress.CONCLUSION_INSERT)
    Observable<WrapperEntity<InsertId>> conclusionInsert(@Body ConclusionInsert conclusionInsert);

    /**
     * 删除日总结
     */
    @POST(ApiAddress.CONCLUSION_DELETE)
    Observable<WrapperEntity<InsertId>> conclusionDelete(@Body ConclusionDelete conclusionDelete);

    /**
     * 修改日总结
     */
    @POST(ApiAddress.CONCLUSION_SUBMIT)
    Observable<WrapperEntity<InsertId>> conclusionSubmit(@Body ConclusionSubmit conclusionSubmit);
}
