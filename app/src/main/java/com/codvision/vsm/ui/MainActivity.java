package com.codvision.vsm.ui;

import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.codvision.vsm.R;
import com.codvision.vsm.ui.fragment.PlanFragment;
import com.codvision.vsm.ui.fragment.TodayFragment;
import com.codvision.vsm.ui.fragment.UserFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvToday;
    private TextView tvPlan;
    private TextView tvUser;

    private PlanFragment planFragment;
    private TodayFragment todayFragment;
    private UserFragment userFragment;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        intiEvent();
    }

    private void initView() {
        tvToday = (TextView) findViewById(R.id.tv_main_today);
        tvPlan = (TextView) findViewById(R.id.tv_main_plan);
        tvUser = (TextView) findViewById(R.id.tv_main_user);
        planFragment = new PlanFragment();
        todayFragment = new TodayFragment();
        userFragment = new UserFragment();
        fragmentManager = getFragmentManager();

    }

    private void intiEvent() {
        tvToday.setClickable(true);
        tvPlan.setClickable(true);
        tvUser.setClickable(true);

        tvToday.setOnClickListener(this);
        tvPlan.setOnClickListener(this);
        tvUser.setOnClickListener(this);


        //首页
        fragmentManager.beginTransaction().replace(R.id.main_fragment, todayFragment).commit();
        setClick(tvToday);
    }

    private void setClick(TextView textView) {
        tvToday.setSelected(false);
        tvPlan.setSelected(false);
        tvUser.setSelected(false);

        Drawable iconUser = getResources().getDrawable(R.drawable.user_unclick);
        iconUser.setBounds(0, 0, 80, 80);
        tvUser.setCompoundDrawables(null, iconUser, null, null);
        Drawable iconOrder = getResources().getDrawable(R.drawable.plan_unclick);
        iconOrder.setBounds(0, 0, 80, 80);
        tvPlan.setCompoundDrawables(null, iconOrder, null, null);
        Drawable iconHome = getResources().getDrawable(R.drawable.home_unclick);
        iconHome.setBounds(0, 0, 80, 80);
        tvToday.setCompoundDrawables(null, iconHome, null, null);

        switch (textView.getId()) {
            case R.id.tv_main_today:
                iconHome = getResources().getDrawable(R.drawable.home_click);
                iconHome.setBounds(0, 0, 80, 80);
                tvToday.setCompoundDrawables(null, iconHome, null, null);

                break;
            case R.id.tv_main_plan:
                iconOrder = getResources().getDrawable(R.drawable.plan_click);
                iconOrder.setBounds(0, 0, 80, 80);
                tvPlan.setCompoundDrawables(null, iconOrder, null, null);
                break;
            case R.id.tv_main_user:
                iconUser = getResources().getDrawable(R.drawable.user_click);
                iconUser.setBounds(0, 0, 80, 80);
                tvUser.setCompoundDrawables(null, iconUser, null, null);
                break;
        }
        textView.setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main_today:
                setClick(tvToday);
                fragmentManager.beginTransaction().replace(R.id.main_fragment, planFragment).commit();
                break;
            case R.id.tv_main_plan:
                setClick(tvPlan);
                fragmentManager.beginTransaction().replace(R.id.main_fragment, userFragment).commit();
                break;
            case R.id.tv_main_user:
                setClick(tvUser);
                fragmentManager.beginTransaction().replace(R.id.main_fragment, userFragment).commit();
                break;
            default:
                break;
        }
    }
}
