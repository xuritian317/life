package me.yokeyword.sample.app.model.dao;

import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import me.yokeyword.sample.app.model.entity.UserData;
import me.yokeyword.sample.app.util.Logger;

/**
 * Created by xu on 2017/6/21.
 */

public class BmobSetUserData {
    private SetDataCallBack callBack;

    private BmobSetUserData(SetDataCallBack callBack) {
        this.callBack = callBack;
    }

    private static BmobSetUserData instance;

    public static BmobSetUserData getInstance(SetDataCallBack callBack) {
        if (instance == null) {
            synchronized (BmobQueryShare.class) {
                if (instance == null) {
                    instance = new BmobSetUserData(callBack);
                }
            }
        }
        return instance;
    }

    public void inputUser(UserData data) {
        data.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    callBack.successCallBack(s);
                } else {
                    Logger.e("数据提交失败", "");
                }
            }
        });
    }

    public interface SetDataCallBack {
        void successCallBack(String s);
    }
}
