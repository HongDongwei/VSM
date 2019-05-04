package com.codvision.vsm.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.codvision.vsm.R;
import com.codvision.vsm.utils.DateTimeDialogOnlyYMD;

import java.text.SimpleDateFormat;
import java.util.Date;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class UserActivity extends AppCompatActivity implements View.OnClickListener, DateTimeDialogOnlyYMD.MyOnDateSetListener {

    public static final String TAG = "UserFragment";
    private ImageView ivHeadChoice;
    private TextView tvSexChange;
    private TextView tvNameChange;
    private TextView tvAgeChange;
    private TextView tvSignChange;
    private TextView tvUserName;
    private TextView tvSave;
    private TextView tvTelChange;
    private String[] sexArry = new String[]{"保密", "女", "男"};// 性别选择
    private int checkdItem = 0;
    private ImageButton mLeftButton;

    private DateTimeDialogOnlyYMD dateTimeDialogOnlyYM;
    private DateTimeDialogOnlyYMD dateTimeDialogOnlyYMD;
    private DateTimeDialogOnlyYMD dateTimeDialogOnlyYear;
    private SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        initEvent();
    }

    private void initView() {
        ivHeadChoice = findViewById(R.id.iv_head_change);
        tvSexChange = findViewById(R.id.tv_sex_change);
        tvNameChange = findViewById(R.id.tv_name_change);
        tvAgeChange = findViewById(R.id.tv_age_change);
        tvSignChange = findViewById(R.id.tv_sign_change);
        tvUserName = findViewById(R.id.tv_user_name);
        tvTelChange = findViewById(R.id.tv_tel_change);
        mLeftButton = findViewById(R.id.mLeftButton);
        tvSave = findViewById(R.id.tv_save);
        dateTimeDialogOnlyYMD = new DateTimeDialogOnlyYMD(this, this, true, true, true);
        dateTimeDialogOnlyYM = new DateTimeDialogOnlyYMD(this, this, false, true, true);

    }


    private void initEvent() {
        ivHeadChoice.setOnClickListener(this);
        tvSexChange.setOnClickListener(this);
        tvNameChange.setOnClickListener(this);
        tvAgeChange.setOnClickListener(this);
        tvSignChange.setOnClickListener(this);
        tvTelChange.setOnClickListener(this);
        mLeftButton.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        //初始化加载
        changeHeadPic(R.drawable.head_default, ivHeadChoice);

    }


    private void changeHeadPic(Object src, ImageView imageView) {
        //设置圆形图像
        Glide.with(this).load(src)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(imageView);
    }

    private void onCreateNameDialog() {
        // 使用LayoutInflater来加载dialog_setname.xml布局
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View nameView = layoutInflater.inflate(R.layout.dialog_setname, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // 使用setView()方法将布局显示到dialog
        alertDialogBuilder.setView(nameView);

        final EditText userInput = (EditText) nameView.findViewById(R.id.et_name_change);

        SpannableString ss = new SpannableString("请输入您要修改的名字!");
// 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(8, true);
// 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        userInput.setHint(new SpannedString(ss));

        // 设置Dialog按钮
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("修改",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                tvNameChange.setText(userInput.getText().toString().trim());
                            }
                        })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void onCreateSignDialog() {
        // 使用LayoutInflater来加载dialog_setname.xml布局
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View nameView = layoutInflater.inflate(R.layout.dialog_setname, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // 使用setView()方法将布局显示到dialog
        alertDialogBuilder.setView(nameView);

        final EditText userInput = (EditText) nameView.findViewById(R.id.et_name_change);

        SpannableString ss = new SpannableString("请输入您修改的签名!");
// 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(8, true);
// 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        userInput.setHint(new SpannedString(ss));
        // 设置Dialog按钮
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("修改",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                tvSignChange.setText(userInput.getText().toString().trim());
                            }
                        })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void onCreateTelDialog() {
        // 使用LayoutInflater来加载dialog_setname.xml布局
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View nameView = layoutInflater.inflate(R.layout.dialog_setname, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // 使用setView()方法将布局显示到dialog
        alertDialogBuilder.setView(nameView);

        final EditText userInput = (EditText) nameView.findViewById(R.id.et_name_change);

        SpannableString ss = new SpannableString("请输入您的电话");
// 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(8, true);
// 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        userInput.setHint(new SpannedString(ss));
        // 设置Dialog按钮
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("修改",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 获取edittext的内容,显示到textview
                                tvTelChange.setText(userInput.getText().toString().trim());
                            }
                        })
                .setNegativeButton("取消",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void showSexChooseDialog() {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);// 自定义对话框
        builder3.setSingleChoiceItems(sexArry, checkdItem, new DialogInterface.OnClickListener() {// 2默认的选中
            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                tvSexChange.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder3.show();// 让弹出框显示
    }


    @Override
    public void onDateSet(Date date, int type) {
        String str = mFormatter.format(date);
        String[] s = str.split("-");
        String time = "";
        if (type == 1) {
            time = s[0];
        } else if (type == 2) {
            time = s[0] + "-" + s[1];

        } else if (type == 3) {
            time = s[0] + "-" + s[1] + "-" + s[2];
        }
        tvAgeChange.setText(time);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head_change:
                Log.i(TAG, "onClick: iv_head_change ");
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
                break;
            case R.id.tv_sex_change:
                showSexChooseDialog();
                break;
            case R.id.tv_name_change:
                onCreateNameDialog();
                break;
            case R.id.tv_age_change:
                dateTimeDialogOnlyYMD.hideOrShow();
                break;
            case R.id.tv_sign_change:
                onCreateSignDialog();
                break;
            case R.id.tv_tel_change:
                onCreateTelDialog();
                break;
            case R.id.mLeftButton:
                finish();
                break;
            case R.id.tv_save:
                Toast.makeText(this, "已经保存了", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            //  ivHeadChoice.setImageURI(data.getData());
            //设置圆形图像
            changeHeadPic(data.getData(), ivHeadChoice);
        }
    }
}
