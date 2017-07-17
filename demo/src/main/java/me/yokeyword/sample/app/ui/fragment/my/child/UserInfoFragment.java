package me.yokeyword.sample.app.ui.fragment.my.child;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseFragment;
import me.yokeyword.sample.app.presenter.my.UserIInfoPresenter;
import me.yokeyword.sample.app.ui.view.my.UserInfoView;

/**
 * Created by YoKeyword on 16/6/6.
 */
public class UserInfoFragment extends BaseFragment<UserIInfoPresenter> implements UserInfoView {

    private static UserInfoFragment instance;

    public static UserInfoFragment newInstance() {
        if (instance == null)
            instance = new UserInfoFragment();
        return instance;
    }

    @BindView(R.id.tv_name)
    TextView tvName;

    @Override
    public int getLayout() {
        return R.layout.item_my_avatar;
    }

    @Override
    public void setPresenter() {
        presenter = new UserIInfoPresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        presenter.setUserInfo();
    }

    @Override
    public void initData() {

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void setUserName(String name) {
        tvName.setText(name);
    }
}
