package me.yokeyword.sample.app.ui.fragment.my.child;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseFragment;
import me.yokeyword.sample.app.presenter.my.SettingPresenter;
import me.yokeyword.sample.app.ui.activity.LoginActivity;
import me.yokeyword.sample.app.ui.activity.MainActivity;
import me.yokeyword.sample.app.ui.view.activity.LoginView;
import me.yokeyword.sample.app.ui.view.my.SettingView;

/**
 * Created by YoKeyword on 16/6/6.
 */
public class SettingsFragment extends BaseFragment<SettingPresenter> implements SettingView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.tv_exit)
    TextView tvExit;

    @OnClick(R.id.tv_exit)
    public void exit() {
        presenter.exit();
    }

    private static SettingsFragment instance;

    public static SettingsFragment newInstance() {
        if (instance == null)
            instance = new SettingsFragment();
        return instance;
    }

    @Override
    public int getLayout() {
        return R.layout.item_my_settings;
    }

    @Override
    public void setPresenter() {
        presenter = new SettingPresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public void setToolbar() {
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }


    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    @Override
    public void exitLogin() {
        _mActivity.finish();
    }

}
