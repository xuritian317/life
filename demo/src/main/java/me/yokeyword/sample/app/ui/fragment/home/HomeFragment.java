package me.yokeyword.sample.app.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseMainFragment;
import me.yokeyword.sample.app.presenter.home.HomePresenter;
import me.yokeyword.sample.app.ui.fragment.home.child.HomeContentFragment;
import me.yokeyword.sample.app.ui.view.home.HomeView;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class HomeFragment extends BaseMainFragment<HomePresenter> implements HomeView {

    private static HomeFragment instance;

    public static HomeFragment newInstance() {
        if (instance == null) {
            instance = new HomeFragment();
        }
        return instance;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void setPresenter() {
        presenter = new HomePresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        presenter.loadRoot(HomeContentFragment.newInstance(), savedInstanceState);
    }

    @Override
    public HomeFragment getContent() {
        return this;
    }

}
