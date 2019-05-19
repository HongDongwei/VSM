package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.ScheduleGet;
import com.codvision.vsm.module.bean.Schedule;

import java.util.ArrayList;

/**
 * Created by sxy on 2019/5/11 21:36
 * todo
 */
public interface ScheduleGetContract {
    public interface Presenter {
        //提交
        void getSchedule(ScheduleGet scheduleGet);
    }

    public interface View extends BaseView<UserLoginContract.Presenter> {

        //成功
        void getScheduleSuccess(ArrayList<Schedule> arrayList);

        void getScheduleFail(String code, String message);
    }
}
