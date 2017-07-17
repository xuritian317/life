package me.yokeyword.sample.app.ui.listener;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public interface AdapterItemClickListener {
    void onItemClick(int position, View view, RecyclerView.ViewHolder vh);
}