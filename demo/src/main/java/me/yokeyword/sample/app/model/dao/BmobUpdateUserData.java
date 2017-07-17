package me.yokeyword.sample.app.model.dao;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import me.yokeyword.sample.app.model.entity.DataInfo;
import me.yokeyword.sample.app.model.entity.DetailInfo;
import me.yokeyword.sample.app.model.entity.UserData;
import me.yokeyword.sample.app.util.Logger;

/**
 * Created by xu on 2017/6/22.
 */

public class BmobUpdateUserData {


    private BmobUpdateUserData() {

    }

    private static BmobUpdateUserData instance;

    public static BmobUpdateUserData getInstance() {
        if (instance == null) {
            synchronized (BmobQueryShare.class) {
                if (instance == null) {
                    instance = new BmobUpdateUserData();
                }
            }
        }
        return instance;
    }


    public void updateUserDataValue(String objectId, int position, int value) {

        UserData data = new UserData();

        data.setValue("data." + position + ".value", value);

        data.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Logger.e("更新成功", "");
                } else {
                    Logger.e("更新失败", "");
                }
            }
        });
    }

    public void updateUserDate(String objectId, List<DetailInfo> detailInfos) {

        UserData data = new UserData();

        List<DataInfo> dataInfos = new ArrayList<>();

        for (DetailInfo info : detailInfos) {
            dataInfos.add(new DataInfo(info.getData(), info.getValues()));
        }
        data.setData(dataInfos);

        data.update(objectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Logger.e("更新成功", "");
                } else {
                    Logger.e("更新失败", "");
                }
            }
        });
    }
}
