package com.codvision.vsm.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.codvision.vsm.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;
import java.util.Date;

public class CalendarActivity extends AppCompatActivity {
    private MaterialCalendarView materialCalendarView;//布局内的控件
    private CalendarDay currentDate;//自定义的日期对象

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
    }

    private void initData() {
        // 显示兴起补全的整个礼拜的上个月或者下个月的日期 一般会多出一行整个礼拜
        // 点击补全出来的另外一个月的信息 可以直接跳到那个月
        materialCalendarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);
        // 设置日历默认的时间为当前的时间
        materialCalendarView.setSelectedDate(new Date());
        currentDate = new CalendarDay(2019, 5, 1);
        //编辑日历属性
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
                currentDate = date;
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

}
