package me.yokeyword.sample.app.ui.fragment.home.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.PieData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.sample.R;
import me.yokeyword.sample.app.db.DBManager;
import me.yokeyword.sample.app.model.entity.DataInfo;
import me.yokeyword.sample.app.model.entity.DetailInfo;
import me.yokeyword.sample.app.model.entity.DetailInfoDao;
import me.yokeyword.sample.app.model.entity.HomePic;
import me.yokeyword.sample.app.base.BaseBackFragment;
import me.yokeyword.sample.app.model.entity.UserData;
import me.yokeyword.sample.app.presenter.home.HomeDetailPresenter;
import me.yokeyword.sample.app.ui.customer.XValueFormat;
import me.yokeyword.sample.app.ui.view.home.HomeDetailView;
import me.yokeyword.sample.app.util.Logger;
import me.yokeyword.sample.app.util.ToastUtils;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class HomeDetailFragment extends BaseBackFragment<HomeDetailPresenter> implements HomeDetailView {
    private static final String TYPE = "type";
    private String type;
    private HomePic mHomePic;
    private static HomeDetailFragment instance;

    public static HomeDetailFragment newInstance(HomePic homePic) {
        Bundle args = new Bundle();
        args.putParcelable(TYPE, homePic);
        if (instance == null)
            instance = new HomeDetailFragment();
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mHomePic = getArguments().getParcelable(TYPE);
            type = mHomePic.getType();
        }
    }

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img_detail)
    ImageView mImgDetail;
    @BindView(R.id.barChart)
    BarChart barChart;
    @BindView(R.id.lineChart)
    LineChart lineChart;
    @BindView(R.id.pieChart)
    PieChart pieChart;

    @OnClick(R.id.tv_share)
    public void share() {
        presenter.share(detailInfos);
    }

    private List<DetailInfo> detailInfos;

    @Override
    public int getLayout() {
        return R.layout.item_home_detail;
    }

    @Override
    public void setPresenter() {
        presenter = new HomeDetailPresenter(this);
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mImgDetail.setImageResource(mHomePic.getImgRes());
        initBarChart();
        initLineChart();
        initPieChart();
    }

    public void initBarChart() {
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.getDescription().setEnabled(false);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(5);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new XValueFormat(detailInfos));

        barChart.getAxisRight().setEnabled(false);
        YAxis left = barChart.getAxisLeft();
        left.setTextSize(9);

        barChart.getLegend().setEnabled(false);

        presenter.setBarData(detailInfos);
    }

    public void initLineChart() {
        lineChart.getDescription().setEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(5);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new XValueFormat(detailInfos));


        lineChart.getAxisRight().setEnabled(false);
        YAxis left = lineChart.getAxisLeft();
        left.setTextSize(9);

        lineChart.getLegend().setEnabled(false);

        presenter.setLineData(detailInfos);
    }

    public void initPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);

        pieChart.getLegend().setEnabled(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setRotationEnabled(false);

        presenter.setPieData(detailInfos);
    }

    @Override
    public void initData() {
        detailInfos = new ArrayList<>();
        DetailInfoDao dao = DBManager.getInstance().detailInfoDao;
        detailInfos = dao.queryBuilder().where(DetailInfoDao.Properties.Type.eq(mHomePic.getTitle())).list();
        Logger.e("detailInfos", ""+detailInfos.size());
    }

    @Override
    public void setToolbar() {
        mToolbar.setTitle(mHomePic.getTitle());
        initToolbarNav(mToolbar);
    }


    @Override
    public void setBarData(BarData data) {
        barChart.setData(data);
    }

    @Override
    public void setLineData(LineData data) {
        lineChart.setData(data);
    }

    @Override
    public void setPieData(PieData data) {
        pieChart.setData(data);
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(_mActivity, msg);
    }

    @BindView(R.id.fab)
    FloatingActionButton floatBtn;

    @OnClick(R.id.fab)
    public void onclickFloat() {

    }
}
