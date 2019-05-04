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
import android.widget.TextView;
import android.widget.TimePicker;

import com.codvision.vsm.R;
import com.codvision.vsm.utils.FindCommand;

import java.util.Calendar;

public class AddScheduleActivity extends AppCompatActivity implements View.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener {

    private TextView tvDay;
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

    private EditText etContent;
    private FindCommand findCommand;
    private Boolean setFindCommand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        initView();
        initDateTime();
        initEvent();
    }

    private void initView() {
        tvDay = findViewById(R.id.tv_add_day);
        tvTime = findViewById(R.id.tv_add_time);
        tvAlert1 = findViewById(R.id.tv_alert1);
        tvAlert2 = findViewById(R.id.tv_alert2);
        tvAlert3 = findViewById(R.id.tv_alert3);
        tvAlert4 = findViewById(R.id.tv_alert4);
        tvContent = findViewById(R.id.tv_content);
        etContent = findViewById(R.id.et_content);
        findCommand = new FindCommand(this);
        date = new StringBuffer();
        time = new StringBuffer();

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

    private void setClick(TextView textView) {
        tvAlert1.setSelected(false);
        tvAlert2.setSelected(false);
        tvAlert3.setSelected(false);
        tvAlert4.setSelected(false);
        textView.setSelected(true);
    }

    private void startCommand(String s) {
        findCommand.setContent(s);
        if (date.length() > 0) { //清除上次记录的日期
            date.delete(0, date.length());
        }
        tvDay.setText(date.append(String.valueOf(findCommand.getYear())).append("年").append(String.valueOf(findCommand.getMonth())).append("月").append(findCommand.getDay()).append("日"));
        if (time.length() > 0) { //清除上次记录的日期
            time.delete(0, time.length());
        }
        tvTime.setText(time.append(String.valueOf(findCommand.getHour())).append("时").append(String.valueOf(findCommand.getMinute())).append("分"));
    }

    private void initEvent() {
        tvDay.setOnClickListener(this);
        tvTime.setOnClickListener(this);
        tvAlert1.setOnClickListener(this);
        tvAlert2.setOnClickListener(this);
        tvAlert3.setOnClickListener(this);
        tvAlert4.setOnClickListener(this);

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
            case R.id.tv_add_day:
                setFindCommand = false;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (date.length() > 0) { //清除上次记录的日期
                            date.delete(0, date.length());
                        }
                        tvDay.setText(date.append(String.valueOf(year)).append("年").append(String.valueOf(month)).append("月").append(day).append("日"));
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                final AlertDialog dialog = builder.create();
                View dialogView = View.inflate(this, R.layout.dialog_date, null);
                final DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.datePicker);

                dialog.setTitle("设置日期");
                dialog.setView(dialogView);
                dialog.show();
                //初始化日期监听事件
                datePicker.init(year, month - 1, day, this);
                break;
            case R.id.tv_add_time:
                setFindCommand = false;
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (time.length() > 0) { //清除上次记录的日期
                            time.delete(0, time.length());
                        }
                        tvTime.setText(time.append(String.valueOf(hour)).append("时").append(String.valueOf(minute)).append("分"));
                        dialog.dismiss();
                    }
                });
                builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog2 = builder2.create();
                View dialogView2 = View.inflate(this, R.layout.dialog_time, null);
                TimePicker timePicker = (TimePicker) dialogView2.findViewById(R.id.timePicker);
                timePicker.setCurrentHour(hour);
                timePicker.setCurrentMinute(minute);
                timePicker.setIs24HourView(true); //设置24小时制
                timePicker.setOnTimeChangedListener(this);
                dialog2.setTitle("设置时间");
                dialog2.setView(dialogView2);
                dialog2.show();
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
        hour = calendar.get(Calendar.HOUR);
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
