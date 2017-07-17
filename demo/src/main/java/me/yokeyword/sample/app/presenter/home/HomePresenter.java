package me.yokeyword.sample.app.presenter.home;

import android.os.Bundle;

import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseFragment;
import me.yokeyword.sample.app.presenter.BasePresenter;
import me.yokeyword.sample.app.ui.view.home.HomeView;

/**
 * Created by xu on 2017/6/17.
 */

public class HomePresenter extends BasePresenter {
    private HomeView view;

    public HomePresenter(HomeView view) {
        this.view = view;
    }

    public void loadRoot(BaseFragment to, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            view.getContent().loadRootFragment(R.id.frame_home_content, to);
        }
    }
}
