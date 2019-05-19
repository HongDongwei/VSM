package com.codvision.vsm.presenter;

import android.content.Context;
import android.util.Log;

import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.UserInsert;
import com.codvision.vsm.module.bean.InsertId;
import com.codvision.vsm.presenter.contract.ScheduleInsertContract;
import com.codvision.vsm.utils.RetrofitUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sxy on 2019/5/9 14:48
 * todo
 */
public class ScheduleInsertPresenter implements ScheduleInsertContract.Presenter {
    public static final String TAG = "UserLoginPresenter";
    private ScheduleInsertContract.View view;
    private Context context;

    public ScheduleInsertPresenter(ScheduleInsertContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void insert(UserInsert userInsert) {
        Log.i(TAG, "insert: userInsert=" + userInsert);
        RetrofitUtil.getInstance().initRetrofit().scheduleInsert(userInsert)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InsertId>(context, Constant.SUBMITING) {
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
