package com.codvision.vsm.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codvision.vsm.R;
import com.codvision.vsm.module.bean.User;
import com.codvision.vsm.ui.activity.FuturePlanActivity;
import com.codvision.vsm.ui.activity.SettingActivity;
import com.codvision.vsm.ui.activity.UserActivity;
import com.codvision.vsm.utils.SharedPreferenceUtils;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class UserFragment extends Fragment implements View.OnClickListener {
    /**
     * TAG
     */
    public static final String TAG = "UserFragment";

    private View view;
    private RelativeLayout rlUserSet;
    private ImageView tvSet;
    private ImageView ivHead;
    private TextView tvName;
    private TextView tvSign;
    private User user;
    private RelativeLayout rlFuturePlan;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        initView();
        initEvent();
        return view;
    }


    private void initView() {
        rlUserSet = view.findViewById(R.id.rl_user_set);
        tvSet = view.findViewById(R.id.tv_set);
        tvName = view.findViewById(R.id.tv_user_name);
        tvSign = view.findViewById(R.id.tv_user_sign);
        ivHead = view.findViewById(R.id.iv_user_head);
        rlFuturePlan = view.findViewById(R.id.rl_to_futureplan);
    }

    private void initEvent() {
        rlUserSet.setOnClickListener(this);
        tvSet.setOnClickListener(this);
        rlFuturePlan.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        user = SharedPreferenceUtils.getUser(getActivity());
        changeHeadPic(R.drawable.head_default, ivHead);
        tvName.setText(user.getUsername());
        tvSign.setText(user.getIntro());
        super.onResume();
    }

    /**
     * 设置圆形图像
     *
     * @param src       图片对象
     * @param imageView 设置图片的控件
     */
    private void changeHeadPic(Object src, ImageView imageView) {
        Glide.with(this).load(src)
                .bitmapTransform(new CropCircleTransformation(getActivity()))
                .into(imageView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_user_set:
                Intent intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_set:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_to_futureplan:
                intent = new Intent(getActivity(), FuturePlanActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
