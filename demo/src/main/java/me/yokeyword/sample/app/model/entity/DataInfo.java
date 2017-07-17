package me.yokeyword.sample.app.model.entity;

/**
 * Created by xu on 2017/6/21.
 */

public class DataInfo {
    private String date;
    private Integer value;

    public DataInfo(String date, Integer value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
