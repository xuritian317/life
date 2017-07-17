package me.yokeyword.sample.app.ui.listener;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by xu on 2017/6/22.
 */

public interface AdapterDateListener {
    void onItemClick(int position, View view, RecyclerView.ViewHolder vh);
}
