package me.yokeyword.sample.app.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseActivity;
import me.yokeyword.sample.app.presenter.activity.LoginPresenter;
import me.yokeyword.sample.app.ui.view.activity.LoginView;
import me.yokeyword.sample.app.util.ToastUtils;

/**
 * Created by xu on 2017/6/20.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.edt_tel)
    EditText edtTel;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_back)
    TextView tvBack;
    @BindView(R.id.tv_resister)
    TextView tvResister;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @OnClick(R.id.btn_login)
    public void clickLogin() {
        presenter.login();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.hasCurrentUser();
    }

    @OnClick(R.id.tv_back)
    public void clickBack() {
        presenter.getBack();
    }

    @OnClick(R.id.tv_resister)
    public void clickRegister() {
        presenter.register();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void setPresenter() {
        presenter = new LoginPresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
    }

    @Override
    public void initData() {

    }

    @Override
    public void setToolbar() {
        mToolbar.setTitle("首页");
    }

    @Override
    public String getTelNum() {
        return edtTel.getText().toString();
    }

    @Override
    public String getPassword() {
        return edtPassword.getText().toString();
    }

    @Override
    public void showToast(String message) {
        ToastUtils.showToast(LoginActivity.this, message);
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void toRegister() {
        startActivityForResult(new Intent(LoginActivity.this, RegisterActivity.class), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}