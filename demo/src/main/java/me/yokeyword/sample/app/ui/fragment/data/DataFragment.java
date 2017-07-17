package me.yokeyword.sample.app.ui.fragment.data;

import android.os.Bundle;
import android.support.annotation.Nullable;

import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseMainFragment;
import me.yokeyword.sample.app.presenter.data.DataPresenter;
import me.yokeyword.sample.app.ui.fragment.data.child.DataContentFragment;
import me.yokeyword.sample.app.ui.view.data.DataView;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class DataFragment extends BaseMainFragment<DataPresenter> implements DataView{

    private static DataFragment instance;
    public static DataFragment newInstance() {
        if (instance==null){
            instance = new DataFragment();
        }
        return instance;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_data;
    }

    @Override
    public void setPresenter() {
        presenter = new DataPresenter(this);
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
        presenter.loadRoot(savedInstanceState,DataContentFragment.newInstance());
    }


    @Override
    public DataFragment getContent() {
        return this;
    }
}
