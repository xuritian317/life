package me.yokeyword.sample.app.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import cn.bmob.v3.BmobObject;

/**
 * Created by xu on 2017/6/19.
 */
@Entity
public class DetailInfo {
    @Id
    private Long id;

    private String ObjectId;
    private String userName;
    private String userTel;
    private String type;
    private String data;
    private int values;

    public DetailInfo() {
    }
    public DetailInfo(String userTel,String userName,String type, String data, int values) {
        this.userTel = userTel;
        this.userName = userName;
        this.type = type;
        this.data = data;
        this.values = values;
    }

    @Generated(hash = 1214578705)
    public DetailInfo(Long id, String ObjectId, String userName, String userTel, String type,
            String data, int values) {
        this.id = id;
        this.ObjectId = ObjectId;
        this.userName = userName;
        this.userTel = userTel;
        this.type = type;
        this.data = data;
        this.values = values;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getValues() {
        return values;
    }

    public void setValues(int values) {
        this.values = values;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserTel() {
        return this.userTel;
    }


    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    @Override
    public String toString() {
        return "DetailInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", type='" + type + '\'' +
                ", data='" + data + '\'' +
                ", values=" + values +
                '}';
    }

    public String getObjectId() {
        return this.ObjectId;
    }

    public void setObjectId(String ObjectId) {
        this.ObjectId = ObjectId;
    }
}
