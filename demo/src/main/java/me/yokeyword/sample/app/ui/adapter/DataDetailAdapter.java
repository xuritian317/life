package me.yokeyword.sample.app.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.yokeyword.sample.R;
import me.yokeyword.sample.app.model.entity.DetailInfo;
import me.yokeyword.sample.app.ui.listener.AdapterDateListener;
import me.yokeyword.sample.app.ui.listener.AdapterItemClickListener;

public class DataDetailAdapter extends RecyclerView.Adapter<DataDetailAdapter.ViewHolder> {
    private List<DetailInfo> infoList = new ArrayList<>();
    private LayoutInflater mInflater;

    private AdapterItemClickListener mClickListener;
    private AdapterDateListener dateClick;

    public DataDetailAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<DetailInfo> info) {
        infoList.clear();
        infoList.addAll(info);
        notifyDataSetChanged();
    }

    public void setData(int position, DetailInfo info) {
        infoList.set(position, info);
        notifyDataSetChanged();
    }

    public List<DetailInfo> getData() {
        return infoList;
    }

    public DetailInfo getData(int position) {
        return infoList.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_data_detail, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.tvValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (mClickListener != null) {
                    mClickListener.onItemClick(position, v, holder);
                }
            }
        });
        holder.tvData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (dateClick != null) {
                    dateClick.onItemClick(position, v, holder);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DetailInfo info = infoList.get(position);
        holder.tvData.setText(info.getData());
        holder.tvValue.setText(info.getValues() + "");
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvData, tvValue;

        public ViewHolder(View itemView) {
            super(itemView);
            tvData = (TextView) itemView.findViewById(R.id.tv_data);
            tvValue = (TextView) itemView.findViewById(R.id.tv_value);
        }
    }

    public void setOnItemClickListener(AdapterItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
    public void setDateClickListener(AdapterDateListener dateClick) {
        this.dateClick = dateClick;
    }
}
