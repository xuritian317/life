package me.yokeyword.sample.app.presenter.data;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.sample.app.model.entity.HomePic;
import me.yokeyword.sample.app.ui.event.TabSelectedEvent;
import me.yokeyword.sample.app.presenter.BasePresenter;
import me.yokeyword.sample.app.ui.view.data.DataContentView;

/**
 * Created by xu on 2017/6/18.
 */

public class DataContentPresenter extends BasePresenter {
    private DataContentView view;

    public DataContentPresenter(DataContentView view) {
        this.view = view;
    }

    public List<HomePic> getData(String[] types, String[] titles, String[] contents) {
        // Init Datas
        List<HomePic> homePicList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HomePic homePic = new HomePic(types[i], titles[i], contents[i]);
            homePicList.add(homePic);
        }
        return homePicList;
    }

    public void start(SupportFragment toFragment) {
        view.getContent().start(toFragment);
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

    public void setTabSelectedEvent(TabSelectedEvent event, int tag) {
        if (event.position != tag) return;
        if (view.isTop()) {
            view.refresh();
        } else {
            view.scrollToTop();
        }
    }
}
