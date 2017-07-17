package me.yokeyword.sample.app.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.sample.app.presenter.BasePresenter;
import me.yokeyword.sample.app.ui.view.BaseViewInterface;

/**
 * Created by xu on 2016/12/23.
 */

public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity implements BaseViewInterface {
    public T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
        setContentView(getLayout());
        ButterKnife.bind(this);
        initData();
        initView(savedInstanceState);
        setToolbar();
    }


    /**
     * 设置presenter
     */
    public abstract void setPresenter();

    /**
     * 返回界面layout
     *
     * @return
     */
    @LayoutRes
    public abstract int getLayout();


    /**
     * 初始化界面
     */
    public abstract void initData();

    /**
     * 初始化view
     */
    @Override
    public abstract void initView(Bundle savedInstanceState);


    /**
     * 设置toolbar
     */
    public abstract void setToolbar();
}
