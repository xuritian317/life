package me.yokeyword.sample.app.ui.fragment.community.child;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.sample.R;
import me.yokeyword.sample.app.base.BaseFragment;
import me.yokeyword.sample.app.model.entity.ShareInfo;
import me.yokeyword.sample.app.presenter.community.CommContentPresenter;
import me.yokeyword.sample.app.ui.activity.MainActivity;
import me.yokeyword.sample.app.ui.adapter.CommContentAdapter;
import me.yokeyword.sample.app.ui.event.TabSelectedEvent;
import me.yokeyword.sample.app.ui.listener.AdapterItemClickListener;
import me.yokeyword.sample.app.ui.view.community.CommContentView;

/**
 * Created by YoKeyword on 16/2/9.
 */
public class CommContentFragment extends BaseFragment<CommContentPresenter> implements CommContentView, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private static CommContentFragment instance;

    public static CommContentFragment newInstance() {
        if (instance == null)
            instance = new CommContentFragment();
        return instance;
    }

    private CommContentAdapter adapter;

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }

    @Override
    public int getLayout() {
        return R.layout.item_comm_content;
    }

    @Override
    public void setPresenter() {
        presenter = new CommContentPresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        adapter = new CommContentAdapter(_mActivity);

        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new AdapterItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
//                presenter.start(vh, HomeDetailFragment.newInstance(adapter.getItem(position)), getString(R.string.image_transition));
            }
        });
        presenter.getShareData();

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
        mToolbar.setTitle("社区");
    }


    @Override
    public void setAdapterData(List<ShareInfo> infoList) {
        adapter.setData(infoList);
    }

    @Override
    public void setRefresh(boolean flag) {
        refreshLayout.setRefreshing(flag);
    }

    private boolean mInAtTop = true;

    private int mScrollTotal;

    @Override
    public boolean isTop() {
        return mInAtTop;
    }


    @Override
    public void refresh() {
        onRefresh();
    }

    @Override
    public void scrollToTop() {
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public int getTotal() {
        return mScrollTotal;
    }

    @Override
    public void setTotal(int total) {
        this.mScrollTotal = total;
    }

    @Override
    public void setIsTop(boolean b) {
        this.mInAtTop = b;
    }

    /**
     * 选择tab事件
     */
    @Subscribe
    public void onTabSelectedEvent(TabSelectedEvent event) {
        presenter.tabSelectedEvent(event, MainActivity.community);
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
    public void onRefresh() {
        presenter.getShareData();
    }
}
