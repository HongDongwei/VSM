package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.DeleteSchedule;
import com.codvision.vsm.module.bean.GetSchedule;
import com.codvision.vsm.module.bean.Schedule;

import java.util.ArrayList;

/**
 * Created by sxy on 2019/5/16 14:53
 * todo
 */
public interface DeleteScheduleContract {
    public interface Presenter {
        //删除
        void deleteSchedule(DeleteSchedule deleteSchedule);
    }

    public interface View extends BaseView<LoginContract.Presenter> {

        //成功
        void deleteScheduleSuccess(ArrayList<Schedule> arrayList);

        void deleteScheduleFail(String code, String message);
    }

}
