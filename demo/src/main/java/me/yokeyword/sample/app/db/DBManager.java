package me.yokeyword.sample.app.db;

import me.yokeyword.sample.App;
import me.yokeyword.sample.app.model.entity.DaoSession;
import me.yokeyword.sample.app.model.entity.DetailInfoDao;

/**
 * Created by xu on 2017/6/19.
 */

public class DBManager {
    public DetailInfoDao detailInfoDao;

    private DBManager() {
        DaoSession daoSession = App.getDaoSession();
        detailInfoDao = daoSession.getDetailInfoDao();
    }


    public static DBManager getInstance() {
        return DBManagerHolder.instance;
    }

    private static class DBManagerHolder {
        public static DBManager instance = new DBManager();
    }

}
