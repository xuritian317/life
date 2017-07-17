package me.yokeyword.sample.app.model.dao;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import me.yokeyword.sample.app.model.entity.UserData;
import me.yokeyword.sample.app.model.entity.UserInfo;
import me.yokeyword.sample.app.util.Logger;

/**
 * Created by xu on 2017/6/21.
 */

public class BmobQueryUserData {
    public QueryDataCallBack callBack;

    private BmobQueryUserData(QueryDataCallBack callBack) {
        this.callBack = callBack;
    }

    private static BmobQueryUserData instance;

    public static BmobQueryUserData getInstance(QueryDataCallBack querySuccess) {
        if (instance == null) {
            synchronized (BmobQueryShare.class) {
                if (instance == null) {
                    instance = new BmobQueryUserData(querySuccess);
                }
            }
        }
        return instance;
    }

    private String userTel;
    private String userName;

    public void getUserData(String type) {
        UserInfo userInfo = BmobUser.getCurrentUser(UserInfo.class);
        userTel = userInfo.getMobilePhoneNumber();
        userName = userInfo.getUsername();

        BmobQuery<UserData> query = new BmobQuery<>();
        query.addWhereEqualTo("userTel", userTel);
        query.addWhereEqualTo("type", type);
        Logger.e("userTel", userTel);

        query.findObjects(new FindListener<UserData>() {
            @Override
            public void done(List<UserData> list, BmobException e) {
                if (e == null) {
                    List<UserData> data = new ArrayList<>();
                    for (UserData info : list) {
                        data.add(info);
                    }
                    callBack.successCallBack(userTel, userName, data);
                } else {
                    callBack.failCallBack(userTel, userName, e);
                }
            }
        });
    }


    public interface QueryDataCallBack {

        void successCallBack(String userTel, String userName, List<UserData> infoList);

        void failCallBack(String userTel, String userName, BmobException e);
    }
}
