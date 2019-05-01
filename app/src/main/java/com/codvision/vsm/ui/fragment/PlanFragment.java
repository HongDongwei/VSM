package com.codvision.vsm.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codvision.vsm.R;

public class PlanFragment extends Fragment implements View.OnClickListener {
    /**
     * TAG
     */
    public static final String TAG = "PlanFragment";

    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_plan, container, false);
        initView();
        initEvent();
        return view;
    }

    private void initView() {

    }

    private void initEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}