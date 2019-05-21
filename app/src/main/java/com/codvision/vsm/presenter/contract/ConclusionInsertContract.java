package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.Conclusion;
import com.codvision.vsm.module.bean.ConclusionGet;
import com.codvision.vsm.module.bean.ConclusionInsert;

import java.util.ArrayList;

/**
 * Created by sxy on 2019/5/19 2:08
 * todo
 */
public interface ConclusionInsertContract {
    public interface Presenter {
        //提交
        void insertConclusion(ConclusionInsert conclusionInsert);
    }

    public interface View extends BaseView<UserLoginContract.Presenter> {

        //成功
        void insertConclusionSuccess();

        void insertConclusionFail(String code, String message);
    }
}
