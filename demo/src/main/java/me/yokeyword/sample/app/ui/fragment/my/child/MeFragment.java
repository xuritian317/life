package me.yokeyword.sample.app.ui.fragment.my.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseFragment;
import me.yokeyword.sample.app.ui.fragment.my.MyFragment;

/**
 * Created by YoKeyword on 16/6/6.
 */
public class MeFragment extends BaseFragment {
    @BindView(R.id.tv_btn_settings)
    TextView mTvBtnSettings;

    private static MeFragment instance;

    public static MeFragment newInstance() {
        if (instance == null)
            instance = new MeFragment();
        return instance;
    }

    @Override
    public int getLayout() {
        return R.layout.item_my_me;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTvBtnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(SettingsFragment.newInstance());
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void setToolbar() {

    }


    @Override
    public boolean onBackPressedSupport() {
        ((MyFragment) getParentFragment()).onBackToFirstFragment();
        return true;
    }
}
