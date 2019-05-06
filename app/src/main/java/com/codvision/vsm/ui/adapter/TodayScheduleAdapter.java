package com.codvision.vsm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codvision.vsm.R;
import com.codvision.vsm.bean.Schedule;

import java.util.List;

/**
 * Created by sxy on 2019/5/5 19:12
 * todo
 */
public class TodayScheduleAdapter extends ArrayAdapter<Schedule> {
    // 子项布局的id
    private int resourceId;

    // 构造函数
    public TodayScheduleAdapter(Context context, int textViewResourceId, List<Schedule> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    // 重写getView=
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Schedule schedule = getItem(position);
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate出子项布局，实例化其中的图片控件和文本控件
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.scheduleAim = (TextView) view.findViewById(R.id.tv_schedule_aim);
            viewHolder.scheduleTime = (TextView) view.findViewById(R.id.tv_schedule_time);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            // 取出缓存
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.scheduleAim.setText(schedule.getThing());
        viewHolder.scheduleTime.setText(schedule.getNote());
        return view;
    }

    // 内部类
    class ViewHolder {
        TextView scheduleAim;
        TextView scheduleTime;
    }

}
