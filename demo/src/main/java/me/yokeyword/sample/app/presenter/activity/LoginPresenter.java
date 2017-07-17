package me.yokeyword.sample.app.presenter.activity;

import android.text.TextUtils;

import cn.bmob.v3.BmobUser;
import me.yokeyword.sample.Common;
import me.yokeyword.sample.app.model.dao.IUserDao;
import me.yokeyword.sample.app.model.dao.UserDao;
import me.yokeyword.sample.app.presenter.BasePresenter;
import me.yokeyword.sample.app.ui.view.activity.LoginView;
import me.yokeyword.sample.app.util.Logger;


/**
 * Created by xu on 2017/6/13.
 */

public class LoginPresenter extends BasePresenter {
    private UserDao userDao;
    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
        userDao = new UserDao();
    }

    public void login() {
        String telIn = view.getTelNum();
        String pass = view.getPassword();
        if (TextUtils.isEmpty(telIn) || TextUtils.isEmpty(pass)) {
            view.showToast("请输入正确的用户名或者密码!");
            return;
        }
        userDao.login(telIn, pass, new IUserDao.MessageCallBack() {
            @Override
            public void callBack(int tag) {
                switch (tag) {
                    case Common.TAG_FAIL:
                        view.showToast("用户名或密码不正确!请重新输入!");
                        Logger.e("success", "");
                        break;
                    case Common.TAG_SUCCESS:
                        view.loginSuccess();
                        Logger.e("fail", "");
                        break;
                }
            }
        });

    }

    public void register() {
        view.toRegister();
    }

    public void getBack() {
    }

    public void hasCurrentUser() {
        BmobUser user = BmobUser.getCurrentUser();
        if (user != null) {
            view.loginSuccess();
            Logger.e("getCurrentUser", "");
        }
    }
}
