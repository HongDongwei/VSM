package com.codvision.vsm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codvision.vsm.R;
import com.codvision.vsm.module.bean.Schedule;

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
            viewHolder.iv_schedule_pic=(ImageView)view.findViewById(R.id.iv_schedule_pic);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            // 取出缓存
            viewHolder = (ViewHolder) view.getTag();
        }
        setType(schedule.getType(), viewHolder.iv_schedule_pic);
        viewHolder.scheduleAim.setText(schedule.getThing());
        viewHolder.scheduleTime.setText(schedule.getPlace());
        return view;
    }
    private void setType(String type, ImageView ivTypr) {
        switch (type) {
            case "0":
                ivTypr.setBackgroundResource(R.drawable.alarmclock);
                break;
            case "1":
                ivTypr.setBackgroundResource(R.drawable.book);
                break;
            case "2":
                ivTypr.setBackgroundResource(R.drawable.building);
                break;
            case "3":
                ivTypr.setBackgroundResource(R.drawable.doctor);
                break;
            case "4":
                ivTypr.setBackgroundResource(R.drawable.eg_study);
                break;
            case "5":
                ivTypr.setBackgroundResource(R.drawable.painting);
                break;
            case "6":
                ivTypr.setBackgroundResource(R.drawable.run);
                break;
            case "7":
                ivTypr.setBackgroundResource(R.drawable.water);
                break;
            case "8":
                ivTypr.setBackgroundResource(R.drawable.add_item);
                break;
            default:
                break;


        }

    }
    // 内部类
    class ViewHolder {
        TextView scheduleAim;
        TextView scheduleTime;
        ImageView iv_schedule_pic;
    }

}
