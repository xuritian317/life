package me.yokeyword.sample.app.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 主页面效果实体类
 * Created by YoKeyword on 16/2/1.
 */
public class HomePic implements Parcelable {
    private String title;
    private String content;
    private int imgRes;
    private String type;

    public HomePic(String title, String content, int imgRes, String type) {
        this.title = title;
        this.content = content;
        this.imgRes = imgRes;
        this.type = type;
    }

    public HomePic(String type, String title, String content) {
        this.title = title;
        this.content = content;
        this.type = type;
    }

    public HomePic(String title, int imgRes) {
        this.title = title;
        this.imgRes = imgRes;
    }

    protected HomePic(Parcel in) {
        title = in.readString();
        content = in.readString();
        imgRes = in.readInt();
        type = in.readString();
    }

    public static final Creator<HomePic> CREATOR = new Creator<HomePic>() {
        @Override
        public HomePic createFromParcel(Parcel in) {
            return new HomePic(in);
        }

        @Override
        public HomePic[] newArray(int size) {
            return new HomePic[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeInt(imgRes);
        dest.writeString(type);
    }
}
