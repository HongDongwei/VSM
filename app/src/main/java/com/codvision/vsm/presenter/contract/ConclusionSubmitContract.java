package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.ConclusionInsert;
import com.codvision.vsm.module.bean.ConclusionSubmit;

/**
 * Created by sxy on 2019/5/19 2:08
 * todo
 */
public class ConclusionSubmitContract {
    public interface Presenter {
        //提交
        void submitConclusion(ConclusionSubmit conclusionSubmit);
    }

    public interface View extends BaseView<UserLoginContract.Presenter> {

        //成功
        void submitConclusionSuccess();

        void submitConclusionFail(String code, String message);
    }
}
