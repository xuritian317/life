package me.yokeyword.sample.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.sample.R;
import me.yokeyword.sample.app.model.entity.HomePic;
import me.yokeyword.sample.app.ui.listener.AdapterItemClickListener;

/**
 * 主页HomeFragment  Adapter
 * Created by YoKeyword on 16/2/1.
 */
public class DataContentAdapter extends RecyclerView.Adapter<DataContentAdapter.ViewHolder> {
    private List<HomePic> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    private AdapterItemClickListener mClickListener;

    public DataContentAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    public void setData(List<HomePic> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_data_content, parent, false);
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
        HomePic item = mItems.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvContent.setText(item.getContent());
        if (item.getType() != null)
            holder.tvType.setText(item.getType());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public HomePic getItem(int position) {
        return mItems.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvContent, tvType;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);
        }
    }

    public void setOnItemClickListener(AdapterItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }
}
