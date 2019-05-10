package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.presenter.SuperPresenter;

/**
 * Created by sxy on 2019/5/9 14:38
 * todo
 */
public interface LoginContract {

    public interface Presenter {
        //登录
        void login(String user, String pwd);
    }

    public interface View extends BaseView<Presenter> {

        //成功
        void loginSuccess();

        void loadFail(String code, String message);
    }
}
