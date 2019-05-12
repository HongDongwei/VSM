package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.Insert;
import com.codvision.vsm.module.bean.Schedule;
import com.codvision.vsm.module.bean.User;

/**
 * Created by sxy on 2019/5/11 15:45
 * todo
 */
public interface InsertContract {
    public interface Presenter {
        //插入
        void insert(Insert insert);
    }

    public interface View extends BaseView<LoginContract.Presenter> {

        //成功
        void insertSuccess();

        void insertFail(String code, String message);
    }
}
