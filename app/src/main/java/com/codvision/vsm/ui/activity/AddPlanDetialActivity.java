package com.codvision.vsm.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codvision.vsm.App;
import com.codvision.vsm.R;
import com.codvision.vsm.module.bean.Insert;
import com.codvision.vsm.presenter.InsertPresenter;
import com.codvision.vsm.presenter.contract.InsertContract;
import com.codvision.vsm.utils.SharedPreferenceUtils;
import com.codvision.vsm.utils.datepicker.CustomDatePicker;
import com.codvision.vsm.utils.datepicker.DateFormatUtils;

import java.util.Calendar;

import static com.codvision.vsm.utils.DayUtils.getDay;
import static com.codvision.vsm.utils.DayUtils.getTime;

public class AddPlanDetialActivity extends AppCompatActivity implements View.OnClickListener, InsertContract.View {

    /**
     * TAG
     */
    public static final String TAG = "AddPlanDetialActivity";
    private int year = 0;
    private int month = 0;
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int clickItem;
    private TextView tvAlert1;
    private TextView tvAlert2;
    private TextView tvAlert3;
    private TextView tvAlert4;

    private TextView tvTpye;
    private ImageView ivTypr;
    private TextView tvStartTime;
    private TextView tvEndTime;
    private EditText etTitle;
    private EditText etCycle;
    private CustomDatePicker mTimerStartPicker;
    private CustomDatePicker mTimerEndPicker;
    private InsertPresenter insertPresenter;

    private Button btSave;
    private Insert insert;
    private String startDay;
    private String endDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan_detial);
        initView();
        initEvent();
    }


    private void initView() {
        tvTpye = findViewById(R.id.tv_type);
        ivTypr = findViewById(R.id.iv_type);
        tvStartTime = findViewById(R.id.tv_add_start_time);
        tvEndTime = findViewById(R.id.tv_add_end_time);
        tvAlert1 = findViewById(R.id.tv_alert1);
        tvAlert2 = findViewById(R.id.tv_alert2);
        tvAlert3 = findViewById(R.id.tv_alert3);
        tvAlert4 = findViewById(R.id.tv_alert4);
        etTitle = findViewById(R.id.et_plan_title);
        etCycle = findViewById(R.id.et_plan_cycle);
        btSave = findViewById(R.id.bt_plan_save);
        insertPresenter = new InsertPresenter(this, this);
        initTimerStartPicker();
        initTimerEndPicker();
        initDateTime();


        Intent intent = getIntent();
        String planType = intent.getStringExtra("plan_type");
        setType(planType);
        setClick(tvAlert2, 2);
    }

    private void initEvent() {
        tvStartTime.setOnClickListener(this);
        tvEndTime.setOnClickListener(this);
        btSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_add_start_time:
                mTimerStartPicker.show(tvStartTime.getText().toString());
                break;
            case R.id.tv_add_end_time:
                mTimerEndPicker.show(tvEndTime.getText().toString());
                break;
            case R.id.bt_plan_save:
                if (TextUtils.isEmpty(etTitle.getText())) {
                    etTitle.setText("标题不能为空");
                } else {
                    if (TextUtils.isEmpty(etCycle.getText())) {
                        etCycle.setText("周期不能为空");
                    } else {
                        insert = new Insert(getDay(startDay), getTime(startDay), etTitle.getText().toString().trim(), etCycle.getText().toString().trim(), clickItem, "0", 0, "0", 0, getDay(startDay), getDay(endDay), SharedPreferenceUtils.getUserId(this), 1);
                        insertPresenter.insert(insert);
                    }
                }

                break;
            default:
                break;
        }
    }

    private void initTimerStartPicker() {
        //        Calendar calendar = Calendar.getInstance();
        //        String beginTime = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        String beginTime = "2018-10-17 18:00";
        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), true);
        tvStartTime.setText(endTime);
        startDay = endTime;
        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerStartPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                tvStartTime.setText(DateFormatUtils.long2Str(timestamp, true));
                startDay = DateFormatUtils.long2Str(timestamp, true);
            }

            @Override
            public void onNullSelected() {

            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerStartPicker.setCancelable(true);
        // 显示时和分
        mTimerStartPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerStartPicker.setScrollLoop(true);
        // 允许滚动动画
        mTimerStartPicker.setCanShowAnim(true);
    }

    private void initTimerEndPicker() {
        //        Calendar calendar = Calendar.getInstance();
        //        String beginTime = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        String beginTime = "2018-10-17 18:00";
        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), true);

        tvEndTime.setText(endTime);
        endDay = endTime;
        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerEndPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                tvEndTime.setText(DateFormatUtils.long2Str(timestamp, true));
                endDay = DateFormatUtils.long2Str(timestamp, true);
            }

            @Override
            public void onNullSelected() {

            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerEndPicker.setCancelable(true);
        // 显示时和分
        mTimerEndPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerEndPicker.setScrollLoop(true);
        // 允许滚动动画
        mTimerEndPicker.setCanShowAnim(true);
    }

    private void initDateTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    private void setClick(TextView textView, int i) {
        clickItem = i;
        tvAlert1.setSelected(false);
        tvAlert2.setSelected(false);
        tvAlert3.setSelected(false);
        tvAlert4.setSelected(false);
        textView.setSelected(true);
    }

    private void setType(String type) {
        switch (type) {
            case "1":
                tvTpye.setText("看书");
                ivTypr.setBackgroundResource(R.drawable.book);
                break;
            case "2":
                tvTpye.setText("锻炼");
                ivTypr.setBackgroundResource(R.drawable.building);
                break;
            case "3":
                tvTpye.setText("看病");
                ivTypr.setBackgroundResource(R.drawable.doctor);
                break;
            case "4":
                tvTpye.setText("学习");
                ivTypr.setBackgroundResource(R.drawable.eg_study);
                break;
            case "5":
                tvTpye.setText("绘画");
                ivTypr.setBackgroundResource(R.drawable.painting);
                break;
            case "6":
                tvTpye.setText("跑步");
                ivTypr.setBackgroundResource(R.drawable.run);
                break;
            case "7":
                tvTpye.setText("喝水");
                ivTypr.setBackgroundResource(R.drawable.water);
                break;
            case "8":
                tvTpye.setText("无");
                ivTypr.setBackgroundResource(R.drawable.add_item);
                break;
            default:
                break;


        }

    }


    @Override
    public void insertSuccess() {
        App.hide();
        finish();
    }

    @Override
    public void insertFail(String code, String message) {
        Log.i(TAG, "insertFail: message=" + message);
    }
}
