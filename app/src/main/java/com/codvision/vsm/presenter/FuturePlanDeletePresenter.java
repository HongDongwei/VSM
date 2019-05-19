package com.codvision.vsm.presenter;

import android.content.Context;
import android.util.Log;

import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.FuturePlan;
import com.codvision.vsm.module.bean.FuturePlanDelete;
import com.codvision.vsm.module.bean.FuturePlanGet;
import com.codvision.vsm.module.bean.InsertId;
import com.codvision.vsm.presenter.contract.FuturePlanDeleteContract;
import com.codvision.vsm.presenter.contract.FuturePlanGetContract;
import com.codvision.vsm.utils.RetrofitUtil;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sxy on 2019/5/9 14:48
 * todo
 */
public class FuturePlanDeletePresenter implements FuturePlanDeleteContract.Presenter {
    public static final String TAG = "UserSubmitPresenter";
    private FuturePlanDeleteContract.View view;
    private Context context;

    public FuturePlanDeletePresenter(FuturePlanDeleteContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void deleteFuturePlan(FuturePlanDelete futurePlanDelete) {
        Log.i(TAG, "deleteFuturePlan: FuturePlanDelete=" + futurePlanDelete);
        RetrofitUtil.getInstance().initRetrofit().futurePlanDelete(futurePlanDelete)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InsertId>(context, Constant.DELETE) {
                    @Override
                    protected void onSuccees(WrapperEntity<InsertId> wrapperEntity) throws Exception {
                        if (wrapperEntity.getStatus()) {
                            view.deleteFuturePlanSuccess();
                        } else {
                            view.deleteFuturePlanFail(wrapperEntity.getCode(), wrapperEntity.getCode() + ": " + wrapperEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.deleteFuturePlanFail("1", "连接服务器失败，请检查网与服务器");
                    }
                });
    }

}
