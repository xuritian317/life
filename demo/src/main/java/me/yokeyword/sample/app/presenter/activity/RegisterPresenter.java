package me.yokeyword.sample.app.presenter.activity;

import android.text.TextUtils;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import me.yokeyword.sample.Common;
import me.yokeyword.sample.app.model.entity.UserInfo;
import me.yokeyword.sample.app.presenter.BasePresenter;
import me.yokeyword.sample.app.ui.view.activity.RegisterView;
import me.yokeyword.sample.app.util.Logger;

/**
 * Created by xu on 2017/6/20.
 */

public class RegisterPresenter extends BasePresenter {
    private RegisterView view;

    public RegisterPresenter(RegisterView view) {
        this.view = view;
    }


    public void sendCode(String phoneNum) {
        if (phoneNum.isEmpty()) {
            view.showToast("手机号不能为空");
            return;
        }
        BmobSMS.requestSMSCode(phoneNum, "default", new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) {// 验证码发送成功
                    view.showToast("验证码发送成功");
                } else {
                    view.showToast("验证码发送失败");
                }
            }
        });
    }

    public void registerUser(String phone, String code, String pass, String name, final RegisterCallBack callBack) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(code)) {
            view.showToast("信息不能为空");
            return;
        }

        UserInfo info = new UserInfo();
        info.setMobilePhoneNumber(phone);
        info.setUsername(name);
        info.setName(name);
        info.setPassword(pass);

        info.signOrLogin(code, new SaveListener<UserInfo>() {
            @Override
            public void done(UserInfo info, BmobException e) {
                if (e == null) {
                    view.showToast("注册成功");
                    callBack.callBack(Common.TAG_SUCCESS);
                } else {
                    Logger.e("BmobException", e.toString());
                    view.showToast("注册失败");
                    callBack.callBack(Common.TAG_FAIL);
                }
            }
        });

    }

    public interface RegisterCallBack {
        void callBack(int tag);
    }
}
