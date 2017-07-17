package me.yokeyword.sample.app.presenter.community;

import android.os.Bundle;

import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseFragment;
import me.yokeyword.sample.app.presenter.BasePresenter;
import me.yokeyword.sample.app.ui.fragment.community.CommunityFragment;
import me.yokeyword.sample.app.ui.fragment.community.child.CommContentFragment;
import me.yokeyword.sample.app.ui.fragment.my.child.UserInfoFragment;
import me.yokeyword.sample.app.ui.view.community.CommunityView;

/**
 * Created by xu on 2017/6/20.
 */

public class CommPresenter extends BasePresenter {
    private CommunityView view;

    public CommPresenter(CommunityView view) {
        this.view = view;
    }

    public void loadRoot(Bundle savedInstanceState, BaseFragment fragment) {
        if (savedInstanceState == null) {
            view.getContent().loadRootFragment(R.id.fl_third_container, fragment);
        }else {  // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (view.getContent().findChildFragment(CommContentFragment.class) == null) {
                view.getContent().loadRootFragment(R.id.fl_third_container, fragment);
            }
        }
    }
}
