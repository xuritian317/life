package me.yokeyword.sample.app.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.sample.Common;
import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseActivity;
import me.yokeyword.sample.app.presenter.activity.RegisterPresenter;
import me.yokeyword.sample.app.ui.view.activity.RegisterView;
import me.yokeyword.sample.app.util.ToastUtils;

/**
 * Created by xu on 2017/6/20.
 */

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edt_tel)
    EditText edtTel;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.btn_send)
    Button btnSend;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.edt_name)
    EditText edtName;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @OnClick(R.id.btn_send)
    public void sendCode() {
        String phoneNum = edtTel.getText().toString().trim();
        presenter.sendCode(phoneNum);

    }

    @OnClick(R.id.btn_register)
    public void registerUser() {
        String phone = edtTel.getText().toString().trim();
        String code = edtCode.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
        String name = edtName.getText().toString().trim();
        presenter.registerUser(phone, code, pass, name, new RegisterPresenter.RegisterCallBack() {
            @Override
            public void callBack(int tag) {
                switch (tag) {
                    case Common.TAG_SUCCESS:
                        setResult(RESULT_OK);
                        finish();
                        break;
                    case Common.TAG_FAIL:
                        break;
                }
            }
        });
    }


    @Override
    public int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void setPresenter() {
        presenter = new RegisterPresenter(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(this, msg);
    }
}
