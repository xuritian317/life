package me.yokeyword.sample.app.presenter.home;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.sample.app.ui.adapter.HomeDetailAdapter;
import me.yokeyword.sample.app.base.BaseBackFragment;
import me.yokeyword.sample.app.ui.event.TabSelectedEvent;
import me.yokeyword.sample.app.ui.helper.DetailTransition;
import me.yokeyword.sample.app.model.entity.HomePic;
import me.yokeyword.sample.app.presenter.BasePresenter;
import me.yokeyword.sample.app.ui.view.home.HomeContentView;

/**
 * Created by xu on 2017/6/17.
 */

public class HomeContentPresenter extends BasePresenter {
    private HomeContentView view;

    public HomeContentPresenter(HomeContentView view) {
        this.view = view;
    }

    public void start(RecyclerView.ViewHolder vh, BaseBackFragment backFragment, String str) {
        // 这里是使用SharedElement的用例
        // LOLLIPOP(5.0)系统的 SharedElement支持有 系统BUG， 这里判断大于 > LOLLIPOP
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            backFragment.setExitTransition(new Fade());
            backFragment.setEnterTransition(new Fade());
            backFragment.setSharedElementReturnTransition(new DetailTransition());
            backFragment.setSharedElementEnterTransition(new DetailTransition());

            // 25.1.0以下的support包,Material过渡动画只有在进栈时有,返回时没有;
            // 25.1.0+的support包，SharedElement正常

            backFragment.transaction()
                    .addSharedElement(((HomeDetailAdapter.ViewHolder) vh).img, str)
                    .addSharedElement(((HomeDetailAdapter.ViewHolder) vh).tvTitle, "tv")
                    .commit();
        }
        view.getContent().start(backFragment);
    }

    public List<HomePic> getData(String[] mTitles, int[] mImgRes) {
        // Init Datas
        List<HomePic> homePicList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HomePic homePic = new HomePic(mTitles[i], mImgRes[i]);
            homePicList.add(homePic);
        }
        return homePicList;
    }

    public void setRefresh(int dy) {
        int total = view.getTotal();
        total += dy;
        view.setTotal(total);
        if (total <= 0) {
            view.setIsTop(true);
        } else {
            view.setIsTop(false);
        }
        if (dy > 5) {
            view.fBtnHide();
        } else if (dy < -5) {
            view.fBtnShow();
        }
    }

    public void tabSelectedEvent(TabSelectedEvent event, int tag) {
        if (event.position != tag) return;
        if (view.isTop()) {
            view.setRefreshLayout();
            view.refresh();
        } else {
            view.scrollToTop();
        }
    }
}
