package com.codvision.vsm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codvision.vsm.R;
import com.codvision.vsm.module.bean.Schedule;

import java.util.List;

/**
 * Created by sxy on 2019/5/5 19:12
 * todo
 */
public class ScheduleAdapter extends ArrayAdapter<Schedule> {
    // 子项布局的id
    private int resourceId;

    // 构造函数
    public ScheduleAdapter(Context context, int textViewResourceId, List<Schedule> objects) {
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
            viewHolder.planAim = (TextView) view.findViewById(R.id.tv_plan_aim);
            viewHolder.planTime = (TextView) view.findViewById(R.id.tv_plan_time);
            viewHolder.planWeight = (TextView) view.findViewById(R.id.tv_plan_weight);
            viewHolder.progressBar = (ProgressBar) view.findViewById(R.id.pb_main_download);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            // 取出缓存
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.planAim.setText(schedule.getThing());
        viewHolder.planTime.setText(schedule.getNote());
        viewHolder.planWeight.setText("1%");
        viewHolder.progressBar.setProgress(2);
        return view;
    }

    // 内部类
    class ViewHolder {
        TextView planAim;
        TextView planTime;
        TextView planWeight;
        ProgressBar progressBar;
    }

}
