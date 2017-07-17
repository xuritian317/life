package me.yokeyword.sample.app.base;

import android.support.v7.widget.Toolbar;
import android.view.View;

import me.yokeyword.sample.R;
import me.yokeyword.sample.app.presenter.BasePresenter;

/**
 * Created by YoKeyword on 16/2/7.
 */
public abstract class BaseBackFragment<T extends BasePresenter> extends BaseFragment {
    public T presenter;

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
    }
}
