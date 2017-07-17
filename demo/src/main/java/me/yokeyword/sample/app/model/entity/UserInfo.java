package me.yokeyword.sample.app.model.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by xu on 2017/6/20.
 */

public class UserInfo extends BmobUser{
    private String name;

    public UserInfo(String name) {
        this.name = name;
    }

    public UserInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
