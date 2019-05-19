package com.codvision.vsm.presenter.contract;

import com.codvision.vsm.base.BaseView;
import com.codvision.vsm.module.bean.Conclusion;
import com.codvision.vsm.module.bean.ConclusionGet;
import com.codvision.vsm.module.bean.FuturePlan;
import com.codvision.vsm.module.bean.FuturePlanDelete;

import java.util.ArrayList;

/**
 * Created by sxy on 2019/5/19 2:08
 * todo
 */
public class ConclusionGetContract {
    public interface Presenter {
        //提交
        void getConclusion(ConclusionGet conclusionGet);
    }

    public interface View extends BaseView<UserLoginContract.Presenter> {

        //成功
        void getConclusionSuccess(ArrayList<Conclusion> arrayList);

        void getConclusionFail(String code, String message);
    }
}
