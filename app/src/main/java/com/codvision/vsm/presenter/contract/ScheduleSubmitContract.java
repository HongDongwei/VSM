package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.ScheduleSubmit;

/**
 * Created by sxy on 2019/5/16 14:53
 * todo
 */
public interface ScheduleSubmitContract {
    public interface Presenter {
        //删除
        void submitSchedule(ScheduleSubmit scheduleSubmit);
    }

    public interface View extends BaseView<UserLoginContract.Presenter> {

        //成功
        void submitScheduleSuccess();

        void submitScheduleFail(String code, String message);
    }

}
