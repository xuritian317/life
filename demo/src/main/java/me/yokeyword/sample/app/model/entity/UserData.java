package me.yokeyword.sample.app.model.entity;

import java.util.List;
import cn.bmob.v3.BmobObject;

/**
 * 用户分析数据的实体类
 * Created by xu on 2017/6/21.
 */

public class UserData extends BmobObject {

    private String userTel;
    private String userName;
    private String type;

    private List<DataInfo> data;

    public UserData(String userTel, String userName, String type, List<DataInfo> data) {
        this.userName = userName;
        this.userTel = userTel;
        this.type = type;
        this.data = data;
    }

    public UserData() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<DataInfo> getData() {
        return data;
    }

    public void setData(List<DataInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userTel='" + userTel + '\'' +
                ", userName='" + userName + '\'' +
                ", type='" + type + '\'' +
                ", data=" + data +
                '}';
    }
}
