package com.codvision.vsm.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.codvision.vsm.R;
import com.codvision.vsm.bean.Schedule;
import com.codvision.vsm.ui.MainActivity;
import com.codvision.vsm.ui.adapter.ScheduleAdapter;
import com.codvision.vsm.utils.datepicker.CustomDatePicker;
import com.codvision.vsm.utils.datepicker.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PlanFragment extends Fragment implements View.OnClickListener {
    /**
     * TAG
     */
    public static final String TAG = "PlanFragment";

    private View view;
    private TextView mTvSelectedDate;
    private ImageView ivTitleArrow;
    private ListView lvPlan;
    private CustomDatePicker mDatePicker;
    private List<Schedule> scheduleArrayList = new ArrayList<Schedule>();
    private ScheduleAdapter scheduleAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plan, container, false);
        initView();
        initDatePicker();
        initPlan();
        initEvent();
        return view;
    }

    private void initView() {
        mTvSelectedDate = view.findViewById(R.id.tv_selected_date);
        scheduleAdapter = new ScheduleAdapter(getActivity(), R.layout.plan_item, scheduleArrayList);
        lvPlan = view.findViewById(R.id.lv_plan);
        ivTitleArrow = view.findViewById(R.id.iv_title_arrow);
    }

    private void initPlan() {
        Schedule schedule1 = new Schedule(1, new Date(2019, 1, 1), "1", "英语学习", "每天", 1, "1", 1, new Date(2019, 1, 1), new Date(2019, 1, 1));
        scheduleArrayList.add(schedule1);
        Schedule schedule2 = new Schedule(1, new Date(2019, 1, 1), "1", "看书", "每天", 1, "1", 1, new Date(2019, 1, 1), new Date(2019, 1, 1));
        scheduleArrayList.add(schedule2);
    }


    private void initEvent() {
        mTvSelectedDate.setOnClickListener(this);
        lvPlan.setAdapter(scheduleAdapter);

        ivTitleArrow.setBackgroundResource(R.drawable.arrow_up);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDatePicker.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_selected_date:
                // 日期格式为yyyy-MM-dd
                mDatePicker.show(mTvSelectedDate.getText().toString());
                ivTitleArrow.setBackgroundResource(R.drawable.arrow_down);
                break;
            default:
                break;
        }
    }

    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2009-05-01", false);
        long endTimestamp = System.currentTimeMillis();

        mTvSelectedDate.setText(DateFormatUtils.long2Str(endTimestamp, false));

        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvSelectedDate.setText(DateFormatUtils.longStr(timestamp, false));
                ivTitleArrow.setBackgroundResource(R.drawable.arrow_up);
            }

            @Override
            public void onNullSelected() {
                ivTitleArrow.setBackgroundResource(R.drawable.arrow_up);
            }
        }, beginTimestamp, endTimestamp);

        // 不允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(false);
        // 不显示时和分
        mDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim(false);
    }


}