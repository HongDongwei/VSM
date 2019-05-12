package com.codvision.vsm.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codvision.vsm.App;
import com.codvision.vsm.R;
import com.codvision.vsm.module.bean.User;
import com.codvision.vsm.presenter.InfoSubmitPresenter;
import com.codvision.vsm.presenter.LoginPresenter;
import com.codvision.vsm.presenter.contract.InfoSubmitContract;
import com.codvision.vsm.presenter.contract.LoginContract;
import com.codvision.vsm.utils.SharedPreferenceUtils;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class UserActivity extends AppCompatActivity implements View.OnClickListener, InfoSubmitContract.View {

    public static final String TAG = "UserFragment";
    private static final int TYPE_EMAIL = 1;
    private static final int TYPE_SIGN = 2;
    private static final int TYPE_PWD = 3;
    private ImageView ivHeadChoice;
    private TextView tvId;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvSex;
    private TextView tvSign;
    private TextView tvPwd;
    private TextView tvSave;
    private ImageButton ibBack;

    private String[] sexArry = new String[]{"保密", "女", "男"};// 性别选择
    private User user;
    private int checkdItem;

    private InfoSubmitPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initView();
        initEvent();
        initUserInfo();
    }


    private void initView() {
        ivHeadChoice = findViewById(R.id.iv_head_change);
        tvId = findViewById(R.id.tv_id_change);
        tvName = findViewById(R.id.tv_name_change);
        tvEmail = findViewById(R.id.tv_email_change);
        tvSex = findViewById(R.id.tv_sex_change);
        tvSign = findViewById(R.id.tv_sign_change);
        tvPwd = findViewById(R.id.tv_pwd_change);
        tvSave = findViewById(R.id.tv_save);
        ibBack = findViewById(R.id.ib_user_back);
        user = SharedPreferenceUtils.getUser(this);
        presenter = new InfoSubmitPresenter(this, this);
    }


    private void initEvent() {
        ivHeadChoice.setOnClickListener(this);
        tvId.setOnClickListener(this);
        tvName.setOnClickListener(this);
        tvEmail.setOnClickListener(this);
        tvSex.setOnClickListener(this);
        tvSign.setOnClickListener(this);
        tvPwd.setOnClickListener(this);
        tvSave.setOnClickListener(this);
        ibBack.setOnClickListener(this);
    }

    private void initUserInfo() {
        changeHeadPic(R.drawable.head_default, ivHeadChoice);
        tvId.setText(String.valueOf(user.getId()));
        tvName.setText(user.getUsername());
        tvEmail.setText(user.getEmail());
        checkdItem = user.getGender();
        tvSex.setText(sexArry[checkdItem]);
        tvSign.setText(user.getIntro());
        tvPwd.setText("点击修改密码");
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
                sexChooseDialog(checkdItem);
                break;
            case R.id.tv_pwd_change:
                modifyDialog("请输入你要修改的密码", TYPE_PWD);
                break;
            case R.id.tv_sign_change:
                modifyDialog("请输入您要修改的签名", TYPE_SIGN);
                break;
            case R.id.tv_email_change:
                modifyDialog("请输入您要修改的邮箱", TYPE_EMAIL);
                break;
            case R.id.ib_user_back:
                finish();
                break;
            case R.id.tv_save:
                presenter.submit(user);
                break;
            default:
                break;
        }
    }

    /**
     * 设置圆形图像
     *
     * @param src       图片对象
     * @param imageView 设置图片的控件
     */
    private void changeHeadPic(Object src, ImageView imageView) {
        Glide.with(this).load(src)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(imageView);
    }

    /**
     * 修改名字等dialog
     *
     * @param tips 提示语句
     * @param type 需要修改文本的控件
     */
    private void modifyDialog(String tips, final int type) {
        // 使用LayoutInflater来加载dialog_setname.xml布局
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View nameView = layoutInflater.inflate(R.layout.dialog_modify, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // 使用setView()方法将布局显示到dialog
        alertDialogBuilder.setView(nameView);

        final EditText userInput = (EditText) nameView.findViewById(R.id.et_change_content);

        SpannableString ss = new SpannableString(tips);
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
                                String content = userInput.getText().toString().trim();
                                switch (type) {
                                    case TYPE_EMAIL:
                                        tvEmail.setText(content);
                                        user.setEmail(content);
                                        break;
                                    case TYPE_SIGN:
                                        tvSign.setText(content);
                                        user.setIntro(content);
                                        break;
                                    case TYPE_PWD:
                                        tvPwd.setText(content);
                                        user.setPassword(content);

                                }
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

    /**
     * 修改性别
     *
     * @param item 默认选项
     */
    private void sexChooseDialog(int item) {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
        builder3.setSingleChoiceItems(sexArry, item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                tvSex.setText(sexArry[which]);
                user.setGender(which);
                dialog.dismiss();
            }
        });
        builder3.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            //  ivHeadChoice.setImageURI(data.getData());
            //设置圆形图像
            if (data != null) {
                changeHeadPic(data.getData(), ivHeadChoice);
            }
        }
    }

    @Override
    public void submitSuccess() {
        SharedPreferenceUtils.putSelfInfo(this, user);
        finish();
        App.hide();
    }

    @Override
    public void submitFail(String code, String message) {
        Log.i(TAG, "submitFail: " + message);
        Toast.makeText(this, "提交失败", Toast.LENGTH_LONG).show();
    }
}
