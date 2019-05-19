package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.FuturePlan;
import com.codvision.vsm.module.bean.FuturePlanGet;
import com.codvision.vsm.module.bean.FuturePlanInsert;

import java.util.ArrayList;

/**
 * Created by sxy on 2019/5/19 2:08
 * todo
 */
public class FuturePlanInsertContract {
    public interface Presenter {
        //提交
        void insertFuturePlan(FuturePlanInsert futurePlanInsert);
    }

    public interface View extends BaseView<UserLoginContract.Presenter> {

        //成功
        void insertFuturePlanSuccess();

        void insertFuturePlanFail(String code, String message);
    }
}
