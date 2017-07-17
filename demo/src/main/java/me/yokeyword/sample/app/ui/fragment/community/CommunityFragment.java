package me.yokeyword.sample.app.ui.fragment.community;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseMainFragment;
import me.yokeyword.sample.app.presenter.community.CommPresenter;
import me.yokeyword.sample.app.ui.fragment.community.child.CommContentFragment;
import me.yokeyword.sample.app.ui.view.community.CommunityView;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class CommunityFragment extends BaseMainFragment<CommPresenter> implements CommunityView {

    private static CommunityFragment instance;

    public static CommunityFragment newInstance() {
        if (instance == null)
            instance = new CommunityFragment();
        return instance;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_community;
    }

    @Override
    public void setPresenter() {
        presenter = new CommPresenter(this);
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
        presenter.loadRoot(savedInstanceState, CommContentFragment.newInstance());
    }

    @Override
    public CommunityFragment getContent() {
        return this;
    }
}
