package com.codvision.vsm.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.codvision.vsm.R;
import com.codvision.vsm.ui.activity.SettingActivity;
import com.codvision.vsm.ui.activity.UserActivity;

public class UserFragment extends Fragment implements View.OnClickListener {
    /**
     * TAG
     */
    public static final String TAG = "UserFragment";

    private View view;
    private RelativeLayout rlUserSet;
    private ImageView tvSet;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        rlUserSet = view.findViewById(R.id.rl_user_set);
        tvSet = view.findViewById(R.id.tv_set);
    }

    private void initEvent() {
        rlUserSet.setOnClickListener(this);
        tvSet.setOnClickListener(this);
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
            default:
                break;
        }
    }
}
