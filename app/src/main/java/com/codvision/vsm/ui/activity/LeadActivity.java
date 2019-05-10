package com.codvision.vsm.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.codvision.vsm.App;
import com.codvision.vsm.R;
import com.codvision.vsm.module.bean.Login;
import com.codvision.vsm.presenter.LoginPresenter;
import com.codvision.vsm.presenter.contract.LoginContract;
import com.codvision.vsm.ui.MainActivity;
import com.codvision.vsm.utils.SharedPreferenceUtils;

public class LeadActivity extends AppCompatActivity implements LoginContract.View {
    public static final String TAG = "LeadActivity";
    private LoginPresenter presenter;
    private Handler handler = new Handler();
    int time = 3;
    private Context context;
    private TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);
        initView();
        initEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        context = this;
        tvTime = findViewById(R.id.tv_lead_time);
        tvTime.setText(String.valueOf(time));
        handler = new Handler();
        presenter = new LoginPresenter(this, this);

    }

    private void initEvent() {
        handler.postDelayed(runnable, 1);

    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            time--;
            handler.postDelayed(this, 1000);
            tvTime.setText(String.valueOf(time));
            if (time == 0) {
                if (SharedPreferenceUtils.getUserState(context)) {
                    Intent intent = new Intent(LeadActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(LeadActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                //  presenter.login(SharedPreferenceUtils.getUserName(context), SharedPreferenceUtils.getPwd(context));
            }
        }
    };


    @Override
    public void loginSuccess() {
        Intent intent = new Intent(LeadActivity.this, MainActivity.class);
        startActivity(intent);
        App.hide();
        finish();
    }

    @Override
    public void loadFail(String code, String message) {
        Log.i(TAG, "loadFail: " + message);
        Toast.makeText(LeadActivity.this, "用户还未登录", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LeadActivity.this, LoginActivity.class);
        startActivity(intent);
        App.hide();
        finish();
    }
}
