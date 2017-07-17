package me.yokeyword.sample.app.ui.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.yokeyword.sample.R;
import me.yokeyword.sample.app.model.entity.HomePic;
import me.yokeyword.sample.app.ui.listener.AdapterItemClickListener;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class HomeDetailAdapter extends RecyclerView.Adapter<HomeDetailAdapter.ViewHolder> {
    private List<HomePic> mItems = new ArrayList<>();
    private LayoutInflater mInflater;

    private AdapterItemClickListener mClickListener;

    public HomeDetailAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adapter_home_content, parent, false);
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

        // 把每个图片视图设置不同的Transition名称, 防止在一个视图内有多个相同的名称, 在变换的时候造成混乱
        // Fragment支持多个View进行变换, 使用适配器时, 需要加以区分
        ViewCompat.setTransitionName(holder.img, String.valueOf(position) + "_image");
        ViewCompat.setTransitionName(holder.tvTitle, String.valueOf(position) + "_tv");

        holder.img.setImageResource(item.getImgRes());
        holder.tvTitle.setText(item.getTitle());
    }

    public void setData(List<HomePic> items) {
        mItems.clear();
        mItems.addAll(items);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public HomePic getItem(int position) {
        return mItems.get(position);
    }

    public void setOnItemClickListener(AdapterItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            img = (ImageView) itemView.findViewById(R.id.img);
        }
    }
}
