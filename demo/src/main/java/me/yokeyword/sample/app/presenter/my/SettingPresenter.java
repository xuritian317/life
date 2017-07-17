package me.yokeyword.sample.app.presenter.my;

import me.yokeyword.sample.app.model.entity.UserInfo;
import me.yokeyword.sample.app.presenter.BasePresenter;
import me.yokeyword.sample.app.ui.view.my.SettingView;

/**
 * Created by xu on 2017/6/21.
 */

public class SettingPresenter extends BasePresenter {
    private SettingView view;

    public SettingPresenter(SettingView view) {
        this.view = view;
    }

    public void exit() {
        UserInfo.logOut();
        view.exitLogin();
    }
}
