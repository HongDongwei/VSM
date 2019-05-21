package com.codvision.vsm.ui.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.codvision.vsm.R;
import com.codvision.vsm.module.bean.Conclusion;
import com.codvision.vsm.module.bean.ConclusionDelete;
import com.codvision.vsm.module.bean.ConclusionGet;
import com.codvision.vsm.module.bean.FuturePlan;
import com.codvision.vsm.module.bean.FuturePlanDelete;
import com.codvision.vsm.presenter.ConclusionDeletePresenter;
import com.codvision.vsm.presenter.ConclusionGetPresenter;
import com.codvision.vsm.presenter.contract.ConclusionDeleteContract;
import com.codvision.vsm.presenter.contract.ConclusionGetContract;
import com.codvision.vsm.ui.adapter.ConclutionAdapter;
import com.codvision.vsm.ui.adapter.FuturePlanAdapter;
import com.codvision.vsm.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class ConclutionActivity extends AppCompatActivity implements ConclusionGetContract.View, ConclusionDeleteContract.View {

    private ConclusionGetPresenter conclusionGetPresenter;
    private ConclusionDeletePresenter conclusionDeletePresenter;
    private ListView lvPlan;
    private ConclutionAdapter conclutionAdapter;
    private List<Conclusion> conclusionList = new ArrayList<Conclusion>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conclution);
        lvPlan = findViewById(R.id.lv_plan);
        conclutionAdapter = new ConclutionAdapter(this, R.layout.item_conclution, conclusionList);
        lvPlan.setAdapter(conclutionAdapter);
        conclusionGetPresenter = new ConclusionGetPresenter(ConclutionActivity.this, this);
        conclusionDeletePresenter = new ConclusionDeletePresenter(this, this);
        conclusionGetPresenter.getConclusion(new ConclusionGet(SharedPreferenceUtils.getUserId(this)));

        lvPlan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showNormalDialog(position);
                return false;
            }
        });
    }

    private void showNormalDialog(final int position) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(ConclutionActivity.this);
        normalDialog.setTitle("温馨提示：");
        normalDialog.setMessage("请问是否删除该日总结?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        conclusionDeletePresenter.deleteConclusion(new ConclusionDelete(conclusionList.get(position).getId()));
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }

    @Override
    public void getConclusionSuccess(ArrayList<Conclusion> arrayList) {
        conclusionList.clear();
        for (int i = 0; i < arrayList.size(); i++) {
            conclusionList.add(arrayList.get(i));
        }
        conclutionAdapter.notifyDataSetChanged();
    }

    @Override
    public void getConclusionFail(String code, String message) {

    }

    @Override
    public void deleteConclusionSuccess() {
        conclusionGetPresenter.getConclusion(new ConclusionGet(SharedPreferenceUtils.getUserId(this)));
    }

    @Override
    public void deleteConclusionFail(String code, String message) {

    }
}
