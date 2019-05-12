package com.codvision.vsm.presenter;

import android.content.Context;
import android.util.Log;


import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.Login;
import com.codvision.vsm.module.bean.User;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.utils.RetrofitUtil;
import com.codvision.vsm.presenter.contract.LoginContract;
import com.codvision.vsm.utils.SharedPreferenceUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sxy on 2019/5/9 14:48
 * todo
 */
public class LoginPresenter implements LoginContract.Presenter {
    public static final String TAG = "LoginPresenter";
    private LoginContract.View view;
    private Context context;

    public LoginPresenter(LoginContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void login(String name, final String pwd) {
        Log.i(TAG, "login: name=" + name + "pwd=" + pwd);
        Map<String, String> map = new HashMap<>();
        map.put("username", name);
        map.put("password", pwd);
        Log.i(TAG, "register: " + map);
        RetrofitUtil.getInstance().initRetrofit().login(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Login>(context, Constant.LOGINING) {
                    @Override
                    protected void onSuccees(WrapperEntity<Login> wrapperEntity) throws Exception {
                        if (wrapperEntity.getStatus()) {
                            view.loginSuccess();
                            User user = new User(wrapperEntity.getData(), pwd, true);
                            Log.i(TAG, "onSuccees: name.pwad=" + user.getPassword());
                            SharedPreferenceUtils.putSelfInfo(context, user);
                        } else {
                            view.loginFail(wrapperEntity.getCode(), wrapperEntity.getCode() + ": " + wrapperEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.loginFail("1", "连接服务器失败，请检查网与服务器");
                    }
                });
    }

}
