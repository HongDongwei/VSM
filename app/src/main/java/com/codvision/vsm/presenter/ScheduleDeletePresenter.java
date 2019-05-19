package com.codvision.vsm.presenter;

import android.content.Context;
import android.util.Log;

import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.InsertId;
import com.codvision.vsm.module.bean.ScheduleDelete;
import com.codvision.vsm.module.bean.Schedule;
import com.codvision.vsm.presenter.contract.ScheduleDeleteContract;
import com.codvision.vsm.utils.RetrofitUtil;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sxy on 2019/5/9 14:48
 * todo
 */
public class ScheduleDeletePresenter implements ScheduleDeleteContract.Presenter {
    public static final String TAG = "UserSubmitPresenter";
    private ScheduleDeleteContract.View view;
    private Context context;

    public ScheduleDeletePresenter(ScheduleDeleteContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void deleteSchedule(ScheduleDelete scheduleDelete) {
        Log.i(TAG, "deleteSchedule: scheduleDelete=" + scheduleDelete);
        RetrofitUtil.getInstance().initRetrofit().scheduleDelete(scheduleDelete)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<InsertId>(context, Constant.DELETE) {
                    @Override
                    protected void onSuccees(WrapperEntity<InsertId> wrapperEntity) throws Exception {
                        if (wrapperEntity.getStatus()) {
                            view.deleteScheduleSuccess();
                        } else {
                            view.deleteScheduleFail(wrapperEntity.getCode(), wrapperEntity.getCode() + ": " + wrapperEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.deleteScheduleFail("1", "连接服务器失败，请检查网与服务器");
                    }
                });
    }

}
