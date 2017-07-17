package me.yokeyword.sample.app.presenter.my;


import cn.bmob.v3.BmobUser;
import me.yokeyword.sample.app.model.entity.UserInfo;
import me.yokeyword.sample.app.presenter.BasePresenter;
import me.yokeyword.sample.app.ui.view.my.UserInfoView;

/**
 * Created by xu on 2017/6/21.
 */

public class UserIInfoPresenter extends BasePresenter {
    private UserInfoView view;

    public UserIInfoPresenter(UserInfoView view) {
        this.view = view;
    }

    public void setUserInfo() {
        UserInfo info = BmobUser.getCurrentUser(UserInfo.class);
        String userName = info.getUsername();
        view.setUserName(userName);
    }
}
