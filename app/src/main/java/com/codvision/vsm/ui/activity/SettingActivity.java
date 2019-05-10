package com.codvision.vsm.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.codvision.vsm.R;
import com.codvision.vsm.utils.SharedPreferenceUtils;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvLogout;
    private ImageButton ibBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initEvent();
    }

    private void initView() {
        tvLogout = findViewById(R.id.tv_logout);
        ibBack = findViewById(R.id.ib_set_back);
    }

    private void initEvent() {
        tvLogout.setOnClickListener(this);
        ibBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_logout:
                SharedPreferenceUtils.clearLoginInfo(SettingActivity.this);
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.ib_set_back:
                break;
            default:
                break;
        }
    }
}
