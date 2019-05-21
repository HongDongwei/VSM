package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.ConclusionDelete;
import com.codvision.vsm.module.bean.FuturePlanDelete;

/**
 * Created by sxy on 2019/5/19 2:08
 * todo
 */
public interface ConclusionDeleteContract {
    public interface Presenter {
        //提交
        void deleteConclusion(ConclusionDelete conclusionDelete);
    }

    public interface View extends BaseView<UserLoginContract.Presenter> {

        //成功
        void deleteConclusionSuccess();

        void deleteConclusionFail(String code, String message);
    }
}
