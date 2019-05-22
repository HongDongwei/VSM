package com.codvision.vsm.ui.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codvision.vsm.R;
import com.codvision.vsm.module.bean.Schedule;

import java.util.Calendar;
import java.util.List;

/**
 * Created by sxy on 2019/5/5 19:12
 * todo
 */
public class ScheduleAdapter extends ArrayAdapter<Schedule> {
    // 子项布局的id
    private int resourceId;
    private Calendar calendar;

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
            viewHolder.planStart = (TextView) view.findViewById(R.id.tv_plan_start);
            viewHolder.planEnd = (TextView) view.findViewById(R.id.tv_plan_end);
            viewHolder.ivHead = (ImageView) view.findViewById(R.id.fruit_image);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            // 取出缓存
            viewHolder = (ViewHolder) view.getTag();
        }

        calendar = Calendar.getInstance();
        setType(schedule.getType(), viewHolder.ivHead);
        viewHolder.planAim.setText(schedule.getThing());
        viewHolder.planTime.setText(schedule.getPlace());
        calendar.setTime(schedule.getStartdate());
        viewHolder.planStart.setText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        calendar.setTime(schedule.getEnddate());
        viewHolder.planEnd.setText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        return view;
    }

    private void setType(String type, ImageView ivTypr) {
        switch (type) {
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
        ImageView ivHead;
        TextView planAim;
        TextView planTime;
        TextView planStart;
        TextView planEnd;
    }

}
