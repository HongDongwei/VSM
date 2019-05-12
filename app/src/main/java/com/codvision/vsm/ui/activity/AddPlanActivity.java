package com.codvision.vsm.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.codvision.vsm.R;
import com.codvision.vsm.ui.MainActivity;

public class AddPlanActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivPlan1, ivPlan2, ivPlan3, ivPlan4, ivPlan5, ivPlan6, ivPlan7, ivPlan8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan);
        initView();
        initEvent();
    }

    private void initView() {
        ivPlan1 = findViewById(R.id.iv_plan_1);
        ivPlan2 = findViewById(R.id.iv_plan_2);
        ivPlan3 = findViewById(R.id.iv_plan_3);
        ivPlan4 = findViewById(R.id.iv_plan_4);
        ivPlan5 = findViewById(R.id.iv_plan_5);
        ivPlan6 = findViewById(R.id.iv_plan_6);
        ivPlan7 = findViewById(R.id.iv_plan_7);
        ivPlan8 = findViewById(R.id.iv_plan_8);
    }

    private void initEvent() {
        ivPlan1.setOnClickListener(this);
        ivPlan2.setOnClickListener(this);
        ivPlan3.setOnClickListener(this);
        ivPlan4.setOnClickListener(this);
        ivPlan5.setOnClickListener(this);
        ivPlan6.setOnClickListener(this);
        ivPlan7.setOnClickListener(this);
        ivPlan8.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_plan_1:
                Intent intent = new Intent(AddPlanActivity.this, AddPlanDetialActivity.class);
                intent.putExtra("plan_type", "1");
                startActivity(intent);
                break;
            case R.id.iv_plan_2:
                intent = new Intent(AddPlanActivity.this, AddPlanDetialActivity.class);
                intent.putExtra("plan_type", "2");
                startActivity(intent);
                break;
            case R.id.iv_plan_3:
                intent = new Intent(AddPlanActivity.this, AddPlanDetialActivity.class);
                intent.putExtra("plan_type", "3");
                startActivity(intent);
                break;
            case R.id.iv_plan_4:
                intent = new Intent(AddPlanActivity.this, AddPlanDetialActivity.class);
                intent.putExtra("plan_type", "4");
                startActivity(intent);
                break;
            case R.id.iv_plan_5:
                intent = new Intent(AddPlanActivity.this, AddPlanDetialActivity.class);
                intent.putExtra("plan_type", "5");
                startActivity(intent);
                break;
            case R.id.iv_plan_6:
                intent = new Intent(AddPlanActivity.this, AddPlanDetialActivity.class);
                intent.putExtra("plan_type", "6");
                startActivity(intent);
                break;
            case R.id.iv_plan_7:
                intent = new Intent(AddPlanActivity.this, AddPlanDetialActivity.class);
                intent.putExtra("plan_type", "7");
                startActivity(intent);
                break;
            case R.id.iv_plan_8:
                intent = new Intent(AddPlanActivity.this, AddPlanDetialActivity.class);
                intent.putExtra("plan_type", "8");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
