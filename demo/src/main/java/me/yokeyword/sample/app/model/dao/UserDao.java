package me.yokeyword.sample.app.model.dao;


import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import me.yokeyword.sample.Common;
import me.yokeyword.sample.app.model.entity.UserInfo;
import me.yokeyword.sample.app.util.Logger;

/**
 * Created by xu on 2017/6/13.
 */

public class UserDao implements IUserDao {

    public void login(String username, String password, final MessageCallBack callBack) {

        BmobUser.loginByAccount(username, password, new LogInListener<UserInfo>() {
            @Override
            public void done(UserInfo info, BmobException e) {
                if (info != null) {
                    callBack.callBack(Common.TAG_SUCCESS);
                } else {
                    callBack.callBack(Common.TAG_FAIL);
                }
            }
        });
    }


}
