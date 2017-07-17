package me.yokeyword.sample.app.presenter.community;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import me.yokeyword.sample.app.model.entity.ShareInfo;
import me.yokeyword.sample.app.presenter.BasePresenter;
import me.yokeyword.sample.app.ui.event.TabSelectedEvent;
import me.yokeyword.sample.app.ui.view.community.CommContentView;
import me.yokeyword.sample.app.util.Logger;

/**
 * Created by xu on 2017/6/20.
 */

public class CommContentPresenter extends BasePresenter {
    private CommContentView view;

    public CommContentPresenter(CommContentView view) {
        this.view = view;
    }

    public void getShareData() {
        view.setRefresh(true);
        BmobQuery<ShareInfo> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(new FindListener<ShareInfo>() {
            @Override
            public void done(List<ShareInfo> list, BmobException e) {
                if (e == null) {
                    List<ShareInfo> data = new ArrayList<>();
                    for (ShareInfo info : list) {
                        data.add(info);
                    }
                    Logger.e("data", data.toString());
                    view.setAdapterData(data);
                    view.setRefresh(false);
                } else {
                    Logger.e("BmobException", e.toString());
                }
            }
        });
    }

    public void tabSelectedEvent(TabSelectedEvent event, int tag) {
        if (event.position != tag) return;
        if (view.isTop()) {
            view.setRefresh(true);
            view.refresh();
        } else {
            view.scrollToTop();
        }
    }

    public void onScroll(int dy) {
        int total = view.getTotal();
        total += dy;
        view.setTotal(total);
        if (total <= 0) {
            view.setIsTop(true);
        } else {
            view.setIsTop(false);
        }
    }
}
