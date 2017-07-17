package me.yokeyword.sample;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import cn.bmob.v3.Bmob;
import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;
import me.yokeyword.sample.app.model.entity.DaoMaster;
import me.yokeyword.sample.app.model.entity.DaoSession;

/**
 * Created by YoKey on 16/11/23.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Bmob.initialize(this, "b3584876208bea7d1e5a396967bed9cf", "bmob");

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "details-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.NONE)
                // ture时，遇到异常："Can not perform this action after onSaveInstanceState!"时，会抛出
                // false时，不会抛出，会捕获，可以在handleException()里监听到
                .debug(BuildConfig.DEBUG)
                // 线上环境时，可能会遇到上述异常，此时debug=false，不会抛出该异常（避免crash），会捕获
                // 建议在回调处上传至我们的Crash检测服务器
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(Exception e) {
                        // 以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install();
    }

    private static DaoSession daoSession;

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
