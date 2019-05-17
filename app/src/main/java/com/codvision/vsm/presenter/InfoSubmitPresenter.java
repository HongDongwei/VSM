package com.codvision.vsm.presenter;

import android.content.Context;
import android.util.Log;

import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.InsertId;
import com.codvision.vsm.module.bean.Login;
import com.codvision.vsm.module.bean.Submit;
import com.codvision.vsm.module.bean.User;
import com.codvision.vsm.presenter.contract.InfoSubmitContract;
import com.codvision.vsm.presenter.contract.LoginContract;
import com.codvision.vsm.utils.RetrofitUtil;
import com.codvision.vsm.utils.SharedPreferenceUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sxy on 2019/5/9 14:48
 * todo
 */
public class InfoSubmitPresenter implements InfoSubmitContract.Presenter {
    public static final String TAG = "InfoSubmitPresenter";
    private InfoSubmitContract.View view;
    private Context context;

    public InfoSubmitPresenter(InfoSubmitContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void submit(User user) {
        Log.i(TAG, "submit: user=" + user);
        RetrofitUtil.getInstance().initRetrofit().submit(new Submit(user))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InsertId>(context, Constant.SUBMITING) {
                    @Override
                    protected void onSuccees(WrapperEntity<InsertId> wrapperEntity) throws Exception {
                        if (wrapperEntity.getStatus()) {
                            view.submitSuccess();
                        } else {
                            view.submitFail(wrapperEntity.getCode(), wrapperEntity.getCode() + ": " + wrapperEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.submitFail("1", "连接服务器失败，请检查网与服务器");
                    }
                });
    }

}
