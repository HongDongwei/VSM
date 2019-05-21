package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.FuturePlan;
import com.codvision.vsm.module.bean.FuturePlanGet;

import java.util.ArrayList;

/**
 * Created by sxy on 2019/5/19 2:08
 * todo
 */
public interface FuturePlanGetContract {
    public interface Presenter {
        //提交
        void getFuturePlan(FuturePlanGet futureplanGet);
    }

    public interface View extends BaseView<UserLoginContract.Presenter> {

        //成功
        void getFuturePlanSuccess(ArrayList<FuturePlan> arrayList);

        void getFuturePlanFail(String code, String message);
    }
}
