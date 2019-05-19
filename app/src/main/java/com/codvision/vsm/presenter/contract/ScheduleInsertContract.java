package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.UserInsert;

/**
 * Created by sxy on 2019/5/11 15:45
 * todo
 */
public interface ScheduleInsertContract {
    public interface Presenter {
        //插入
        void insert(UserInsert userInsert);
    }

    public interface View extends BaseView<UserLoginContract.Presenter> {

        //成功
        void insertSuccess();

        void insertFail(String code, String message);
    }
}
