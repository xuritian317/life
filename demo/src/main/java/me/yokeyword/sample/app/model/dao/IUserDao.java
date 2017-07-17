package me.yokeyword.sample.app.model.dao;

/**
 * Created by xu on 2017/6/13.
 */

public interface IUserDao {
    void login(String username, final String password, MessageCallBack callBack);

    interface MessageCallBack {
        void callBack(int tag);
    }
}
