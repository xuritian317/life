package me.yokeyword.sample.app.ui.customer;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

import me.yokeyword.sample.app.model.entity.DetailInfo;

/**
 * Created by xu on 2017/6/19.
 */

public class XValueFormat implements IAxisValueFormatter {

    private List<DetailInfo> infoList;

    public XValueFormat(List<DetailInfo> infoList) {
        this.infoList = infoList;

    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return infoList.get((int) value).getData();
    }
}
