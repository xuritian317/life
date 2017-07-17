package me.yokeyword.sample.app.ui.view.activity;

import me.yokeyword.sample.app.ui.view.BaseViewInterface;

/**
 * Created by xu on 2017/6/13.
 */

public interface LoginView extends BaseViewInterface {

    String getTelNum();

    String getPassword();

    void showToast(String message);

    void loginSuccess();

    void toRegister();
}
