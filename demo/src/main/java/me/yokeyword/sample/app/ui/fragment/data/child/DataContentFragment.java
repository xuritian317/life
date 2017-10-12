package me.yokeyword.sample.app.ui.fragment.data.child;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import me.yokeyword.sample.R;
import me.yokeyword.sample.app.ui.activity.MainActivity;
import me.yokeyword.sample.app.ui.adapter.DataContentAdapter;
import me.yokeyword.sample.app.ui.event.TabSelectedEvent;
import me.yokeyword.sample.app.ui.listener.AdapterItemClickListener;
import me.yokeyword.sample.app.base.BaseFragment;
import me.yokeyword.sample.app.presenter.data.DataContentPresenter;
import me.yokeyword.sample.app.ui.view.data.DataContentView;
import me.yokeyword.sample.app.util.Logger;

/**
 * Created by YoKeyword on 16/6/3.
 */
public class DataContentFragment extends BaseFragment<DataContentPresenter> implements DataContentView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recy)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;


    private DataContentAdapter mAdapter;

    private boolean mAtTop = true;

    private int mScrollTotal;
    private String[] mTypes = new String[]{
            "健康分析",
            "消费分析",
            "自定义分析"
    };

    private String[] mTitles = new String[]{
            "健康分析",
            "消费分析",
            "自定义分析"
    };

    private String[] mContents = new String[]{
            "最近30天步行情况统计",
            "最近30天消费情况统计",
            "最近30天情况统计"
    };
    private static DataContentFragment instance;

    public static DataContentFragment newInstance() {
        if (instance == null) {
            instance = new DataContentFragment();
        }
        return instance;
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

    /**
     * 选择tab事件
     */
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        presenter.setTabSelectedEvent(event, MainActivity.data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public int getLayout() {
        return R.layout.item_data_content;
    }

    @Override
    public void setPresenter() {
        presenter = new DataContentPresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(this);

        mAdapter = new DataContentAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setData(presenter.getData(mTypes,mTitles, mContents));

        mAdapter.setOnItemClickListener(new AdapterItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                presenter.start(DataDetailFragment.newInstance(mAdapter.getItem(position).getType()));
//                Logger.e("mAdapter.getItem(position).getType())",mAdapter.getItem(position).getType());
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                presenter.onScroll(dy);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void setToolbar() {
        mToolbar.setTitle("分析");
    }

    @Override
    public DataContentFragment getContent() {
        return this;
    }

    @Override
    public void setTotal(int total) {
        this.mScrollTotal = total;
    }

    @Override
    public int getTotal() {
        return mScrollTotal;
    }

    @Override
    public void setIsTop(boolean flag) {
        this.mAtTop = flag;
    }

    @Override
    public boolean isTop() {
        return mAtTop;
    }

    @Override
    public void scrollToTop() {
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void refresh() {
        mRefreshLayout.setRefreshing(true);
        onRefresh();
    }
}
