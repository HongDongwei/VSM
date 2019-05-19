package com.codvision.vsm.presenter;

import android.content.Context;
import android.util.Log;

import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.ConclusionDelete;
import com.codvision.vsm.module.bean.FuturePlanDelete;
import com.codvision.vsm.module.bean.InsertId;
import com.codvision.vsm.presenter.contract.ConclusionDeleteContract;
import com.codvision.vsm.presenter.contract.FuturePlanDeleteContract;
import com.codvision.vsm.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sxy on 2019/5/9 14:48
 * todo
 */
public class ConclusionDeletePresenter implements ConclusionDeleteContract.Presenter {
    public static final String TAG = "UserSubmitPresenter";
    private ConclusionDeleteContract.View view;
    private Context context;

    public ConclusionDeletePresenter(ConclusionDeleteContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void deleteConclusion(ConclusionDelete conclusionDelete) {
        Log.i(TAG, "deleteConclusion: ConclusionDelete=" + conclusionDelete);
        RetrofitUtil.getInstance().initRetrofit().conclusionDelete(conclusionDelete)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InsertId>(context, Constant.DELETE) {
                    @Override
                    protected void onSuccees(WrapperEntity<InsertId> wrapperEntity) throws Exception {
                        if (wrapperEntity.getStatus()) {
                            view.deleteConclusionSuccess();
                        } else {
                            view.deleteConclusionFail(wrapperEntity.getCode(), wrapperEntity.getCode() + ": " + wrapperEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.deleteConclusionFail("1", "连接服务器失败，请检查网与服务器");
                    }
                });
    }

}
