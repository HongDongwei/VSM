package com.codvision.vsm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.codvision.vsm.R;
import com.codvision.vsm.module.bean.FuturePlan;

import java.util.List;

/**
 * Created by sxy on 2019/5/5 19:12
 * todo
 */
public class FuturePlanAdapter extends ArrayAdapter<FuturePlan> {
    // 子项布局的id
    private int resourceId;

    // 构造函数
    public FuturePlanAdapter(Context context, int textViewResourceId, List<FuturePlan> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    // 重写getView=
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FuturePlan futureplan = getItem(position);
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate出子项布局，实例化其中的图片控件和文本控件
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.planTime = (TextView) view.findViewById(R.id.tv_item_futureplan_time);
            viewHolder.planContent = (TextView) view.findViewById(R.id.tv_item_futureplan_content);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            // 取出缓存
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.planTime.setText(futureplan.getFtime() + "天");
        viewHolder.planContent.setText(futureplan.getFcontent() + "");
        return view;
    }

    // 内部类
    class ViewHolder {
        TextView planTime;
        TextView planContent;
    }

}
