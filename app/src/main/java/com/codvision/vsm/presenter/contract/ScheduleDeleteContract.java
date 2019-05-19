package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.ScheduleDelete;

/**
 * Created by sxy on 2019/5/16 14:53
 * todo
 */
public interface ScheduleDeleteContract {
    public interface Presenter {
        //删除
        void deleteSchedule(ScheduleDelete scheduleDelete);
    }

    public interface View extends BaseView<UserLoginContract.Presenter> {

        //成功
        void deleteScheduleSuccess();

        void deleteScheduleFail(String code, String message);
    }

}
