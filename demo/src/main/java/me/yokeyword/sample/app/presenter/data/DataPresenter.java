package me.yokeyword.sample.app.presenter.data;

import android.os.Bundle;

import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseFragment;
import me.yokeyword.sample.app.presenter.BasePresenter;
import me.yokeyword.sample.app.ui.view.data.DataView;

/**
 * Created by xu on 2017/6/18.
 */

public class DataPresenter extends BasePresenter {
    private DataView view;

    public DataPresenter(DataView view) {
        this.view = view;
    }

    public void loadRoot(Bundle savedInstanceState, BaseFragment fragment) {
        if (savedInstanceState == null)
            view.getContent().loadRootFragment(R.id.frame_data_content, fragment);
    }
}
