package me.yokeyword.sample.app.model.entity;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by xu on 2017/6/21.
 */

public class ShareInfo extends BmobObject {
    private String userName;
    private String userTel;
    private String type;
    private List<DetailInfo> data;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<DetailInfo> getData() {
        return data;
    }

    public void setData(List<DetailInfo> data) {
        this.data = data;
    }
}
