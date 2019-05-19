package com.codvision.vsm.presenter;

import android.content.Context;
import android.util.Log;

import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.FuturePlan;
import com.codvision.vsm.module.bean.FuturePlanGet;
import com.codvision.vsm.module.bean.Schedule;
import com.codvision.vsm.presenter.contract.FuturePlanGetContract;
import com.codvision.vsm.utils.RetrofitUtil;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sxy on 2019/5/9 14:48
 * todo
 */
public class FuturePlanGetPresenter implements FuturePlanGetContract.Presenter {
    public static final String TAG = "UserSubmitPresenter";
    private FuturePlanGetContract.View view;
    private Context context;

    public FuturePlanGetPresenter(FuturePlanGetContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getFuturePlan(FuturePlanGet futureplanGet) {
        Log.i(TAG, "getFuturePlan: futureplanGet=" + futureplanGet);
        RetrofitUtil.getInstance().initRetrofit().futurePlanGet(futureplanGet)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ArrayList<FuturePlan>>(context, Constant.GETING) {
                    @Override
                    protected void onSuccees(WrapperEntity<ArrayList<FuturePlan>> wrapperEntity) throws Exception {
                        if (wrapperEntity.getStatus()) {
                            view.getFuturePlanSuccess(wrapperEntity.getData());
                        } else {
                            view.getFuturePlanFail(wrapperEntity.getCode(), wrapperEntity.getCode() + ": " + wrapperEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.getFuturePlanFail("1", "连接服务器失败，请检查网与服务器");
                    }
                });
    }

}
