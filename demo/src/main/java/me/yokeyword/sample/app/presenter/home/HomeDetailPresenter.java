package me.yokeyword.sample.app.presenter.home;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import me.yokeyword.sample.app.model.entity.DataInfo;
import me.yokeyword.sample.app.model.entity.DetailInfo;
import me.yokeyword.sample.app.model.entity.ShareInfo;
import me.yokeyword.sample.app.presenter.BasePresenter;
import me.yokeyword.sample.app.ui.view.home.HomeDetailView;
import me.yokeyword.sample.app.util.Logger;

/**
 * Created by xu on 2017/6/19.
 */

public class HomeDetailPresenter extends BasePresenter {
    private HomeDetailView view;

    public HomeDetailPresenter(HomeDetailView view) {
        this.view = view;
    }

    public void setBarData(List<DetailInfo> infoList) {
        if (infoList.isEmpty())
            return;
        List<BarEntry> yVals = new ArrayList<>();
        for (int i = 0; i < infoList.size(); i++) {
            DetailInfo info = infoList.get(i);
            yVals.add(new BarEntry(i, info.getValues()));
        }

        BarDataSet dataSet = new BarDataSet(yVals, "");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setValueTextSize(7);

        BarData data = new BarData(dataSet);

        view.setBarData(data);
    }

    public void setLineData(List<DetailInfo> infoList) {
        if (infoList.isEmpty())
            return;
        List<Entry> yVals = new ArrayList<>();
        for (int i = 0; i < infoList.size(); i++) {
            DetailInfo info = infoList.get(i);
            yVals.add(new Entry(i, info.getValues()));
        }

        LineDataSet dataSet = new LineDataSet(yVals, "");
        dataSet.setValueTextSize(12);
        dataSet.setValueTextColors(ColorTemplate.createColors(ColorTemplate.MATERIAL_COLORS));
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setDrawCircleHole(false);

        LineData data = new LineData(dataSet);

        view.setLineData(data);
    }

    public void setPieData(List<DetailInfo> infoList) {
        if (infoList.isEmpty())
            return;
        List<PieEntry> yVals = new ArrayList<>();
        for (int i = 0; i < infoList.size(); i++) {
            DetailInfo info = infoList.get(i);
            yVals.add(new PieEntry(i, info.getValues()));
        }

        PieDataSet dataSet = new PieDataSet(yVals, "");
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        dataSet.setValueTextSize(9);
        dataSet.setDrawValues(false);
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return decimalFormat.format(value) + "%\t" + (int) entry.getY();
            }
        });

        PieData data = new PieData(dataSet);

        view.setPieData(data);
    }

    private DecimalFormat decimalFormat = new DecimalFormat("###,###,###,##0.0");

    public void share(List<DetailInfo> infoList) {
        BmobUser user = BmobUser.getCurrentUser();

        ShareInfo info = new ShareInfo();
        info.setUserName(user.getUsername());
        info.setData(infoList);

        info.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    view.showToast("分享成功");
                } else {
                    view.showToast("分享失败");
                    Logger.e("BmobException", e.toString());
                }
            }
        });
    }


}
