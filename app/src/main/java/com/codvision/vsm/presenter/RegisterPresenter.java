package com.codvision.vsm.presenter;

import android.content.Context;

import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.User;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.utils.RetrofitUtil;
import com.codvision.vsm.presenter.contract.RegisterContract;
import com.codvision.vsm.utils.SharedPreferenceUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sxy on 2019/5/9 14:48
 * todo
 */
public class RegisterPresenter implements RegisterContract.Presenter {
    public static final String TAG = "LoginPresenter";
    private RegisterContract.View view;
    private Context context;

    public RegisterPresenter(RegisterContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }


    @Override
    public void register(String user, String pwd) {
        Map<String, String> map = new HashMap<>();
        map.put("username", user);
        map.put("password", pwd);
        RetrofitUtil.getInstance().initRetrofit().register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<User>(context, Constant.REGISTERING) {
                    @Override
                    protected void onSuccees(WrapperEntity<User> wrapperEntity) throws Exception {
                        if (wrapperEntity.getStatus()) {
                            view.registerSuccess();
                            User user = wrapperEntity.getData();
                            SharedPreferenceUtils.putSelfInfo(context, user);
                        } else {
                            view.registerFail(wrapperEntity.getCode(), wrapperEntity.getCode() + ": " + wrapperEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.registerFail("1", "连接服务器失败，请检查网与服务器");
                    }
                });
    }
}
