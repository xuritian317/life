package me.yokeyword.sample.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.sample.R;
import me.yokeyword.sample.app.model.entity.DataInfo;
import me.yokeyword.sample.app.model.entity.DetailInfo;
import me.yokeyword.sample.app.model.entity.ShareInfo;
import me.yokeyword.sample.app.ui.customer.XValueFormat;
import me.yokeyword.sample.app.ui.listener.AdapterItemClickListener;

/**
 * Created by xu on 2017/6/21.
 */

public class CommContentAdapter extends RecyclerView.Adapter<CommContentAdapter.ViewHolder> {

    private AdapterItemClickListener mClickListener;
    private List<ShareInfo> data = new ArrayList<>();

    private LayoutInflater inflate;

    public CommContentAdapter(Context context) {
        this.inflate = LayoutInflater.from(context);
    }

    public void setData(List<ShareInfo> data) {
        this.data.clear();
        this.data = data;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflate.inflate(R.layout.adapter_comm_content, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v, holder);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShareInfo info = data.get(position);
        holder.tvName.setText(info.getUserName());
        holder.setBarData(info.getData());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        BarChart barChart;

        XAxis xAxis;

        public void initBarChart() {
            barChart.setDrawBarShadow(false);
            barChart.setDrawValueAboveBar(true);
            barChart.getDescription().setEnabled(false);

            xAxis = barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setTextSize(5);
            xAxis.setDrawGridLines(false);

            barChart.getAxisRight().setEnabled(false);
            YAxis left = barChart.getAxisLeft();
            left.setTextSize(9);

            barChart.getLegend().setEnabled(false);

        }

        public void setBarData(List<DetailInfo> infoList) {
            xAxis.setValueFormatter(new XValueFormat(infoList));

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

            barChart.setData(data);
        }

        public ViewHolder(View itemView) {
            super(itemView);
            barChart = (BarChart) itemView.findViewById(R.id.share_barChart);
            tvName = (TextView) itemView.findViewById(R.id.tv_userName);
            initBarChart();
        }
    }

    public void setOnItemClickListener(AdapterItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
