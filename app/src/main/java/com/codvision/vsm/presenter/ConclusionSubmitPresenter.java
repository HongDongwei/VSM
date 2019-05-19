package com.codvision.vsm.presenter;

import android.content.Context;
import android.util.Log;

import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.ConclusionSubmit;
import com.codvision.vsm.module.bean.InsertId;
import com.codvision.vsm.module.bean.ScheduleSubmit;
import com.codvision.vsm.presenter.contract.ConclusionSubmitContract;
import com.codvision.vsm.presenter.contract.ScheduleSubmitContract;
import com.codvision.vsm.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sxy on 2019/5/9 14:48
 * todo
 */
public class ConclusionSubmitPresenter implements ConclusionSubmitContract.Presenter {
    public static final String TAG = "ScheduleSubmitPresenter";
    private ConclusionSubmitContract.View view;
    private Context context;

    public ConclusionSubmitPresenter(ConclusionSubmitContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void submitConclusion(ConclusionSubmit conclusionSubmit) {
        Log.i(TAG, "submitConclusion: ConclusionSubmit=" + conclusionSubmit);
        RetrofitUtil.getInstance().initRetrofit().conclusionSubmit(conclusionSubmit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InsertId>(context, Constant.SUBMITING) {
                    @Override
                    protected void onSuccees(WrapperEntity<InsertId> wrapperEntity) throws Exception {
                        if (wrapperEntity.getStatus()) {
                            view.submitConclusionSuccess();
                        } else {
                            view.submitConclusionFail(wrapperEntity.getCode(), wrapperEntity.getCode() + ": " + wrapperEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.submitConclusionFail("1", "连接服务器失败，请检查网与服务器");
                    }
                });
    }

}
