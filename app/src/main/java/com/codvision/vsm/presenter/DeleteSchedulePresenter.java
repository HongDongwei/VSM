package com.codvision.vsm.presenter;

import android.content.Context;
import android.util.Log;

import com.codvision.vsm.base.BaseObserver;
import com.codvision.vsm.base.Constant;
import com.codvision.vsm.base.WrapperEntity;
import com.codvision.vsm.module.bean.DeleteSchedule;
import com.codvision.vsm.module.bean.GetSchedule;
import com.codvision.vsm.module.bean.Schedule;
import com.codvision.vsm.presenter.contract.DeleteScheduleContract;
import com.codvision.vsm.presenter.contract.GetScheduleContract;
import com.codvision.vsm.utils.RetrofitUtil;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by sxy on 2019/5/9 14:48
 * todo
 */
public class DeleteSchedulePresenter implements DeleteScheduleContract.Presenter {
    public static final String TAG = "InfoSubmitPresenter";
    private GetScheduleContract.View view;
    private Context context;

    public DeleteSchedulePresenter(GetScheduleContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void deleteSchedule(DeleteSchedule deleteSchedule) {
        Log.i(TAG, "submit: id=" + deleteSchedule);
        RetrofitUtil.getInstance().initRetrofit().delete(deleteSchedule)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ArrayList<Schedule>>(context, Constant.GETING) {
                    @Override
                    protected void onSuccees(WrapperEntity<ArrayList<Schedule>> wrapperEntity) throws Exception {
                        if (wrapperEntity.getStatus()) {
                            view.getScheduleSuccess(wrapperEntity.getData());
                        } else {
                            view.getScheduleFail(wrapperEntity.getCode(), wrapperEntity.getCode() + ": " + wrapperEntity.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError) throws Exception {
                        view.getScheduleFail("1", "连接服务器失败，请检查网与服务器");
                    }
                });
    }

}
