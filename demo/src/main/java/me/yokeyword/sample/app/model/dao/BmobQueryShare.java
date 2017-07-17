package me.yokeyword.sample.app.model.dao;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import me.yokeyword.sample.app.model.entity.DetailInfo;
import me.yokeyword.sample.app.model.entity.ShareInfo;
import me.yokeyword.sample.app.util.Logger;

/**
 * Created by xu on 2017/6/21.
 */

public class BmobQueryShare {
    public QueryShareSuccess queryShareSuccess;

    private BmobQueryShare(QueryShareSuccess queryShareSuccess) {
        this.queryShareSuccess = queryShareSuccess;
    }

    private static BmobQueryShare instance;

    public static BmobQueryShare getInstance(QueryShareSuccess queryShareSuccess) {
        if (instance == null) {
            synchronized (BmobQueryShare.class) {
                if (instance == null) {
                    instance = new BmobQueryShare(queryShareSuccess);
                }
            }
        }
        return instance;
    }

    public void getShareData() {
        cn.bmob.v3.BmobQuery<DetailInfo> query = new cn.bmob.v3.BmobQuery<>();
        query.findObjects(new FindListener<DetailInfo>() {
            @Override
            public void done(List<DetailInfo> list, BmobException e) {
                if (e == null) {
                    List<DetailInfo> data = new ArrayList<>();
                    for (DetailInfo info : list) {
                        data.add(info);
                    }
                    Logger.e("data", data.toString());
                } else {
                    Logger.e("BmobException", e.toString());
                }
            }
        });
    }

    public interface QueryShareSuccess {
        void successCallBack(List<ShareInfo> infoList);
    }
}
