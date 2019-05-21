package com.codvision.vsm.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.codvision.vsm.R;
import com.codvision.vsm.module.bean.Conclusion;
import com.codvision.vsm.module.bean.ConclusionGet;
import com.codvision.vsm.module.bean.ConclusionInsert;
import com.codvision.vsm.module.bean.ConclusionSubmit;
import com.codvision.vsm.presenter.ConclusionGetPresenter;
import com.codvision.vsm.presenter.ConclusionInsertPresenter;
import com.codvision.vsm.presenter.ConclusionSubmitPresenter;
import com.codvision.vsm.presenter.contract.ConclusionGetContract;
import com.codvision.vsm.presenter.contract.ConclusionInsertContract;
import com.codvision.vsm.presenter.contract.ConclusionSubmitContract;
import com.codvision.vsm.utils.DayUtils;
import com.codvision.vsm.utils.SharedPreferenceUtils;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity implements ConclusionInsertContract.View, ConclusionGetContract.View , ConclusionSubmitContract.View {
    public static final String TAG = "CalendarActivity";
    private MaterialCalendarView materialCalendarView;//布局内的控件
    private CalendarDay currentDate;//自定义的日期对象
    private ConclusionInsertPresenter conclusionInsertPresenter;
    private ConclusionGetPresenter conclusionGetPresenter;
    private ConclusionSubmitPresenter conclusionSubmitPresenter;
    private Button bt_save;
    private Button bt_back;
    private ImageView iv_calendar_back;
    private EditText et_conclusion;
    private String nowTime;
    ArrayList<Conclusion> arrayList = new ArrayList<>();
    private boolean isHave;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initView();
        initData();

    }

    private void initView() {
        // 实例化
        materialCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        conclusionInsertPresenter = new ConclusionInsertPresenter(this, this);
        conclusionGetPresenter = new ConclusionGetPresenter(this, this);
        conclusionSubmitPresenter=new ConclusionSubmitPresenter(this,this);
        conclusionGetPresenter.getConclusion(new ConclusionGet(SharedPreferenceUtils.getUserId(CalendarActivity.this)));
        bt_save = findViewById(R.id.bt_save);
        bt_back = findViewById(R.id.bt_back);
        iv_calendar_back = findViewById(R.id.iv_calendar_back);
        et_conclusion = findViewById(R.id.et_conclusion);


        materialCalendarView.setSelectedDate(new Date());
        materialCalendarView.state().edit().commit();

        iv_calendar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialCalendarView.setSelectedDate(new Date());
                materialCalendarView.state().edit().commit();
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH)+1;
                int day = c.get(Calendar.DAY_OF_MONTH);
                nowTime = year + "-";
                if (month < 10) {
                    nowTime += "0" + month;
                } else {
                    nowTime += month;
                }

                if (day < 10) {
                    nowTime += "-" + "0" + day;
                } else {
                    nowTime += "-" + day;
                }
                conclusionGetPresenter.getConclusion(new ConclusionGet(SharedPreferenceUtils.getUserId(CalendarActivity.this)));
            }
        });
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHave) {
                    conclusionInsertPresenter.insertConclusion(new ConclusionInsert(SharedPreferenceUtils.getUserId(CalendarActivity.this), et_conclusion.getText().toString().trim(), nowTime));
                }else {
                    conclusionSubmitPresenter.submitConclusion(new ConclusionSubmit(id,et_conclusion.getText().toString().trim()));
                }

            }
        });
    }

    private void initData() {
        // 显示兴起补全的整个礼拜的上个月或者下个月的日期 一般会多出一行整个礼拜
        // 点击补全出来的另外一个月的信息 可以直接跳到那个月
        materialCalendarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
        // 设置日历默认的时间为当前的时间
        materialCalendarView.setSelectedDate(new Date());
        currentDate = new CalendarDay(new Date());
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        nowTime = year + "-";
        if (month < 10) {
            nowTime += "0" + month;
        } else {
            nowTime += month;
        }

        if (day < 10) {
            nowTime += "-" + "0" + day;
        } else {
            nowTime += "-" + day;
        }
        //编辑日历属性
        Calendar calendar = Calendar.getInstance();
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)   //设置每周开始的第一天
                .setMinimumDate(CalendarDay.from(2010, 4, 3))  //设置可以显示的最早时间
                .setMaximumDate(CalendarDay.from(2020, 5, 12))//设置可以显示的最晚时间
                .setCalendarDisplayMode(CalendarMode.MONTHS)//设置显示模式，可以显示月的模式，也可以显示周的模式
                .commit();// 返回对象并保存
        //      设置点击日期的监听
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                conclusionGetPresenter.getConclusion(new ConclusionGet(SharedPreferenceUtils.getUserId(CalendarActivity.this)));
                currentDate = date;
                int year = currentDate.getYear();
                int month = currentDate.getMonth() + 1; //月份跟系统一样是从0开始的，实际获取时要加1
                int day = currentDate.getDay();
                nowTime = year + "-";
                if (month < 10) {
                    nowTime += "0" + month;
                } else {
                    nowTime += month;
                }

                if (day < 10) {
                    nowTime += "-" + "0" + day;
                } else {
                    nowTime += "-" + day;
                }
            }
        });
    }

    /**
     * 获取点击后的日期数
     */
    public void getTime(View view) {
        if (currentDate != null) {
            int year = currentDate.getYear();
            int month = currentDate.getMonth() + 1; //月份跟系统一样是从0开始的，实际获取时要加1
            int day = currentDate.getDay();
            Toast.makeText(this, currentDate.toString() + "你选中的是：" + year + "-" + month + "-" + day, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "请选择时间", Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void insertConclusionSuccess() {

    }

    @Override
    public void insertConclusionFail(String code, String message) {

    }

    @Override
    public void getConclusionSuccess(ArrayList<Conclusion> arrayList) {
        Log.i(TAG, "getConclusionSuccess: true");
        this.arrayList.clear();
        et_conclusion.setText("");
        isHave = false;
        for (int i = 0; i < arrayList.size(); i++) {
            this.arrayList.add(arrayList.get(i));
        }
        for (int i = 0; i < this.arrayList.size(); i++) {
            if (DayUtils.getDay(arrayList.get(i).getTime()).equals(nowTime)) {
                et_conclusion.setText(arrayList.get(i).getContent());
                isHave = true;
                id=arrayList.get(i).getId();
            }
        }

    }

    @Override
    public void getConclusionFail(String code, String message) {

    }

    @Override
    public void submitConclusionSuccess() {

    }

    @Override
    public void submitConclusionFail(String code, String message) {

    }
}
