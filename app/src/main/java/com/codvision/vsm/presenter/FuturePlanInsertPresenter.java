package com.codvision.vsm.presenter;

import android.content.Context;
import android.util.Log;

import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.FuturePlan;
import com.codvision.vsm.module.bean.FuturePlanGet;
import com.codvision.vsm.module.bean.FuturePlanInsert;
import com.codvision.vsm.module.bean.InsertId;
import com.codvision.vsm.presenter.contract.FuturePlanGetContract;
import com.codvision.vsm.presenter.contract.FuturePlanInsertContract;
import com.codvision.vsm.utils.RetrofitUtil;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sxy on 2019/5/9 14:48
 * todo
 */
public class FuturePlanInsertPresenter implements FuturePlanInsertContract.Presenter {
    public static final String TAG = "UserSubmitPresenter";
    private FuturePlanInsertContract.View view;
    private Context context;

    public FuturePlanInsertPresenter(FuturePlanInsertContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void insertFuturePlan(FuturePlanInsert futurePlanInsert) {
        Log.i(TAG, "FuturePlanInsert: futurePlanInsert=" + futurePlanInsert);
        RetrofitUtil.getInstance().initRetrofit().futurePlanInsert(futurePlanInsert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InsertId>(context, Constant.SUBMITING) {
                    @Override
                    protected void onSuccees(WrapperEntity<InsertId> wrapperEntity) throws Exception {
                        if (wrapperEntity.getStatus()) {
                            view.insertFuturePlanSuccess();
                        } else {
                            view.insertFuturePlanFail(wrapperEntity.getCode(), wrapperEntity.getCode() + ": " + wrapperEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.insertFuturePlanFail("1", "连接服务器失败，请检查网与服务器");
                    }
                });
    }

}
