package com.codvision.vsm.presenter;

import android.content.Context;
import android.util.Log;

import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.Insert;
import com.codvision.vsm.module.bean.InsertId;
import com.codvision.vsm.module.bean.Login;
import com.codvision.vsm.module.bean.Schedule;
import com.codvision.vsm.module.bean.User;
import com.codvision.vsm.presenter.contract.InsertContract;
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
public class InsertPresenter implements InsertContract.Presenter {
    public static final String TAG = "LoginPresenter";
    private InsertContract.View view;
    private Context context;

    public InsertPresenter(InsertContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void insert(Insert insert) {
        Log.i(TAG, "insert: insert=" + insert);
        RetrofitUtil.getInstance().initRetrofit().insert(insert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InsertId>(context, Constant.LOGINING) {
                    @Override
                    protected void onSuccees(WrapperEntity<InsertId> wrapperEntity) throws Exception {
                        if (wrapperEntity.getStatus()) {
                            view.insertSuccess();
                        } else {
                            view.insertFail(wrapperEntity.getCode(), wrapperEntity.getCode() + ": " + wrapperEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.insertFail("1", "连接服务器失败，请检查网与服务器");
                    }
                });
    }

}