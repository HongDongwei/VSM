package com.codvision.vsm.ui.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.codvision.vsm.R;
import com.codvision.vsm.module.bean.ScheduleDelete;
import com.codvision.vsm.module.bean.ScheduleGet;
import com.codvision.vsm.module.bean.Schedule;
import com.codvision.vsm.presenter.ScheduleDeletePresenter;
import com.codvision.vsm.presenter.ScheduleGetPresenter;
import com.codvision.vsm.presenter.contract.ScheduleDeleteContract;
import com.codvision.vsm.presenter.contract.ScheduleGetContract;
import com.codvision.vsm.ui.activity.AddPlanActivity;
import com.codvision.vsm.ui.adapter.ScheduleAdapter;
import com.codvision.vsm.utils.SharedPreferenceUtils;
import com.codvision.vsm.utils.datepicker.CustomDatePicker;
import com.codvision.vsm.utils.datepicker.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.codvision.vsm.utils.DayUtils.isDay;
import static com.codvision.vsm.utils.DayUtils.isMounth;

public class PlanFragment extends Fragment implements View.OnClickListener, ScheduleGetContract.View, ScheduleDeleteContract.View {
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
    private ScheduleGetPresenter scheduleGetPresenter;
    private ScheduleDeletePresenter scheduleDeletePresenter;
    private ScheduleGet scheduleGet;
    private TextView tvNum;
    private TextView tvComplete;
    private TextView tvUnComplete;
    private ImageView ivAdd;
    private String time;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plan, container, false);
        initView();
        initDatePicker();
        initEvent();
        return view;
    }

    @Override
    public void onResume() {
        scheduleGetPresenter.getSchedule(new ScheduleGet("", SharedPreferenceUtils.getUserId(getActivity())));
        super.onResume();
    }

    private void initView() {
        mTvSelectedDate = view.findViewById(R.id.tv_selected_date);
        scheduleAdapter = new ScheduleAdapter(getActivity(), R.layout.plan_item, scheduleArrayList);
        lvPlan = view.findViewById(R.id.lv_plan);
        ivTitleArrow = view.findViewById(R.id.iv_title_arrow);
        tvComplete = view.findViewById(R.id.tv_plan_complete);
        scheduleGetPresenter = new ScheduleGetPresenter(this, getActivity());
        scheduleDeletePresenter = new ScheduleDeletePresenter(this, getActivity());
        tvNum = view.findViewById(R.id.tv_plan_num);
        tvUnComplete = view.findViewById(R.id.tv_plan_uncomplete);
        ivAdd = view.findViewById(R.id.iv_plan_add);
    }


    private void initEvent() {
        mTvSelectedDate.setOnClickListener(this);
        ivAdd.setOnClickListener(this);
        lvPlan.setAdapter(scheduleAdapter);
        ivTitleArrow.setBackgroundResource(R.drawable.arrow_up);
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
                new AlertDialog.Builder(getActivity());
        normalDialog.setTitle("温馨提示：");
        normalDialog.setMessage("请问是否删除该计划?");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        scheduleDeletePresenter.deleteSchedule(new ScheduleDelete(scheduleArrayList.get(position).getId()));
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
            case R.id.iv_plan_add:
                Intent intent = new Intent(getActivity(), AddPlanActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2009-05-01", false);
        long endTimestamp = System.currentTimeMillis();

        mTvSelectedDate.setText(DateFormatUtils.long2Str(endTimestamp, false));
        scheduleGetPresenter.getSchedule(new ScheduleGet("", SharedPreferenceUtils.getUserId(getActivity())));
        time = DateFormatUtils.long2Str(endTimestamp, false);
        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(getActivity(), new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                scheduleGetPresenter.getSchedule(new ScheduleGet("", SharedPreferenceUtils.getUserId(getActivity())));
                mTvSelectedDate.setText(DateFormatUtils.long2Str(timestamp, false));
                time = DateFormatUtils.long2Str(timestamp, false);
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


    @Override
    public void getScheduleSuccess(ArrayList<Schedule> arrayList) {
        scheduleArrayList.clear();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int complete = 0;
        int unComplete = 0;
        if (arrayList.size() > 0) {
            Log.i(TAG, "getScheduleSuccess: 成功了,arrayList.size():" + arrayList.size() + " day:" + df.format(arrayList.get(0).getStartdate()) + " time:" + time);
            for (int i = 0; i < arrayList.size(); i++) {
                Schedule schedule = arrayList.get(i);
                if (isMounth(df.format(schedule.getStartdate()) + "", df.format(schedule.getEnddate()) + "", time)) {
                    Log.i(TAG, "getScheduleSuccess: true");
                    scheduleArrayList.add(schedule);
                }
            }
            for (int i = 0; i < scheduleArrayList.size(); i++) {
                Schedule schedule = arrayList.get(i);
                if (isDay(df.format(schedule.getStartdate()) + "", df.format(schedule.getEnddate()) + "", time)) {
                    complete += 1;
                } else {
                    unComplete += 1;
                }
            }
            tvNum.setText(scheduleArrayList.size() + "");
            tvUnComplete.setText(unComplete + "");
            tvComplete.setText(complete + "");
        } else {
            tvNum.setText("0");
            tvUnComplete.setText("0");
        }
        scheduleAdapter.notifyDataSetChanged();
    }

    @Override
    public void getScheduleFail(String code, String message) {

    }

    @Override
    public void deleteScheduleSuccess() {
        scheduleGetPresenter.getSchedule(new ScheduleGet("", SharedPreferenceUtils.getUserId(getActivity())));
    }

    @Override
    public void deleteScheduleFail(String code, String message) {

    }
}