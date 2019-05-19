package com.codvision.vsm.presenter;

import android.content.Context;
import android.util.Log;

import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.ConclusionInsert;
import com.codvision.vsm.module.bean.FuturePlanInsert;
import com.codvision.vsm.module.bean.InsertId;
import com.codvision.vsm.presenter.contract.ConclusionInsertContract;
import com.codvision.vsm.presenter.contract.FuturePlanInsertContract;
import com.codvision.vsm.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sxy on 2019/5/9 14:48
 * todo
 */
public class ConclusionInsertPresenter implements ConclusionInsertContract.Presenter {
    public static final String TAG = "UserSubmitPresenter";
    private ConclusionInsertContract.View view;
    private Context context;

    public ConclusionInsertPresenter(ConclusionInsertContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void insertConclusion(ConclusionInsert conclusionInsert){
        Log.i(TAG, "insertConclusion: ConclusionInsert=" + conclusionInsert);
        RetrofitUtil.getInstance().initRetrofit().conclusionInsert(conclusionInsert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InsertId>(context, Constant.SUBMITING) {
                    @Override
                    protected void onSuccees(WrapperEntity<InsertId> wrapperEntity) throws Exception {
                        if (wrapperEntity.getStatus()) {
                            view.insertConclusionSuccess();
                        } else {
                            view.insertConclusionFail(wrapperEntity.getCode(), wrapperEntity.getCode() + ": " + wrapperEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.insertConclusionFail("1", "连接服务器失败，请检查网与服务器");
                    }
                });
    }

}
