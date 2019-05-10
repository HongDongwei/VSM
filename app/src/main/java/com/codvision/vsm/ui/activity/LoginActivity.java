package com.codvision.vsm.ui.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.codvision.vsm.App;
import com.codvision.vsm.R;
import com.codvision.vsm.presenter.LoginPresenter;
import com.codvision.vsm.presenter.contract.LoginContract;
import com.codvision.vsm.ui.MainActivity;
import com.codvision.vsm.utils.SharedPreferenceUtils;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {
    public static final String TAG = "LoginActivity";
    private LoginPresenter presenter;
    private TextInputLayout inputUser;
    private TextInputLayout inputPassword;
    private TextView tvRegister;
    private ImageButton ibfinish;
    private TextView cvLogin;
    private EditText etUser;
    private EditText etPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initEvent();
    }

    private void initView() {
        inputUser = (TextInputLayout) findViewById(R.id.et_user);
        inputPassword = (TextInputLayout) findViewById(R.id.et_password);
        cvLogin = (TextView) findViewById(R.id.tv_login);
        tvRegister = findViewById(R.id.tv_login_register);
        ibfinish = findViewById(R.id.ib_login_finish);
        etUser = inputUser.getEditText();
        etPwd = inputPassword.getEditText();
        presenter = new LoginPresenter(this, this);
    }

    private void initEvent() {
        tvRegister.setOnClickListener(this);
        ibfinish.setOnClickListener(this);
        cvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                // 清空错误提示
                etUser.setError(null);
                etPwd.setError(null);
                String name = etUser.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    inputUser.setError("用户账号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    inputPassword.setError("用户密码不能为空");
                    return;
                }
                presenter.login(name, pwd);
                break;
            case R.id.tv_login_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.ib_login_finish:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        App.hide();
        finish();
    }

    @Override
    public void loadFail(String code, String message) {
        Log.i(TAG, "loadFail: " + message);
        if ("1004".equals(code)) {
            inputPassword.setError("密码错误，请重新输入");
        } else if ("1005".equals(code)) {
            inputUser.setError("账号错误，请重新输入");
        }

    }


}
