package me.yokeyword.sample.app.ui.fragment.home.child;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.sample.R;
import me.yokeyword.sample.app.ui.activity.MainActivity;
import me.yokeyword.sample.app.ui.adapter.HomeDetailAdapter;
import me.yokeyword.sample.app.base.BaseFragment;
import me.yokeyword.sample.app.ui.event.TabSelectedEvent;
import me.yokeyword.sample.app.ui.listener.AdapterItemClickListener;
import me.yokeyword.sample.app.presenter.home.HomeContentPresenter;
import me.yokeyword.sample.app.util.ToastUtils;
import me.yokeyword.sample.app.ui.view.home.HomeContentView;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class HomeContentFragment extends BaseFragment<HomeContentPresenter> implements HomeContentView, SwipeRefreshLayout.OnRefreshListener {

    private HomeDetailAdapter mAdapter;

    private boolean mInAtTop = true;

    private int mScrollTotal;

    private String[] mTitles = new String[]{
            "健康分析",
            "消费分析",
            "自定义分析"
    };
    private int[] mImgRes = new int[]{R.drawable.bg_second, R.drawable.bg_third, R.drawable.bg_fifth};

    private static HomeContentFragment instance;

    public static HomeContentFragment newInstance() {
        if (instance == null) {
            instance = new HomeContentFragment();
        }
        return instance;
    }

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.recy)
    RecyclerView recyclerView;

    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    @BindView(R.id.fab)
    FloatingActionButton floatBtn;

    @OnClick(R.id.fab)
    public void onclickFloat() {
        ToastUtils.showToast(_mActivity, "Action");
    }

    @Override
    public void onRefresh() {
        mRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void scrollToTop() {
        recyclerView.smoothScrollToPosition(0);
    }


    @Override
    public void refresh() {
        onRefresh();
    }

    @Override
    public void setRefreshLayout() {
        mRefreshLayout.setRefreshing(true);
    }

    /**
     * 选择tab事件
     */
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        presenter.tabSelectedEvent(event, MainActivity.home);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getLayout() {
        return R.layout.item_home_content;
    }

    @Override
    public void setPresenter() {
        presenter = new HomeContentPresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);

        mAdapter = new HomeDetailAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setData(presenter.getData(mTitles, mImgRes));

        mAdapter.setOnItemClickListener(new AdapterItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                presenter.start(vh, HomeDetailFragment.newInstance(mAdapter.getItem(position)), getString(R.string.image_transition));
            }
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                presenter.setRefresh(dy);
            }
        });

    }

    @Override
    public void initData() {
    }

    @Override
    public void setToolbar() {
        mToolbar.setTitle("首页");
    }

    @Override
    public HomeContentFragment getContent() {
        return this;
    }

    @Override
    public void setIsTop(Boolean flag) {
        mInAtTop = flag;
    }

    @Override
    public boolean isTop() {
        return mInAtTop;
    }

    @Override
    public void setTotal(int value) {
        this.mScrollTotal = value;
    }

    @Override
    public int getTotal() {
        return mScrollTotal;
    }

    @Override
    public void fBtnHide() {
        floatBtn.hide();
    }

    @Override
    public void fBtnShow() {
        floatBtn.show();

    }

}
