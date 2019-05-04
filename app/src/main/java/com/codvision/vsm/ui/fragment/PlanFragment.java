package com.codvision.vsm.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codvision.vsm.R;
import com.codvision.vsm.utils.datepicker.CustomDatePicker;
import com.codvision.vsm.utils.datepicker.DateFormatUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PlanFragment extends Fragment implements View.OnClickListener {
    /**
     * TAG
     */
    public static final String TAG = "PlanFragment";

    private View view;
    private TextView mTvSelectedDate;
    private CustomDatePicker mDatePicker;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plan, container, false);
        initView();
        initDatePicker();

        initEvent();
        return view;
    }

    private void initView() {
        mTvSelectedDate = view.findViewById(R.id.tv_selected_date);

    }

    private void initEvent() {
        mTvSelectedDate.setOnClickListener(this);
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
                mTvSelectedDate.setText(DateFormatUtils.long2Str(timestamp, false));
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