package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.User;

/**
 * Created by sxy on 2019/5/9 22:19
 * todo
 */
public interface InfoSubmitContract {
    public interface Presenter {
        //提交
        void submit(User user);
    }

    public interface View extends BaseView<LoginContract.Presenter> {

        //成功
        void submitSuccess();

        void submitFail(String code, String message);
    }
}