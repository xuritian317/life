package me.yokeyword.sample.app.ui.fragment.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseMainFragment;
import me.yokeyword.sample.app.ui.fragment.my.child.UserInfoFragment;
import me.yokeyword.sample.app.ui.fragment.my.child.MeFragment;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class MyFragment extends BaseMainFragment {
    @BindView(R.id.toolbar)
     Toolbar mToolbar;

    public static MyFragment newInstance() {

        Bundle args = new Bundle();

        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void setPresenter() {

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
        if (savedInstanceState == null) {
            loadFragment();
        } else {  // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (findChildFragment(UserInfoFragment.class) == null) {
                loadFragment();
            }
        }
        mToolbar.setTitle("我的");
    }

    private void loadFragment() {
        loadRootFragment(R.id.fl_fourth_container_upper, UserInfoFragment.newInstance());
        loadRootFragment(R.id.fl_fourth_container_lower, MeFragment.newInstance());
    }

    public void onBackToFirstFragment() {
        _mBackToFirstListener.onBackToFirstFragment();
    }
}
