package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;

/**
 * Created by sxy on 2019/5/9 22:19
 * todo
 */
public interface InfoSubmitContract {
    public interface Presenter {
        //登录
        void submit(String user, String pwd);
    }

    public interface View extends BaseView<LoginContract.Presenter> {

        //成功
        void loginSuccess();

        void loadFail(String code, String message);
    }
}