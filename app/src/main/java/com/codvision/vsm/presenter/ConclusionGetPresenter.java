package com.codvision.vsm.presenter;

import android.content.Context;
import android.util.Log;

import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.Conclusion;
import com.codvision.vsm.module.bean.ConclusionGet;
import com.codvision.vsm.module.bean.FuturePlan;
import com.codvision.vsm.module.bean.FuturePlanGet;
import com.codvision.vsm.presenter.contract.ConclusionGetContract;
import com.codvision.vsm.presenter.contract.FuturePlanGetContract;
import com.codvision.vsm.utils.RetrofitUtil;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sxy on 2019/5/9 14:48
 * todo
 */
public class ConclusionGetPresenter implements ConclusionGetContract.Presenter {
    public static final String TAG = "ConclusionGetPresenter";
    private ConclusionGetContract.View view;
    private Context context;

    public ConclusionGetPresenter(ConclusionGetContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void getConclusion(ConclusionGet conclusionGet) {
        Log.i(TAG, "getConclusion: ConclusionGet=" + conclusionGet);
        RetrofitUtil.getInstance().initRetrofit().conclusionGet(conclusionGet)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ArrayList<Conclusion>>(context, Constant.GETING) {
                    @Override
                    protected void onSuccees(WrapperEntity<ArrayList<Conclusion>> wrapperEntity) throws Exception {
                        if (wrapperEntity.getStatus()) {
                            Log.i(TAG, "onSuccees: ArrayList"+wrapperEntity.getData().size());
                            view.getConclusionSuccess(wrapperEntity.getData());
                        } else {
                            view.getConclusionFail(wrapperEntity.getCode(), wrapperEntity.getCode() + ": " + wrapperEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.getConclusionFail("1", "连接服务器失败，请检查网与服务器");
                    }
                });
    }

}
