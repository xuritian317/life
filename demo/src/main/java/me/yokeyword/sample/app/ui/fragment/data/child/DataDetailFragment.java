package me.yokeyword.sample.app.ui.fragment.data.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.sample.R;
import me.yokeyword.sample.app.model.entity.DataInfo;
import me.yokeyword.sample.app.model.entity.UserData;
import me.yokeyword.sample.app.ui.adapter.DataDetailAdapter;
import me.yokeyword.sample.app.base.BaseBackFragment;
import me.yokeyword.sample.app.ui.listener.AdapterDateListener;
import me.yokeyword.sample.app.ui.listener.AdapterItemClickListener;
import me.yokeyword.sample.app.presenter.data.DataDetailPresenter;
import me.yokeyword.sample.app.ui.view.data.DataDetailView;
import me.yokeyword.sample.app.util.Logger;
import me.yokeyword.sample.app.util.ToastUtils;

/**
 * Created by YoKeyword on 16/2/3.
 */
public class DataDetailFragment extends BaseBackFragment<DataDetailPresenter> implements DataDetailView, SwipeRefreshLayout.OnRefreshListener {
    private static final String content_type = "type";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.data_detail_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.refresh_data_detail)
    SwipeRefreshLayout refreshLayout;

    private String type;

    private DataDetailAdapter adapter;

    private static DataDetailFragment instance;

    public static DataDetailFragment newInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(content_type, type);
        if (instance == null) {
            instance = new DataDetailFragment();
        }
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString(content_type);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.item_data_detail;
    }

    @Override
    public void setPresenter() {
        presenter = new DataDetailPresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {

        adapter = new DataDetailAdapter(_mActivity);
        LinearLayoutManager manager = new LinearLayoutManager(_mActivity);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        presenter.getData(type);

        adapter.setOnItemClickListener(new AdapterItemClickListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                presenter.showDialog(_mActivity, adapter.getData(), position);
            }
        });

        adapter.setDateClickListener(new AdapterDateListener() {
            @Override
            public void onItemClick(int position, View view, RecyclerView.ViewHolder vh) {
                presenter.showCalendarView(_mActivity, adapter.getData(), position);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void setToolbar() {
        mToolbar.setTitle(type);
        initToolbarNav(mToolbar);
    }
    @Override
    public void changeData(int position, DataInfo info) {
        adapter.setData(position, info);
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(_mActivity, msg);
    }

    @Override
    public void setRefreshLayout(boolean flag) {
        refreshLayout.setRefreshing(flag);
    }

    @Override
    public void setAdapterData(UserData data) {
        adapter.setData(data);
    }


    @Override
    public void loge(String msg) {
        Logger.e("msg", "");
    }

    @Override
    public void onRefresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.e("onDestroyView", "true");

    }

}
