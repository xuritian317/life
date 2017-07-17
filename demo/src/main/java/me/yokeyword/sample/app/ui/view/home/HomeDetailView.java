package me.yokeyword.sample.app.ui.view.home;


import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.PieData;

import me.yokeyword.sample.app.ui.view.BaseViewInterface;

/**
 * Created by xu on 2017/6/19.
 */

public interface HomeDetailView extends BaseViewInterface {
    void setBarData(BarData data);
    void setLineData(LineData data);
    void setPieData(PieData data);
    void showToast(String msg);
}
