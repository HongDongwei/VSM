package com.codvision.vsm.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.codvision.vsm.R;
import com.codvision.vsm.utils.FindCommand;
import com.codvision.vsm.utils.datepicker.CustomDatePicker;
import com.codvision.vsm.utils.datepicker.DateFormatUtils;

import java.util.Calendar;

public class AddScheduleActivity extends AppCompatActivity implements View.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {
    /**
     * TAG
     */
    public static final String TAG = "AddScheduleActivity";
    private TextView tvTime;
    private int year = 0;
    private int month = 0;
    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private StringBuffer date, time;

    private TextView tvAlert1;
    private TextView tvAlert2;
    private TextView tvAlert3;
    private TextView tvAlert4;
    private TextView tvContent;

    private ImageView ivAddBack;
    private EditText etContent;
    private FindCommand findCommand;
    private Boolean setFindCommand;
    private CustomDatePicker mTimerPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        initView();

        initDateTime();
        initEvent();
    }

    private void initView() {
        tvTime = findViewById(R.id.tv_add_time);
        tvAlert1 = findViewById(R.id.tv_alert1);
        tvAlert2 = findViewById(R.id.tv_alert2);
        tvAlert3 = findViewById(R.id.tv_alert3);
        tvAlert4 = findViewById(R.id.tv_alert4);
        tvContent = findViewById(R.id.tv_content);
        etContent = findViewById(R.id.et_content);
        ivAddBack = findViewById(R.id.iv_add_back);
        findCommand = new FindCommand(this);
        date = new StringBuffer();
        time = new StringBuffer();
        initTimerPicker();
        setFindCommand = true;
        setClick(tvAlert2);
        Intent intent = getIntent();
        String NewsID = intent.getStringExtra("goodId");
        if (!TextUtils.isEmpty(NewsID)) {
            tvContent.setText(NewsID);
            etContent.setText(NewsID);
            startCommand(NewsID);
        }
    }


    private void initEvent() {
        tvTime.setOnClickListener(this);
        tvAlert1.setOnClickListener(this);
        tvAlert2.setOnClickListener(this);
        tvAlert3.setOnClickListener(this);
        tvAlert4.setOnClickListener(this);
        ivAddBack.setOnClickListener(this);
        etContent.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 输入的内容变化的监听
                tvContent.setText(etContent.getText().toString().trim());
                if (setFindCommand == true) {
                    startCommand(etContent.getText().toString().trim());
                }

                Log.e("输入过程中执行该方法", "文字变化");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // 输入前的监听
                Log.e("输入前确认执行该方法", "开始输入");

            }

            @Override
            public void afterTextChanged(Editable s) {
                // 输入后的监听
                Log.e("输入结束执行该方法", "输入结束");


            }
        });
    }

    private void setClick(TextView textView) {
        tvAlert1.setSelected(false);
        tvAlert2.setSelected(false);
        tvAlert3.setSelected(false);
        tvAlert4.setSelected(false);
        textView.setSelected(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimerPicker.onDestroy();
    }

    private void startCommand(String s) {
        findCommand.setContent(s);
        if (date.length() > 0) { //清除上次记录的日期
            date.delete(0, date.length());
        }
        if (time.length() > 0) { //清除上次记录的日期
            time.delete(0, time.length());
        }
        tvTime.setText(date.append(String.valueOf(findCommand.getYear())).append("-").append(String.valueOf(findCommand.getMonth())).append("-").append(findCommand.getDay()) + " " + time.append(String.valueOf(findCommand.getHour())).append(":").append(String.valueOf(findCommand.getMinute())));
        Log.i(TAG, "startCommand: " + date.append(String.valueOf(findCommand.getYear())).append("-").append(String.valueOf(findCommand.getMonth())).append("-").append(findCommand.getDay()) + " " + time.append(String.valueOf(findCommand.getHour())).append(":").append(String.valueOf(findCommand.getMinute())));
        ;
    }

    private void initTimerPicker() {
        //        Calendar calendar = Calendar.getInstance();
        //        String beginTime = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        String beginTime = "2018-10-17 18:00";
        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), true);

        tvTime.setText(endTime);

        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                tvTime.setText(DateFormatUtils.long2Str(timestamp, true));
            }

            @Override
            public void onNullSelected() {

            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker.setCancelable(true);
        // 显示时和分
        mTimerPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mTimerPicker.setCanShowAnim(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_alert1:
                setClick(tvAlert1);
                break;
            case R.id.tv_alert2:
                setClick(tvAlert2);
                break;
            case R.id.tv_alert3:
                setClick(tvAlert3);
                break;
            case R.id.tv_alert4:
                setClick(tvAlert4);
                break;
            case R.id.iv_add_back:
                finish();
                break;
            case R.id.tv_add_time:
                // 日期格式为yyyy-MM-dd HH:mm
                mTimerPicker.show(tvTime.getText().toString());
                break;
            default:
                break;
        }
    }

    private void initDateTime() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
    }

    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        this.hour = hourOfDay;
        this.minute = minute;
    }
}
