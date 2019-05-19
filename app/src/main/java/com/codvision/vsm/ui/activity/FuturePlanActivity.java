package com.codvision.vsm.ui.activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.codvision.vsm.R;
import com.codvision.vsm.module.bean.FuturePlan;
import com.codvision.vsm.module.bean.FuturePlanDelete;
import com.codvision.vsm.module.bean.FuturePlanGet;
import com.codvision.vsm.module.bean.FuturePlanInsert;
import com.codvision.vsm.presenter.FuturePlanDeletePresenter;
import com.codvision.vsm.presenter.FuturePlanGetPresenter;
import com.codvision.vsm.presenter.FuturePlanInsertPresenter;
import com.codvision.vsm.presenter.contract.FuturePlanDeleteContract;
import com.codvision.vsm.presenter.contract.FuturePlanGetContract;
import com.codvision.vsm.presenter.contract.FuturePlanInsertContract;
import com.codvision.vsm.ui.adapter.FuturePlanAdapter;
import com.codvision.vsm.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class FuturePlanActivity extends AppCompatActivity implements FuturePlanGetContract.View, View.OnClickListener, FuturePlanInsertContract.View, FuturePlanDeleteContract.View {
    /**
     * TAG
     */
    public static final String TAG = "FuturePlanActivity";
    private FuturePlanGetPresenter futurePlanGetPresenter;
    private FuturePlanInsertPresenter futurePlanInsertPresenter;
    private FuturePlanDeletePresenter futurePlanDeletePresenter;
    private ListView lvFuterePlan;
    private ImageButton ibBack;
    private RelativeLayout rlAdd;

    private FuturePlanAdapter futurePlanAdapter;
    private List<FuturePlan> futurePlanList = new ArrayList<FuturePlan>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_plan);
        initView();
        initEvent();
    }

    private void initView() {
        lvFuterePlan = findViewById(R.id.lv_future_plan);
        ibBack = findViewById(R.id.ib_future_plan_finish);
        rlAdd = findViewById(R.id.rl_future_plan_add);
        futurePlanAdapter = new FuturePlanAdapter(this, R.layout.item_futureplan, futurePlanList);
        lvFuterePlan.setAdapter(futurePlanAdapter);
        futurePlanGetPresenter = new FuturePlanGetPresenter(this, this);
        futurePlanInsertPresenter = new FuturePlanInsertPresenter(this, this);
        futurePlanDeletePresenter = new FuturePlanDeletePresenter(this, this);
        futurePlanGetPresenter.getFuturePlan(new FuturePlanGet(SharedPreferenceUtils.getUserId(this)));
    }

    private void initEvent() {
        ibBack.setOnClickListener(this);
        rlAdd.setOnClickListener(this);
        lvFuterePlan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
                new AlertDialog.Builder(FuturePlanActivity.this);
        normalDialog.setTitle("温馨提示：");
        normalDialog.setMessage("请问是否删除该未来计划?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        futurePlanDeletePresenter.deleteFuturePlan(new FuturePlanDelete(futurePlanList.get(position).getFid()));
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_future_plan_finish:
                finish();
                break;
            case R.id.rl_future_plan_add:
                showAddPlanDialog();
                break;
            default:
                break;
        }
    }

    private void showAddPlanDialog() {
        /* @setView 装入自定义View ==> R.layout.dialog_customize
         * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
         * dialog_customize.xml可自定义更复杂的View
         */
        AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(FuturePlanActivity.this);
        final View dialogView = LayoutInflater.from(FuturePlanActivity.this)
                .inflate(R.layout.dialog_future_plan, null);
        customizeDialog.setTitle("添加未来计划");
        customizeDialog.setView(dialogView);
        customizeDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 获取EditView中的输入内容
                        EditText etContent =
                                (EditText) dialogView.findViewById(R.id.et_future_plan_content);
                        EditText etTime =
                                (EditText) dialogView.findViewById(R.id.et_future_plan_timr);
                        futurePlanInsertPresenter.insertFuturePlan(new FuturePlanInsert(SharedPreferenceUtils.getUserId(FuturePlanActivity.this), etContent.getText().toString().trim(), etTime.getText().toString().trim()));
                    }
                });
        customizeDialog.show();
    }

    @Override
    public void getFuturePlanSuccess(ArrayList<FuturePlan> arrayList) {
        Log.i(TAG, "getFuturePlanSuccess: 成功了,arrayList.size():" + arrayList.size());
        futurePlanList.clear();
        for (int i = 0; i < arrayList.size(); i++) {
            FuturePlan futurePlan = arrayList.get(i);
            futurePlanList.add(futurePlan);
        }
        futurePlanAdapter.notifyDataSetChanged();
    }

    @Override
    public void getFuturePlanFail(String code, String message) {

    }

    @Override
    public void insertFuturePlanSuccess() {
        futurePlanGetPresenter.getFuturePlan(new FuturePlanGet(SharedPreferenceUtils.getUserId(this)));
    }

    @Override
    public void insertFuturePlanFail(String code, String message) {

    }

    @Override
    public void deleteFuturePlanSuccess() {
        futurePlanGetPresenter.getFuturePlan(new FuturePlanGet(SharedPreferenceUtils.getUserId(this)));
    }

    @Override
    public void deleteFuturePlanFail(String code, String message) {

    }
}
