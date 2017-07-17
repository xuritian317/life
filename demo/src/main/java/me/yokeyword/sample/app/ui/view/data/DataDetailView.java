package me.yokeyword.sample.app.ui.view.data;

import java.util.List;

import me.yokeyword.sample.app.model.entity.DetailInfo;
import me.yokeyword.sample.app.ui.view.BaseViewInterface;

/**
 * Created by xu on 2017/6/19.
 */

public interface DataDetailView extends BaseViewInterface {
    void changeData(int position, DetailInfo info);
    void showToast(String msg);
    void setRefreshLayout(boolean flag);
    void setAdapterData(List<DetailInfo> infos);
    void loge(String msg);
}
