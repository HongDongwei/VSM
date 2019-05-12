package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.GetSchedule;
import com.codvision.vsm.module.bean.Schedule;
import com.codvision.vsm.module.bean.User;

import java.util.ArrayList;

/**
 * Created by sxy on 2019/5/11 21:36
 * todo
 */
public interface GetScheduleContract {
    public interface Presenter {
        //提交
        void getSchedule(GetSchedule getSchedule);
    }

    public interface View extends BaseView<LoginContract.Presenter> {

        //成功
        void getScheduleSuccess(ArrayList<Schedule> arrayList);

        void getScheduleFail(String code, String message);
    }
}
